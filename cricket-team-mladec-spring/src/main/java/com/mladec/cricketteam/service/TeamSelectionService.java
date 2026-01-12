
package com.mladec.cricketteam.service;

import com.mladec.cricketteam.domain.FinalTeamMember;
import com.mladec.cricketteam.domain.Player;
import com.mladec.cricketteam.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamSelectionService {
    private final PlayerRepository playerRepo;
    private final JdbcTemplate battingJdbc;
    private final JdbcTemplate bowlingJdbc;

    public TeamSelectionService(PlayerRepository playerRepo,
                                @Qualifier("battingJdbcTemplate") JdbcTemplate battingJdbc,
                                @Qualifier("bowlingJdbcTemplate") JdbcTemplate bowlingJdbc) {
        this.playerRepo = playerRepo;
        this.battingJdbc = battingJdbc;
        this.bowlingJdbc = bowlingJdbc;
    }

    public List<FinalTeamMember> buildFinalTeam() {
        List<Player> players = playerRepo.findAll();
        Map<Integer, Player> pm = players.stream().collect(Collectors.toMap(Player::getId, p -> p));

        Map<Integer, double[]> batAgg = new HashMap<>();
        String batSql = "SELECT player_id, AVG(runs) AS avg_runs, AVG(runs*100.0/balls) AS avg_sr, SUM(runs) AS total_runs FROM batting_stats GROUP BY player_id";
        battingJdbc.query(batSql, (ResultSetExtractor<Void>) rs -> {
            while (rs.next()) {
                batAgg.put(rs.getInt("player_id"), new double[]{
                        rs.getDouble("avg_runs"),
                        rs.getDouble("avg_sr"),
                        rs.getInt("total_runs")
                });
            }
            return null;
        });

        Map<Integer, double[]> bowlAgg = new HashMap<>();
        String bowlSql = "SELECT player_id, AVG(wickets) AS avg_wkts, AVG(CASE WHEN overs>0 THEN runs_conceded/overs ELSE NULL END) AS eco, SUM(wickets) AS total_wkts FROM bowling_stats GROUP BY player_id";
        bowlingJdbc.query(bowlSql, (ResultSetExtractor<Void>) rs -> {
            while (rs.next()) {
                bowlAgg.put(rs.getInt("player_id"), new double[]{
                        rs.getDouble("avg_wkts"),
                        rs.getDouble("eco"),
                        rs.getInt("total_wkts")
                });
            }
            return null;
        });

        List<Integer> ids = players.stream()
                .map(Player::getId)
                .sorted((a, b) -> Double.compare(
                        batAgg.getOrDefault(b, new double[]{0,0,0})[0] + 20 * bowlAgg.getOrDefault(b, new double[]{0,0,0})[0],
                        batAgg.getOrDefault(a, new double[]{0,0,0})[0] + 20 * bowlAgg.getOrDefault(a, new double[]{0,0,0})[0]
                ))
                .limit(11)
                .collect(Collectors.toList());

        List<FinalTeamMember> team = new ArrayList<>();
        for (int pid : ids) {
            Player p = pm.get(pid);
            double[] ba = batAgg.getOrDefault(pid, new double[]{0,0,0});
            double[] bw = bowlAgg.getOrDefault(pid, new double[]{0,0,0});
            double eco = Double.isNaN(bw[1]) ? 0.0 : bw[1];
            team.add(new FinalTeamMember(pid, p.getName(), p.getRole(), ba[0], ba[1], bw[0], eco, (int) ba[2], (int) bw[2]));
        }
        return team;
    }
}
