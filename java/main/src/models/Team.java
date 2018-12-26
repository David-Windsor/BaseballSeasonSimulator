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

    public Team() {
    }
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

    public List<Player> getRoster() {
        return roster;
    }

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
