package models;

import com.sun.istack.internal.NotNull;
import database.TeamRequestHandler;

import java.util.ArrayList;

/**
 * Class available to all other classes to manage a given list of Team. Typically this class should be given a year or
 * the complete team list for said year and be used to query teams based on league and possibly division
 *
 * @author David Windsor
 * @version 1
 */
@SuppressWarnings("unused")
public class TeamList {
    private ArrayList<Team> teams;

    public TeamList(ArrayList<Team> teams) {
        this.teams = teams;
    }

    TeamList(Integer year) {
        teams = new TeamRequestHandler().getTeamsForYear(year);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * @param league league to be used as the search, currently only "AL" and "NL" are supported
     * @return ArrayList\<Team\> of all the teams belonging to the league out of all the teams in the TeamList
     */
    @NotNull
    public ArrayList<Team> getTeamsByLeague(String league) {
        ArrayList<Team> teamsInLeague = new ArrayList<>();
        for (Team team : teams) {
            if (team.getLeague().equals(league))
                teamsInLeague.add(team);
        }
        return teamsInLeague;
    }

    /**
     * @param league   league to be used as a search key, currently only "AL" and "NL" are supported
     * @param division division to be used as a search key, currently only "W" "C" and "E" are supported
     * @return ArrayList\<Team\> of all the teams belonging to the league and division out of all the teams in the
     * TeamList
     */
    ArrayList<Team> getTeamsByLeagueAndDivision(String league, String division) {
        ArrayList<Team> teamsInLeague = getTeamsByLeague(league);
        ArrayList<Team> leagueAndDivisionList = new ArrayList<>();
        for (Team team : teamsInLeague) {
            if (team.getDivision().equals(division))
                leagueAndDivisionList.add(team);
        }
        return leagueAndDivisionList;
    }
}
