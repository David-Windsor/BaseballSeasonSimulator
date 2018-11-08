package models;

import database.TeamRequestHandler;

import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;

    public TeamList(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public TeamList(Integer year) {
        teams = new TeamRequestHandler().getTeamsForYear(year);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Team> getTeamsByLeague(String league) {
        ArrayList<Team> teamsInLeague = new ArrayList<>();
        for (Team team : teams) {
            if (team.getLeague().equals(league))
                teamsInLeague.add(team);
        }
        return teamsInLeague;
    }

    public ArrayList<Team> getTeamsByLeagueAndDivision(String league, String division) {
        ArrayList<Team> teamsInLeague = getTeamsByLeague(league);
        ArrayList<Team> leagueAndDivisionList = new ArrayList<>();
        for (Team team : teamsInLeague) {
            if (team.getDivision().equals(division))
                leagueAndDivisionList.add(team);
        }
        return leagueAndDivisionList;
    }
}
