import models.Player;
import models.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        Team team = new Team("PIT", "Pittsburgh", "NL", "W", 300);
        team.addPlayer(new Player("Dan", "D", team));
        team.addPlayer(new Player("Stan", "S", team));
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();
    }
}
