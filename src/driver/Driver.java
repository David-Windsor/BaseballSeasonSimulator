package driver;

import database.Database;
import models.Game;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Driver {

	public static void main(String[] args) {
		// Build database.Database and Populate Players based off of past three seasons
		// Build teams based on the players stats and their most recent teams if played
		// in 2017-2018 season
		Database db = new Database();
		db.buildDatabase();
		Game g1 = new Game(db.getTeam("PIT"), db.getTeam("PHI"));
		for (int j = 0; j < 100; j++) {

			int pit = 0, phi = 0;
			for (int i = 0; i < 100000; i++) {
				if (g1.getResult().winner.getTeamId().equals("PIT")) {
					pit++;
				} else {
					phi++;
				}
			}
			System.out.println(pit + "|" + phi);
		}

	}

	/**
	 * Generates the result of a game based on the contents of the game
	 * change
	 * @param models.Game
	 *
	 * @return A game Result that contains the winner This obviously needs some help
	 *         because we can't compare teams yet.
	 */

}
