import models.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class HibernateTest {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaQuery<Team> query = session.getCriteriaBuilder().createQuery(Team.class);
        Root<Team> root = query.from(Team.class);
        query.select(root);
        ArrayList<Team> teams = new ArrayList<>(session.createQuery(query).list());
        for (Team team : teams)
            System.out.println(team);
        session.close();
    }
}
