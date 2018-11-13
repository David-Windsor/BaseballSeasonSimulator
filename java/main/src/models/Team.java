package models;

import java.util.ArrayList;

/**
 * Representation of a Team in the MLB. Will be expanded on as much as needed for the granularity of the simulation
 */
@SuppressWarnings("unused")
public class Team {
    private ArrayList<Player> roster;

    private int battingAverage;

    private String teamId;
    private String teamName;
    private String league;
    private String division;

    public Team(String id, String name, String leagueId, String divisionId, int battingAverage) {
        roster = new ArrayList<>();
        this.battingAverage = battingAverage;
        teamId = id;
        teamName = name;
        league = leagueId;
        division = divisionId;
    }

    public String getTeamId() {
        return teamId;
    }

    String getTeamName() {
        return teamName;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }


    int getBattingAverage() {
        return battingAverage;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    @Override
    public String toString() {
        return teamId;
    }

    @Override
    public int hashCode() {
        return (league + division + teamId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Team.class && ((Team) obj).getTeamId().equals(teamId)
                && battingAverage == ((Team) obj).getBattingAverage();
    }

}
