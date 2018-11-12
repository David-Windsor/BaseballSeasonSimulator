package models;

import java.util.Random;

/**
 * This class houses all the information about a particular game
 * More information will be stored here soon
 *
 */

public class Game {

    private Team home;
    private Team away;

	public Game(Team H, Team A) {
		home = H;
		away = A;
	}

	public GameResult play() {
		Random r = new Random();
		double n = r.nextDouble()*(home.getTeamValue()+away.getTeamValue());
		if(n < home.getTeamValue()) {
			return new GameResult(home);
		}else if( n >= home.getTeamValue()) {
			return new GameResult(away);
		}
		return null;
	}

	public Team getHome() {
		return home;
	}

	public Team getAway() {
		return away;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass() == Game.class && ((Game) obj).getHome().equals(home) && ((Game) obj).getAway().equals(away);
	}

	public boolean sameTeams(Game g) {
		return (home.equals(g.away) || home.equals(g.home)) && (away.equals(g.away) || away.equals(g.home));
	}
}
