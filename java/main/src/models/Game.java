package models;

import java.util.ArrayList;

/**
 * @author David Windsor
 * @author Dan Jackson
 */
public class Game {
    private Team home;
    private Team away;


    public Game(Team homeTeam, Team awayTeam) {
        home = homeTeam;
        away = awayTeam;
    }

    GameResult play() {
        int homeScore = 0;
        int awayScore = 0;
        ArrayList<Player> homeCard = home.getBattingCard();
        ArrayList<Player> awayCard = away.getBattingCard();
        //TODO make this a try catch
        assert awayCard.size() == 9;
        assert homeCard.size() == 9;
        boolean[] bases = new boolean[4];
        bases[0] = true;
        int lineupSpot = 0;
        for (int i = 0; i < 9; ++i) {
            int outs = 0;
            while (outs < 3) {
                int result = homeCard.get(lineupSpot).getResultGenerator().roll();
                switch (result) {
                    case 0:
                        ++outs;
                        break;
                    case 1:
                        //advance to home plate
                        if (bases[3]) {
                            ++homeScore;
                            bases[3] = false;
                        }
                        //advance to third
                        if (bases[2]) {
                            bases[3] = true;
                            bases[2] = false;
                        }
                        //advance to second
                        if (bases[1]) {
                            bases[2] = true;
                            bases[1] = false;
                        }
                        bases[1] = true;
                        break;
                    case 2:
                        // 2nd and 3rd score
                        if (bases[3]) {
                            bases[3] = false;
                            ++homeScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++homeScore;
                        }
                        //first to third
                        if (bases[1]) {
                            bases[3] = true;
                            bases[1] = false;
                        }
                        bases[2] = true;
                        break;
                    case 3:
                        // first, second and third score
                        if (bases[3]) {
                            bases[3] = false;
                            ++homeScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++homeScore;
                        }
                        if (bases[1]) {
                            bases[1] = false;
                            ++homeScore;
                        }
                        bases[3] = true;
                        break;
                    case 4:
                        //same as above but we also score bases[0]
                        if (bases[3]) {
                            bases[3] = false;
                            ++homeScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++homeScore;
                        }
                        if (bases[1]) {
                            bases[1] = false;
                            ++homeScore;
                        }
                        ++homeScore;
                        break;
                }
                lineupSpot = ++lineupSpot == 9 ? 0 : lineupSpot;
            }

        }
        for (int i = 0; i < 9; ++i) {
            int outs = 0;
            while (outs < 3) {
                int result = awayCard.get(lineupSpot).getResultGenerator().roll();
                switch (result) {
                    case 0:
                        ++outs;
                        break;
                    case 1:
                        //advance to home plate
                        if (bases[3]) {
                            ++awayScore;
                            bases[3] = false;
                        }
                        //advance to third
                        if (bases[2]) {
                            bases[3] = true;
                            bases[2] = false;
                        }
                        //advance to second
                        if (bases[1]) {
                            bases[2] = true;
                            bases[1] = false;
                        }
                        bases[1] = true;
                        break;
                    case 2:
                        // 2nd and 3rd score
                        if (bases[3]) {
                            bases[3] = false;
                            ++awayScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++awayScore;
                        }
                        //first to third
                        if (bases[1]) {
                            bases[3] = true;
                            bases[1] = false;
                        }
                        bases[2] = true;
                        break;
                    case 3:
                        // first, second and third score
                        if (bases[3]) {
                            bases[3] = false;
                            ++awayScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++awayScore;
                        }
                        if (bases[1]) {
                            bases[1] = false;
                            ++awayScore;
                        }
                        bases[3] = true;
                        break;
                    case 4:
                        //same as above but we also score bases[0]
                        if (bases[3]) {
                            bases[3] = false;
                            ++awayScore;
                        }
                        if (bases[2]) {
                            bases[2] = false;
                            ++awayScore;
                        }
                        if (bases[1]) {
                            bases[1] = false;
                            ++awayScore;
                        }
                        ++awayScore;
                        break;
                }
                lineupSpot = ++lineupSpot == 9 ? 0 : lineupSpot;
            }
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
