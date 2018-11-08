package models;

import database.TeamRequestHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class AlternativeSeason {
    private TeamRequestHandler requestHandler;
    private HashMap<Team, Integer> wins;

    public AlternativeSeason(Integer year) {
        wins = new HashMap<>();
        requestHandler = new TeamRequestHandler();
    }

    public HashMap<Team, Integer> getWins() {
        return wins;
    }

    public Integer[] getWinNumbers() {
        return wins.values().stream().map(Integer::new).toArray(Integer[]::new);
    }

    public Double[] getWinNumbersAsDoubles() {
        return wins.values().stream().map(Double::new).toArray(Double[]::new);
    }

    private ArrayList<Game> buildSeason(Integer year) {
        ArrayList<Series> seriesForSeason = new ArrayList<>();
        // Build the series for same division and league
        // We will start with American League Series


    }
}
