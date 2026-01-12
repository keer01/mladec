
package com.mladec.cricketteam;

import com.mladec.cricketteam.config.AppConfig;
import com.mladec.cricketteam.domain.FinalTeamMember;
import com.mladec.cricketteam.service.TeamSelectionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            TeamSelectionService service = ctx.getBean(TeamSelectionService.class);
            System.out.println("===== Final World Cup Team (mladec) =====");
            List<FinalTeamMember> team = service.buildFinalTeam();
            for (FinalTeamMember m : team) {
                System.out.printf("%-20s %-15s | Bat Avg: %.2f | SR: %.1f | Wkts/Match: %.2f | Economy: %.2f | Runs: %d | Wickets: %d%n",
                        m.getName(), m.getRole(), m.getBattingAverage(), m.getStrikeRate(),
                        m.getBowlingWicketsPerMatch(), m.getEconomy(), m.getTotalRuns(), m.getTotalWickets());
            }
        }
    }
}
