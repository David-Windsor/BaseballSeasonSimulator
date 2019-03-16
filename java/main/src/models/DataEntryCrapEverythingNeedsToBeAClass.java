package models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataEntryCrapEverythingNeedsToBeAClass {
    public static void main(String[] args) throws IOException {

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
        for (Team t : teams) {
            System.out.println(t.getTeamId());
        }



        BufferedReader reader = new BufferedReader(new FileReader("./external_resources/teamstuff.txt"));
        String currentLine;

//        while((currentLine = reader.readLine()) != null) {
//            String[] strings = currentLine.split(",");
//            for(int i = 0; i < strings.length; i += 3) {
//                Team away = teamHashMap.get(strings[i]);
//                Team home = teamHashMap.get(strings[i+2]);
//                Game game = new Game(home, away);
//            }
//
//        }


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
            System.out.println("Adding " + team.getTeamId());
            session.save(team);
        }
        session.getTransaction().commit();
        session.close();
        System.exit(0);

    }

}
