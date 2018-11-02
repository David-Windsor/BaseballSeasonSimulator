package driver;

import database.Database;
import models.Game;

public class Driver {

	public static void main(String[] args) {
		// Build database.Database and Populate Players based off of past three seasons
		// Build teams based on the players stats and their most recent teams if played
		// in 2017-2018 season

		//TODO MAKE PACKAGE PRIVATE AFTER HANDLERS ARE MADE
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

}
