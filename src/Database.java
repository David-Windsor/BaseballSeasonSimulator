import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * This is where we will store the database during run-time
 * I Don't quite know how we are gonna hold this yet.
 *
 */
public class Database {
	Connection c = null;
	Statement stmt = null;
	HashMap<String,Team> teams;
	Database(){
		teams = new HashMap<String, Team>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/BaseballDatabase.db");
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void buildDatabase() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Team");
			
			while(rs.next()) {
				String id = rs.getString("team_id");
				String name = rs.getString("name");
				teams.put(id, new Team(id,name));
			}
			rs = stmt.executeQuery("SELECT team_id, hits, at_bat FROM TeamBattingYear WHERE year = 2017");
			while(rs.next()) {
				teams.get(rs.getString("team_id")).setTeamValue(rs.getDouble("hits")/rs.getDouble("at_bat"));
			}
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}

	
	public Team getTeam(String teamID) {
		return teams.get(teamID);
	}
}
