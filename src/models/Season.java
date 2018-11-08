package models;

import database.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class Season {
    private List<Team> teams;
    private HashMap<Team, Integer> wins;
    private Database db;

    public Season(Database d) {
        db = d;
        wins = new HashMap<>();
        for (String s : db.getTeamIDs()) {
            //System.out.println("WORKING ON TEAM: " + db.getTeam(s));
            wins.put(db.getTeam(s), 0);
            buildSeasonForTeam(db.getTeam(s));
        }
    }

    public HashMap<Team, Integer> getWins() {
        return wins;
    }

    public Integer[] getWinNumbers() {
        return wins.values().stream().map(Integer::new).toArray(Integer[]::new);
    }

    public Double[] getWinNumbersAsDoubles() {
        return wins.values().stream().map(Double::new).toArray(Double[]::new);
    }

    private void buildSeasonForTeam(Team t) {
        Random r = new Random();
        if (t.getLeague().equals("AL")) {
            switch (t.getDivision()) {
                case "E": {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'AL' AND division_id = 'E'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();

                    break;
                }
                case "C": {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'AL' AND division_id = 'C'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();
                    break;
                }
                default: {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'AL' AND division_id = 'W'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();
                    break;
                }
            }

        } else {
            switch (t.getDivision()) {
                case "E": {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'NL' AND division_id = 'E'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();

                    break;
                }
                case "C": {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'NL' AND division_id = 'C'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();
                    break;
                }
                default: {
                    ArrayList<Team> playableTeams = db
                            .getTeams("SELECT team_id FROM Team WHERE league_id = 'NL' AND division_id = 'W'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 74; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'NL'");
                    playableTeams.remove(t);
                    for (int i = 0; i < 67; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams = db.getTeams("SELECT team_id FROM Team WHERE league_id = 'AL'");
                    for (int i = 0; i < 21; i++) {
                        Game g = new Game(t, playableTeams.get(r.nextInt(playableTeams.size())));
                        if (g.getResult().winner == t) {
                            int temp = wins.get(t);
                            wins.remove(t);
                            wins.put(t, temp + 1);
                        }
                    }
                    playableTeams.clear();
                    break;
                }
            }
        }
    }
}
