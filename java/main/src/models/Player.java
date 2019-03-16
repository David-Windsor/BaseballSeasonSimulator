package models;


import javax.persistence.*;

/**
 * Class representing a Player in the MLB. Currently unused but will be needed when Innings are introduced
 *
 * @author Dan Jackson
 * @author David Windsor
 */
@Entity
@Table(name = "PLAYERS")
public class Player implements Comparable<Player> {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int atBats;
    private String playerID;
    @OneToOne(cascade = CascadeType.ALL)
    private ResultGenerator resultGenerator;

    public Player() {
        name = "";
        playerID = "";
        atBats = 0;
        resultGenerator = new ResultGenerator();
    }

    public Player(String n, String id) {
        name = n;
        playerID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtBats() {
        return atBats;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public ResultGenerator getResultGenerator() {
        return resultGenerator;
    }

    public void setResultGenerator(ResultGenerator resultGenerator) {
        this.resultGenerator = resultGenerator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Player o) {
        if (atBats > o.atBats) return 1;
        if (atBats < o.atBats) return -1;
        return 0;
    }
}
