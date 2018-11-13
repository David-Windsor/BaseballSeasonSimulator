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

        Season season = new Season(2017);
        season.playSeason();

        WinHistogram winHistogram = new WinHistogram("Wins for season", season, 12);
            winHistogram.pack();
            winHistogram.setVisible(true);
    }

}
