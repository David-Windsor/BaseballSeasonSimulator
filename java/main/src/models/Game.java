package models;

import java.time.ZonedDateTime;
import java.util.Random;

/**
 * @author David Windsor
 * @author Dan Jackson
 *
 */

public class Game {

    private Team home;
    private Team away;

	Game(Team H, Team A) {
		home = H;
		away = A;
	}

	GameResult play() {
		Random rng = new Random(ZonedDateTime.now().toEpochSecond());
		int winner = rng.nextInt(home.getBattingAverage() + away.getBattingAverage());
		return new GameResult(winner < home.getBattingAverage() ? home : away);
	}

	Team getHome() {
		return home;
	}

	Team getAway() {
		return away;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass() == Game.class && sameTeams((Game) obj);
	}

	boolean sameTeams(Game g) {
		return (home.equals(g.away) || home.equals(g.home)) && (away.equals(g.away) || away.equals(g.home));
	}
}
