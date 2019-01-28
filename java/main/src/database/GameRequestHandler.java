package database;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 * Handler that all handles all requests to the database regarding games
 *
 * @author Dan Jackson
 */
public class GameRequestHandler {
    private static ArrayList<String> listOfGamesAsStrings;

    public GameRequestHandler() {
        listOfGamesAsStrings = new ArrayList<String>();
    }

    /**
     * will request a list of games from the database if that has not happened yet.
     *
     * @return ArrayList of strings which represent games. Ex "PIT @ PHI"
     */
    @NotNull
    public ArrayList<String> getGames() {
        // Check to see if the team has already been loaded and if so just return a copy
        if (!listOfGamesAsStrings.isEmpty()) {
            return listOfGamesAsStrings;
        }
        //if it isn't we will insert it ourselves. Default to ArrayList
        else {
            fetchGames();
            return listOfGamesAsStrings;
        }
    }

    private void fetchGames() {
        listOfGamesAsStrings = Database.getGames();
    }


}

