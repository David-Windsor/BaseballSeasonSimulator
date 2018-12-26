package models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class representing a Player in the MLB. Currently unused but will be needed when Innings are introduced
 * @author Dan Jackson
 * @author David Windsor
 */
@Entity
@Table(name = "PLAYERS")
public class Player {
    private String name;
    @Id
    private String playerID;
    @OneToOne
    private Team team;
	
	public Player(String n, String id, Team t) {
		name = n;
		playerID = id;
		team = t;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
