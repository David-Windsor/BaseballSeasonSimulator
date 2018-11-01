package models;

import java.util.Random;

/**
 * This class houses all the information about a particular game
 * More information will be stored here soon
 *
 */

public class Game {

	Team home;
	Team away;

	public Game(Team H, Team A) {
		home = H;
		away = A;
	}

	public GameResult getResult() {
		Random r = new Random();
		double n = r.nextDouble()*(home.getTeamValue()+away.getTeamValue());
		if(n < home.getTeamValue()) {
			return new GameResult(home);
		}else if( n >= home.getTeamValue()) {
			return new GameResult(away);
		}
		return null;
	}

}
