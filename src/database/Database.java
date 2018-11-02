package database;

import models.Team;

import java.sql.*;
import java.util.HashMap;


/**
 * This is where we will store the database during run-time
 * I Don't quite know how we are gonna hold this yet.
 *
 */
public class Database {
	private Connection c = null;
	private Statement stmt = null;
	private HashMap<String,Team> teams;

	public Database() {
		teams = new HashMap<>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./external_resources/BaseballDatabase.db");
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
