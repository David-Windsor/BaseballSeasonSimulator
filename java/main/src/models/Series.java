package models;

import java.util.ArrayList;

/**
 * @author David Windsor
 * Class to hold several games played between two teams.
 * To avoid memory issues with storing the same game multiple times, this class will know how many times a certain
 * match-up should be played and will handle returning and managing all results of the games played
 */
class Series {
    private Game game;
    private int numberOfPlays;

    Series(Game game, int numberOfPlays) {
        this.game = game;
        this.numberOfPlays = numberOfPlays;
    }

    Game getGame() {
        return game;
    }

    int getNumberOfPlays() {
        return numberOfPlays;
    }

    ArrayList<GameResult> playSeries() {
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
    boolean doesSeriesContainGame(Game g) {
        return g.sameTeams(game);
    }
}
