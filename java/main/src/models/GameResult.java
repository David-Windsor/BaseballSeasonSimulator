package models;

/**
 * Simple holder for the winner of an unspecified game
 * @author Dan Jackson
 */
public class GameResult {
	private Team winner;


    GameResult(Team w) {

		winner = w;
	}

	Team getWinner() {
		return winner;
	}

    @Override
	public String toString() {
		return winner.getTeamName();
	}

}
