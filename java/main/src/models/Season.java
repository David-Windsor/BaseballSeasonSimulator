package models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author David Windsor
 * Created from the orignial Season class by Dan Jackson
 * Class to represent an MLB season. Since the way a season is built changes periodically this will build and represent
 * possible seasons from the 2013 - 2017 MLB seasons.
 */
public class Season {
    private HashMap<Team, Integer> wins;
    private HashMap<Team, Integer> gamesAppeared;
    private TeamList teamsForSeason;
    private Integer year;
    private ArrayList<Game> games;

    public Season(Integer year, ArrayList<Team> teams) {
        wins = new HashMap<>();
        gamesAppeared = new HashMap<>();
        teamsForSeason = new TeamList(teams);
        this.year = year;
        // games = buildSeason();
    }

    public Season(Integer year, ArrayList<Team> teams, ArrayList<Game> games) {
        wins = new HashMap<>();
        gamesAppeared = new HashMap<>();
        teamsForSeason = new TeamList(teams);
        this.games = games;
    }

    public HashMap<Team, Integer> getWins() {
        return wins;
    }

    public HashMap<Team, Integer> getGamesAppeared() {
        return gamesAppeared;
    }
    /**
     * @return Array of doubles of the amount of wins in the season. Used by the WinHistogram Class
     */
    public Double[] getWinNumbersAsDoubles() {
        return wins.values().stream().map(Double::new).toArray(Double[]::new);
    }

