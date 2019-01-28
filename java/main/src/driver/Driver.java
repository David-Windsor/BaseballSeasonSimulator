package driver;

import database.TeamRequestHandler;
import graphics.WinHistogram;
import models.Season;
import models.Team;
import models.TeamList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
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

        Season season = new Season(2018, teams);
        System.out.println("Season made");
        season.playSeason();
        System.out.println("Season played");
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
