package driver;
import database.Database;
import models.Season;

import java.util.HashMap;

public class Driver {

	public static void main(String[] args) {

        // Build Database and Populate Players based off of past three seasons
        // Build teams based on the players stats and their most recent teams if played
        // in 2017-2018 season
        Database db = new Database();
        db.buildDatabase();
        HashMap<String, Integer> totalWins = new HashMap<>();
        for (String teamid : db.getTeamIDs()) {
            totalWins.put(teamid, 0);
        }
        for (int i = 0; i < 1; i++) {
            Season s = new Season(db);
            for (String teamid : db.getTeamIDs()) {
                //System.out.println(teamid + ": " + s.getWins().get(db.getTeam(teamid)));
                int temp = totalWins.get(teamid);
                temp += s.getWins().get(db.getTeam(teamid));
                totalWins.remove(teamid);
                totalWins.put(teamid, temp);
            }
            System.out.println(i);
        }

        for (String teamid : db.getTeamIDs()) {
            System.out.println(teamid + ": " + totalWins.get(teamid) + " Wins");
        }


    }

}
