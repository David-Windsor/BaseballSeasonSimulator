package models;

import java.util.ArrayList;

/**
 * Class that houses all the information about a team
 * Right now this just includes players but could be
 * expanded to other information
 * teamValue is what we will use at first to determine wins and losses
 * this will be deprecated later
 */
public class Team {
    private ArrayList<Player> roster;

    private double teamValue;

    private String teamId;
    private String teamName;
    private String league;
    private String division;

    public Team(String id, String name, String l, String d) {
        roster = new ArrayList<>();
        teamValue = 0;
        teamId = id;
        teamName = name;
        league = l;
        division = d;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public void setTeamValue(double val) {
        teamValue = val;
    }

    public double getTeamValue() {
        return teamValue;
    }

    public void addPlayer(Player p) {
        roster.add(p);
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    @Override
    public String toString() {
        return teamId;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Team.class && ((Team) obj).getTeamId().equals(teamId);
    }
}
