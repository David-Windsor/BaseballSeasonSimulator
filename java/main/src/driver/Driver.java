package driver;

import database.TeamRequestHandler;
import models.Game;
import models.Season;
import models.Team;
import models.TeamList;
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

    public static void main(String[] args) throws IOException {
        //Keep this here, session factories are expensive to make so we want to able to keep just one at all times
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
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


//        //TODO THIS IS JUST FOR TESTING RIGHT NOW, ONCE IT IS MADE THE OPTION WONT BE NEEDED MUCH
//        if (args.length > 0 && args[0].equals("--create")) {
//            if (!createDatabase(sf))
//                System.exit(10);
//        }
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
        //do it 20 times and average the results
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

    private static boolean createDatabase(SessionFactory sessionFactory) {
        //Get the team list
        TeamRequestHandler teamHandler = new TeamRequestHandler();
        TeamList teamList = new TeamList(teamHandler.getTeamsForYear(2017));
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Team team : teamList.getTeams()) {
            team.setYear(2017);
            session.save(team);
        }
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
