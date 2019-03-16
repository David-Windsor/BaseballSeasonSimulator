package driver;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver {

    private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
    public static void main(String[] args) throws IOException {
        if (args != null && args.length > 0) {
            switch (args[0]) {
                case "-c":
                    createDatabase();
                    break;
                default:
                    break;
            }
        }
        //Keep this here, session factories are expensive to make so we want to able to keep just one at all times
        Session session = sf.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Team> criteria = builder.createQuery(Team.class);

        // UPDATED: Specify criteria root
        criteria.from(Team.class);
        // UPDATED: Execute query
        List<Team> ts = session.createQuery(criteria).getResultList();
        System.out.println("Teams gotten");
        ArrayList<Team> teams = new ArrayList<>(ts);

        HashMap<String, Team> teamHashMap = new HashMap<>();
        for (Team t : teams) {
            teamHashMap.put(t.getTeamId(), t);
        }
        ArrayList<Game> games = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("./external_resources/schedule_19.csv"));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] strings = currentLine.split(",");
            for (int i = 0; i < strings.length - 1; i += 2) {
                Team away = teamHashMap.get(strings[i]);
                Team home = teamHashMap.get(strings[i + 1]);
                Game game = new Game(home, away);
                games.add(game);
            }

        }
        Season season = new Season(2019, teams, games);
        season.playSeason();
        HashMap<Team, Integer> wins = season.getWins();
        HashMap<Team, Integer> gamesAppeared = season.getGamesAppeared();
        for (Team t : wins.keySet()) {
            System.out.println(t.getTeamId() + " : " + wins.get(t) + " : " + gamesAppeared.get(t));
        }
        HashMap<Team, Double> averages = new HashMap<>();
        for (Team t : wins.keySet()) {
            averages.put(t, 0.0);
        }
        //do it 50 times and average the results
        for (int i = 0; i < 50; ++i) {
            season.playSeason();
            wins = season.getWins();
            for (Team t : wins.keySet()) {
                averages.put(t, averages.get(t) + wins.get(t));
            }
        }
        for (Team t : averages.keySet()) {
            averages.put(t, averages.get(t) / 50);
        }
        for (Team t : averages.keySet()) {
            System.out.println(t.getTeamId() + "," + averages.get(t));
        }
//        WinHistogram winHistogram = new WinHistogram("Wins for season", season, 12);
//            winHistogram.pack();
//            winHistogram.setVisible(true);
    }

    private static void createDatabase() throws IOException {
        Session session = sf.openSession();
        session.beginTransaction();
        BufferedReader reader = new BufferedReader(new FileReader("./external_resources/teamstuff.txt"));
        String currentLine;
        // Make teams
        while ((currentLine = reader.readLine()) != null) {
            String[] split = currentLine.split("\\s+");
            Team team = new Team();
            team.setTeamId(split[0]);
            team.setLeague(split[1]);
            team.setDivision(split[2]);
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 3; i < split.length; ++i) {
                numbers.add(Integer.parseInt(split[i]));
            }
            team.setBlackboard(new ResultGenerator(numbers));

            //We should add each player into the teams now
            String file = team.getTeamId().toLowerCase() + "_18.csv";
            BufferedReader playerReader = new BufferedReader(new FileReader("./external_resources/" + file));
            //read the first line to get rid of it
            String playerLine = playerReader.readLine();
            while ((playerLine = playerReader.readLine()) != null) {
                //split the line on commas
                String[] pSplit = playerLine.split(",");
                //remove the possible #,* and other characters from names
                pSplit[2] = pSplit[2].replaceAll("[*#]", "");
                Player player = new Player();
                player.setName(pSplit[2]);
                player.setAtBats(Integer.parseInt(pSplit[6]));
                String[] name = pSplit[2].split("\\s+");
                //TODO change this 18 to a more general approach for year if we simulate other years
                player.setPlayerID(name[0].substring(0, 2) + name[1] + 18);
                int hr = Integer.parseInt(pSplit[11]);
                int t = Integer.parseInt(pSplit[10]);
                int d = Integer.parseInt(pSplit[9]);
                int s = Integer.parseInt(pSplit[8]) - t - d - hr;
                int outs = player.getAtBats() - s - d - t - hr;
                ArrayList<Integer> pNumbers = new ArrayList<>();
                pNumbers.add(outs);
                pNumbers.add(s);
                pNumbers.add(d);
                pNumbers.add(t);
                pNumbers.add(hr);
                ResultGenerator r = new ResultGenerator(pNumbers);
                player.setResultGenerator(r);
                session.save(player);
                team.addPlayer(player);
            }
            System.out.println("Adding " + team.getTeamId());
            session.save(team);
            System.out.println("\nSAVED TEAM: " + team.getTeamId());
        }
        session.getTransaction().commit();
        session.close();
    }
}
