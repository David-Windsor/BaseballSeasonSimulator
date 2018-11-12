package models;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Season {
    private HashMap<Team, Integer> wins;
    private TeamList teamsForSeason;
    private Integer year;
    private ArrayList<Series> series;

    public Season(Integer year) {
        wins = new HashMap<>();
        teamsForSeason = new TeamList(year);
        this.year = year;
        series = buildSeason(year);
    }

    public HashMap<Team, Integer> getWins() {
        return wins;
    }

    public Integer[] getWinNumbersAsInts() {
        return wins.values().stream().map(Integer::new).toArray(Integer[]::new);
    }

    public Double[] getWinNumbersAsDoubles() {
        return wins.values().stream().map(Double::new).toArray(Double[]::new);
    }

    public void playSeason() {
        ArrayList<GameResult> results;
        for (Series s : series) {
            results = s.playSeries();
            for (GameResult result : results) {
                Team winner = result.getWinner();
                if (wins.containsKey(winner)) {
                    wins.put(winner, wins.get(winner) + 1);
                } else {
                    wins.put(winner, 1);
                }
            }

        }

    }

    private ArrayList<Series> buildSeason(Integer year) {
        ArrayList<Series> seriesForSeason = new ArrayList<>();
        // Build the series for same division and league
        //Get all the intra division games built and added first since they have the highest amount that needs played
        makeIntraDivisionSeries("AL", "W", seriesForSeason);
        makeIntraDivisionSeries("AL", "C", seriesForSeason);
        makeIntraDivisionSeries("AL", "E", seriesForSeason);
        makeIntraDivisionSeries("NL", "W", seriesForSeason);
        makeIntraDivisionSeries("NL", "C", seriesForSeason);
        makeIntraDivisionSeries("NL", "E", seriesForSeason);

        //Now we need to assign matches between each team in a league. 66 games should be played for this
        //Being a combination of 6 and 7 games series. 6x7 series and 4x6 series.
        // We don't need to add a series that already exists
        makeIntraLeagueSeries("AL", seriesForSeason);
        makeIntraLeagueSeries("NL", seriesForSeason);

        makeInterLeagueSeries(seriesForSeason);
        return seriesForSeason;
    }

    private void makeIntraDivisionSeries(String league, String division, ArrayList<Series> seriesList) {
        ArrayList<Team> leagueAndDivision = teamsForSeason.getTeamsByLeagueAndDivision(league, division);
        // Now each team has four series with each other team, 19 games
        for (int i = 0; i < leagueAndDivision.size(); ++i)
            for (int j = i + 1; j < leagueAndDivision.size(); ++j)
                seriesList.add(new Series(new Game(leagueAndDivision.get(i), leagueAndDivision.get(j)), 19));
    }

    private void makeIntraLeagueSeries(String league, ArrayList<Series> seriesList) {
        ArrayList<Team> leagueTeams = teamsForSeason.getTeamsByLeague(league);
        for (int i = 0; i < leagueTeams.size(); ++i) {
            int gamesAdded = 0;
            for (int j = i; j < leagueTeams.size(); ++j) {
                boolean isAlreadyMade = false;
                Game game = new Game(leagueTeams.get(i), leagueTeams.get(j));
                for (Series s : seriesList) {
                    if (s.doesSeriesContainGame(game)) {
                        isAlreadyMade = true;
                        break;
                    }
                }
                if (!isAlreadyMade) {
                    seriesList.add(new Series(game, gamesAdded % 10 <= 5 ? 7 : 6));
                    ++gamesAdded;
                }
            }
        }
    }

    /**
     * @param seriesForSeason the ArrayList<Series> to insert the new series into
     *                        Inter-league games are based on two divisions from different leagues playing each other
     *                        So in 2013, AL-W vs NL-C, AL-E vs NL-W and AL-C vs NL-E that rotate yearly
     */
    private void makeInterLeagueSeries(ArrayList<Series> seriesForSeason) {
        String[] americanLeague = {"E", "C", "W"};
        String[] nationalLeague = {"W", "E", "C"};
        shiftLeagueMatchup(americanLeague, year);
        // Create the 16 inter-league division matches for each team
        for (int i = 0; i < 3; ++i) {
            ArrayList<Team> americanTeams = teamsForSeason.getTeamsByLeagueAndDivision("AL", americanLeague[i]);
            ArrayList<Team> nationalTeams = teamsForSeason.getTeamsByLeagueAndDivision("NL", nationalLeague[i]);
            for (Team american : americanTeams) {
                for (int j = 0; j < nationalTeams.size() - 1; ++j) {
                    seriesForSeason.add(new Series(new Game(american, nationalTeams.get(j)), 3));
                }
                seriesForSeason.add(new Series(new Game(american, nationalTeams.get(4)), 4));
            }
        }
        HashMap<Team, Integer> appearanceMap = new HashMap<>();
        // Now we need those teams to have 4 games overall against teams not in that divisional match up
        for (int i = 0; i < americanLeague.length; ++i) {
            // Take each AL division and figure out their other inter-league matches
            ArrayList<Team> american = teamsForSeason.getTeamsByLeagueAndDivision("AL", americanLeague[i]);
            ArrayList<Team> national = teamsForSeason.getTeamsByLeague("NL");
            national.removeAll(teamsForSeason.getTeamsByLeagueAndDivision("NL", nationalLeague[i]));
            for (Team team : american) {
                Random rng = new Random(ZonedDateTime.now().toEpochSecond());
                for (int m = 0; m < 4; ++m) {
                    Team nationalTeam = national.get(rng.nextInt(national.size()));
                    //If the national team appears in the map we need to check if it's had its 4 games made already
                    if (appearanceMap.containsKey(nationalTeam)) {
                        //if it does then we need to find a team that isn't
                        while (!(appearanceMap.get(nationalTeam) < 4))
                            nationalTeam = national.get(rng.nextInt(national.size()));

                        appearanceMap.put(nationalTeam, appearanceMap.get(nationalTeam) + 1);
                        appearanceMap.put(team, i + 1);
                        seriesForSeason.add(new Series(new Game(team, nationalTeam), 1));

                    } else {
                        appearanceMap.put(nationalTeam, 1);
                        appearanceMap.put(team, i + 1);
                        seriesForSeason.add(new Series(new Game(team, nationalTeam), 1));
                    }
                }
            }
        }
    }

    /**
     * @param americanLeague Array of AL Divisions, handled in makeInterLeagueSeries
     * @param year           Year of the season used to determine how many shifts are required. Year must be >= 2013 for this
     *                       scheduling
     */
    private void shiftLeagueMatchup(String[] americanLeague, Integer year) {
        // doing a left cyclical shift of the AL divisions is enough to get the proper matches,
        int amountOfCycles = (year - 2013) % 3;
        for (int i = 0; i < amountOfCycles; ++i) {
            String temp = americanLeague[0];
            americanLeague[0] = americanLeague[1];
            americanLeague[1] = americanLeague[2];
            americanLeague[2] = temp;
        }
    }
}
