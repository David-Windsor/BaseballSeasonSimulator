package models;

/**
 * A simple class that holds the results of a game
 * This can be used to update the database or just to keep track of stats for the season
 *
 */
public class GameResult {
	public Team winner;

    public  GameResult(Team w) {
		winner = w;
	}
	
	public Team getWinner() {
		return winner;
	}

    @Override
	public String toString() {
		return winner.getTeamName();
	}

}
