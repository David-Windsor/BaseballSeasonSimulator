package models;

/**
 * @author David Windsor
 * @author Dan Jackson
 *
 */
public class Game {
    private Team home;
    private Team away;


	Game(Team homeTeam, Team awayTeam) {
		home = homeTeam;
		away = awayTeam;
	}

	GameResult play() {
		int homeScore = 0;
		int awayScore = 0;
		for (int i = 0; i < 9; ++i) {
			homeScore += home.getBlackboard().roll();
			awayScore += away.getBlackboard().roll();
		}
		//overtime innings
		while (awayScore == homeScore) {
			homeScore += home.getBlackboard().roll();
			awayScore += away.getBlackboard().roll();
		}
		return homeScore > awayScore ? new GameResult(home) : new GameResult(away);
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
