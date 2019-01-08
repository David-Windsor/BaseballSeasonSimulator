package driver;

import database.TeamRequestHandler;
import graphics.WinHistogram;
import models.Season;
import models.Team;
import models.TeamList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
        //Keep this here, session factories are expensive to make so we want to able to keep just one at all times
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        //TODO THIS IS JUST FOR TESTING RIGHT NOW, ONCE IT IS MADE THE OPTION WONT BE NEEDED MUCH
        if (args.length > 0 && args[0].equals("--create")) {
            if (!createDatabase(sessionFactory))
                System.exit(10);
        }
        //Ask what year we are simulating
        Scanner kbd = new Scanner(System.in);
        System.out.println("What year would you like to simulate? Hint, use 2017");
        int simulatedYear = kbd.nextInt();

        Session session = sessionFactory.openSession();
        CriteriaQuery<Team> query = session.getCriteriaBuilder().createQuery(Team.class);
        Root<Team> root = query.from(Team.class);
        query.select(root);
        ArrayList<Team> teams = new ArrayList<>(session.createQuery(query).list());
        HashMap<String, Integer> totalWins = new HashMap<>();
        for (Team team : teams) {
            totalWins.put(team.getTeamId(), 0);

        }

        Season season = new Season(2017, teams);
        season.playSeason();

        WinHistogram winHistogram = new WinHistogram("Wins for season", season, 12);
            winHistogram.pack();
            winHistogram.setVisible(true);
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