    /**
     * Plays out the season and stores all of its records into the wins HashMap. WILL OVERWRITE THE PREVIOUS RESULTS OF
     * playSeason()
     */
    public void playSeason() {
        ArrayList<GameResult> results;
        for (Team team : teamsForSeason.getTeams()) {
            gamesAppeared.put(team, 0);
            wins.put(team, 0);
        }
        for (Game game : games) {
            // Increment the games each team has appeared in by one
            gamesAppeared.put(game.getAway(), gamesAppeared.get(game.getAway()) + 1);
            gamesAppeared.put(game.getHome(), gamesAppeared.get(game.getHome()) + 1);
            //play the game and add one to the winner's total
            Team winner = game.play().getWinner();
            wins.put(winner, wins.get(winner) + 1);
        }

    }
//    /**
//     * @return ArrayList\<Series> for the Season to play out.
//     */
//    private ArrayList<Series> buildSeason() {
//        ArrayList<Series> seriesForSeason = new ArrayList<>();
//        // Build the games for same division and league
//        //Get all the intra division games built and added first since they have the highest amount that needs played
//        makeIntraDivisionSeries("AL", "W", seriesForSeason);
//        makeIntraDivisionSeries("AL", "C", seriesForSeason);
//        makeIntraDivisionSeries("AL", "E", seriesForSeason);
//        makeIntraDivisionSeries("NL", "W", seriesForSeason);
//        makeIntraDivisionSeries("NL", "C", seriesForSeason);
//        makeIntraDivisionSeries("NL", "E", seriesForSeason);
//
//        //Now we need to assign matches between each team in a league. 66 games should be played for this
//        //Being a combination of 6 and 7 games games. 6x7 games and 4x6 games.
//        // We don't need to add a games that already exists
//        makeIntraLeagueSeries("AL", seriesForSeason);
//        makeIntraLeagueSeries("NL", seriesForSeason);
//
//        makeInterLeagueSeries(seriesForSeason);
//        return seriesForSeason;
//    }
//
//    /**
//     *
//     * @param league League that the matches will be made for
//     * @param division Division the matches will be made for
//     * @param seriesList ArrayList<Series> that the games for the matches will be held in
//     */
//    private void makeIntraDivisionSeries(String league, String division, ArrayList<Series> seriesList) {
//        ArrayList<Team> leagueAndDivision = teamsForSeason.getTeamsByLeagueAndDivision(league, division);
//        // Now each team has four games with each other team, 19 games
//        for (int i = 0; i < leagueAndDivision.size(); ++i)
//            for (int j = i + 1; j < leagueAndDivision.size(); ++j)
//                seriesList.add(new Series(new Game(leagueAndDivision.get(i), leagueAndDivision.get(j)), 19));
//    }
//
//    /**
//     *
//     * @param league League that the Intraleague games will be made for
//     * @param seriesList ArrayList\<Series> of the Intraleague matches
//     */
//    private void makeIntraLeagueSeries(String league, ArrayList<Series> seriesList) {
//        ArrayList<Team> leagueTeams = teamsForSeason.getTeamsByLeague(league);
//        for (int i = 0; i < leagueTeams.size(); ++i) {
//            int gamesAdded = 0;
//            for (int j = i; j < leagueTeams.size(); ++j) {
//                boolean isAlreadyMade = false;
//                Game game = new Game(leagueTeams.get(i), leagueTeams.get(j));
//                for (Series s : seriesList) {
//                    if (s.doesSeriesContainGame(game)) {
//                        isAlreadyMade = true;
//                        break;
//                    }
//                }
//                if (!isAlreadyMade) {
//                    seriesList.add(new Series(game, gamesAdded % 10 <= 5 ? 7 : 6));
//                    ++gamesAdded;
//                }
//            }
//        }
//    }
//
//    /**
//     * @param seriesForSeason the ArrayList<Series> to insert the new games into
//     *                        Inter-league games are based on two divisions from different leagues playing each other
//     *                        So in 2013, AL-W vs NL-C, AL-E vs NL-W and AL-C vs NL-E that rotate yearly
//     */
//    //TODO SOME SEASONS RESULT IN THE CORRECT TOTAL NUMBER OF GAMES, BUT SOME TEAMS PLAY TOO MANY GAMES, FIX THAT
//    private void makeInterLeagueSeries(ArrayList<Series> seriesForSeason) {
//        String[] americanLeague = {"E", "C", "W"};
//        String[] nationalLeague = {"W", "E", "C"};
//        shiftLeagueMatchup(americanLeague, year);
//        HashMap<Game, Boolean> isGameMade = new HashMap<>();
//        // Create the 16 inter-league division matches for each team
//        for (int i = 0; i < 3; ++i) {
//            ArrayList<Team> americanTeams = teamsForSeason.getTeamsByLeagueAndDivision("AL", americanLeague[i]);
//            ArrayList<Team> nationalTeams = teamsForSeason.getTeamsByLeagueAndDivision("NL", nationalLeague[i]);
//            for (Team american : americanTeams) {
//                for (int j = 0; j < nationalTeams.size() - 1; ++j) {
//                    Game game = new Game(american, nationalTeams.get(j));
//                    seriesForSeason.add(new Series(game, 3));
//                    isGameMade.put(game, true);
//                }
//                Game game = new Game(american, nationalTeams.get(4));
//                seriesForSeason.add(new Series(game, 4));
//                isGameMade.put(game, true);
//            }
//        }
//        ArrayList<Team> americanTeams = teamsForSeason.getTeamsByLeague("AL");
//        ArrayList<Team> nationalTeams = teamsForSeason.getTeamsByLeague("NL");
//        HashMap<Team, Integer> appearanceMap = new HashMap<>();
//        for (Team team : nationalTeams)
//            appearanceMap.put(team, 0);
//        for (Team americanTeam : americanTeams) {
//            int gamesAdded = 0;
//            for (Team nationalTeam : nationalTeams) {
//                if (gamesAdded == 4)
//                    break;
//                Game game = new Game(americanTeam, nationalTeam);
//                if (!isGameMade.containsKey(game))
//                    if (appearanceMap.get(nationalTeam) < 4) {
//                        appearanceMap.put(nationalTeam, appearanceMap.get(nationalTeam) + 1);
//                        isGameMade.put(game, true);
//                        seriesForSeason.add(new Series(game, 1));
//                        ++gamesAdded;
//                    }
//            }
//        }
//    }
//
//    /**
//     * @param americanLeague Array of AL Divisions, handled in makeInterLeagueSeries
//     * @param year           Year of the season used to determine how many shifts are required. Year must be >= 2013 for this
//     *                       scheduling
//     */
//    private void shiftLeagueMatchup(String[] americanLeague, Integer year) {
//        // doing a left cyclical shift of the AL divisions is enough to get the proper matches,
//        int amountOfCycles = (year - 2013) % 3;
//        for (int i = 0; i < amountOfCycles; ++i) {
//            String temp = americanLeague[0];
//            americanLeague[0] = americanLeague[1];
//            americanLeague[1] = americanLeague[2];
//            americanLeague[2] = temp;
//        }
//    }
}
