package models;

import java.util.ArrayList;

public class Series {
    private Game game;
    private int numberOfPlays;

    public Series(Game game, int numberOfPlays) {
        this.game = game;
        this.numberOfPlays = numberOfPlays;
    }

    public Game getGame() {
        return game;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public ArrayList<GameResult> playSeries() {
        ArrayList<GameResult> results = new ArrayList<>();
        for (int i = 0; i < numberOfPlays; ++i) {
            results.add(game.play());
        }
        return results;
    }

    /**
     * @param g game to compare to
     * @return True if the same two teams are playing the series as in g
     */
    public boolean doesSeriesContainGame(Game g) {
        return g.sameTeams(game);
    }
}
