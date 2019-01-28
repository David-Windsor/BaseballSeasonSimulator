package models;

import database.GameRequestHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Season {

    ArrayList<Game> games;
    private HashMap<Team, Integer> wins;

    public Season(ArrayList<Team> teams) {
        games = new ArrayList<>();
        wins = new HashMap<>();
        buildList(teams);
    }

    /**
     * @param teams ArrayList of teams so the season can make a HashMap<String,Team> for access to objects
     * Fetches the list of games in string form and creates game objects
     */
    private void buildList(ArrayList<Team> teams) {
        GameRequestHandler handler = new GameRequestHandler();
        ArrayList<String> gameStrings = handler.getGames();
        HashMap<String, Team> teamMap = new HashMap<>();
        for (Team t : teams) {
            teamMap.put(t.toString(), t);
        }
        for (String s : gameStrings) {
            games.add(new Game(teamMap.get(s.substring(6, 9)), teamMap.get(s.substring(0, 3))));
        }
    }

    /**
     * 'Plays' each game and stores the results in a HashMap that keeps track of wins
     */
    public void playSeason() {
        ArrayList<GameResult> results = new ArrayList<>();
        for (Game g : games) {
            results.add(g.play());
        }
        for (GameResult result : results) {
            Team winner = result.getWinner();
            if (wins.containsKey(winner)) {
                wins.put(winner, wins.get(winner) + 1);
            } else {
                wins.put(winner, 1);
            }
        }

        for (Team t : wins.keySet()) {
            System.out.println(wins.get(t));
        }

    }

    /**
     * @return Array of doubles of the amount of wins in the season. Used by the WinHistogram Class
     */
    public Double[] getWinNumbersAsDoubles() {
        return wins.values().stream().map(Double::new).toArray(Double[]::new);
    }
}
