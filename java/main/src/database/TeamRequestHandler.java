package database;

import com.sun.istack.internal.NotNull;
import models.Team;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler that all handles all requests to the database regarding teams
 *
 * @author David Windsor
 */
public class TeamRequestHandler {
    private static HashMap<Integer, ArrayList<Team>> teamListMap = new HashMap<>();

    public TeamRequestHandler() {
    }

    @NotNull
    public ArrayList<Team> getTeamsForYear(Integer year) {
        // Check to see if the team has already been loaded and if so just return a copy
        if (teamListMap.get(year) != null) {
            return teamListMap.get(year);
        }
        //if it isn't we will insert it ourselves. Default to ArrayList
        else {
            teamListMap.put(year, new ArrayList<>());
            fetchTeamsForYear(year);
            return teamListMap.get(year);
        }
    }

    private void fetchTeamsForYear(Integer year) {
        ArrayList<Team> teams = Database.getTeamsForYear(year);
        teamListMap.put(year, teams);
    }


}
