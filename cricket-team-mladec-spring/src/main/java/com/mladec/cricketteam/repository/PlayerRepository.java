
package com.mladec.cricketteam.repository;

import com.mladec.cricketteam.domain.Player;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayerRepository {
    private final JdbcTemplate jdbc;
    public PlayerRepository(@Qualifier("playersJdbcTemplate") JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Player> findAll() {
        return jdbc.query("SELECT id, name, role FROM players", new RowMapper<Player>() {
            @Override
            public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        });
    }
}
