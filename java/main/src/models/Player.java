package models;


/**
 * Class representing a Player in the MLB. Currently unused but will be needed when Innings are introduced
 * @author Dan Jackson
 * @author David Windsor
 */
public class Player {
	private String name;
	private String playerID;
	private Team team;
	
	public Player(String n, String id, Team t) {
		name = n;
		playerID = id;
		team = t;
	}

}
