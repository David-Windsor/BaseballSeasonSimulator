package models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DataEntryCrapEverythingNeedsToBeAClass {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Team> criteria = builder.createQuery(Team.class);

        // UPDATED: Specify criteria root
        criteria.from(Team.class);
        // UPDATED: Execute query
        List<Team> teams = session.createQuery(criteria).getResultList();
        Team team = null;
        for (Team t : teams) {
            System.out.println(t.getTeamId() + " " + t.getLeague() + " " + t.getDivision());
        }

//        BufferedReader reader = new BufferedReader(new FileReader("./external_resources/teamstuff.txt"));
//        String currentLine;
//
//        while((currentLine = reader.readLine()) != null) {
//            String[] split = currentLine.split("\\s+");
//            Team team = new Team();
//            team.setTeamId(split[0]);
//            team.setLeague(split[1]);
//            team.setDivision(split[2]);
//            ArrayList<Integer> numbers = new ArrayList<>();
//            for(int i = 3; i < split.length; ++i) {
//                numbers.add(Integer.parseInt(split[i]));
//            }
//            team.setBlackboard(new TeamBattingBlackboard(numbers));
//            System.out.println("Adding " + team.getTeamId());
//            session.save(team);
//        }
//        session.getTransaction().commit();
        session.close();
        System.exit(0);

    }

}
