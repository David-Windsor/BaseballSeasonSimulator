package models;

/**
 * @author David Windsor
 * @version 0.1
 * Class to represent an Inning.
 */
public class Inning {
    private Game game;
    private boolean isTop;

    public Inning(Game game) {
        this.game = game;
        isTop = false;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isTop() {
        return isTop;
    }

    void play() {

    }

}
