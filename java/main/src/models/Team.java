package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a Team in the MLB. Will be expanded on as much as needed for the granularity of the simulation
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "TEAMS")
public class Team {
    @Id
    @Column(name = "id")
    private String teamId;
    @ManyToMany
    private List<Player> roster;
    @Column(name = "batting_average")
    private int battingAverage;
    @Column(name = "team_name")
    private String teamName;
    private String league;
    private String division;
    private int year;
    @OneToOne(cascade = CascadeType.ALL)
    private ResultGenerator blackboard;
    public Team() {
        teamId = teamName = league = division = "";
        year = 2018;
        roster = new ArrayList<>();
        blackboard = null;
        battingAverage = 0;
    }

    public Team(String id, String name, String leagueId, String divisionId, int battingAverage) {
        roster = new ArrayList<>();
        this.battingAverage = battingAverage;
        teamId = id;
        teamName = name;
        league = leagueId;
        division = divisionId;
    }


    public Team(String id, String name, String leagueId, String divisionId, int battingAverage, ResultGenerator bboard) {
        roster = new ArrayList<>();
        this.battingAverage = battingAverage;
        teamId = id;
        teamName = name;
        league = leagueId;
        division = divisionId;
        blackboard = bboard;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTeamId() {
        return teamId;
    }

    //TODO REMOVE AFTER FIX
    public void setTeamId(String teamId) {
        this.teamId = teamId;
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

    public ResultGenerator getBlackboard() {
        return blackboard;
    }

    public void setBlackboard(ResultGenerator blackboard) {
        this.blackboard = blackboard;
    }

    int getBattingAverage() {
        return battingAverage;
    }

    public List<Player> getRoster() {
        return roster;
    }

    //TODO remove this once testing and the initializing process starts
    public void addPlayer(Player p) {
        roster.add(p);
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
