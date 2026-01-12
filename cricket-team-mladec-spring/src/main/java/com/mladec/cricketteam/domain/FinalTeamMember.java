
package com.mladec.cricketteam.domain;

public class FinalTeamMember {
    private int playerId;
    private String name;
    private String role;
    private double battingAverage;
    private double strikeRate;
    private double bowlingWicketsPerMatch;
    private double economy;
    private int totalRuns;
    private int totalWickets;

    public FinalTeamMember(int playerId, String name, String role, double battingAverage, double strikeRate,
                           double bowlingWicketsPerMatch, double economy, int totalRuns, int totalWickets) {
        this.playerId = playerId; this.name = name; this.role = role;
        this.battingAverage = battingAverage; this.strikeRate = strikeRate;
        this.bowlingWicketsPerMatch = bowlingWicketsPerMatch; this.economy = economy;
        this.totalRuns = totalRuns; this.totalWickets = totalWickets;
    }

    public int getPlayerId() { return playerId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getBattingAverage() { return battingAverage; }
    public double getStrikeRate() { return strikeRate; }
    public double getBowlingWicketsPerMatch() { return bowlingWicketsPerMatch; }
    public double getEconomy() { return economy; }
    public int getTotalRuns() { return totalRuns; }
    public int getTotalWickets() { return totalWickets; }
}
