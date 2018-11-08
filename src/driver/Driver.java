package driver;

import database.TeamRequestHandler;
import graphics.WinHistogram;
import models.Season;
import models.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

	public static void main(String[] args) {

        // Build Database and Populate Players based off of past three seasons
        // Build teams based on the players stats and their most recent teams if played
        // in 2017-2018 season
        TeamRequestHandler teamHandler = new TeamRequestHandler();
        ArrayList<Team> teams = teamHandler.getTeamsForYear(2017);

        HashMap<String, Integer> totalWins = new HashMap<>();
        for (Team team : teams) {
            totalWins.put(team.getTeamId(), 0);

        }
        for (int i = 0; i < 10; i++) {
            Season s = new Season(2017);
            for (String teamId : db.getTeamIDs()) {
                //System.out.println(teamId + ": " + s.getWins().get(db.getTeam(teamId)));
                int temp = totalWins.get(teamId);
                temp += s.getWins().get(db.getTeam(teamId));
                totalWins.remove(teamId);
                totalWins.put(teamId, temp);
            }
            System.out.println(i);
            WinHistogram winHistogram = new WinHistogram("Wins", s, 8);
            winHistogram.pack();
            winHistogram.setVisible(true);
        }

        for (String teamId : db.getTeamIDs()) {
            System.out.println(teamId + ": " + totalWins.get(teamId) + " Wins");
        }


    }

}
