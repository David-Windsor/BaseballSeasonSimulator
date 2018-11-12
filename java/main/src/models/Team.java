package models;

import java.util.ArrayList;

/**
 * Class that houses all the information about a team
 * Right now this just includes players but could be
 * expanded to other information
 * teamValue is what we will use at first to determine wins and losses
 * this will be deprecated later
 */
@SuppressWarnings("unused")
public class Team {
    private ArrayList<Player> roster;

    private double teamValue;

    private String teamId;
    private String teamName;
    private String league;
    private String division;

    public Team(String id, String name, String leagueId, String divisionId) {
        roster = new ArrayList<>();
        teamValue = 0;
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


    double getTeamValue() {
        return teamValue;
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
        return obj.getClass() == Team.class && ((Team) obj).getTeamId().equals(teamId);
    }

    /**
     * @author David Windsor
     * Builder pattern implementation for the Team class. As time goes on this class will become much more complicated
     * and require several paramaters. Using this we can avoid excessively long contructors
     */
    public static class TeamBuilder {

    }
}
