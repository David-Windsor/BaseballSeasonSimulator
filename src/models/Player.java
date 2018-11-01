package models;


/**
 * This class will house all the information about a particular player
 * Sooner or later, a map might be better for storing all this information
 * 
 */
public class Player {
	public String name;
	public String playerID;
	public Team team;
	
	public Player(String n, String id, Team t) {
		name = n;
		playerID = id;
		team = t;
	}

}
