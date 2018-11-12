package database;

import com.sun.istack.internal.NotNull;
import models.Team;

import java.sql.*;
import java.util.ArrayList;


/**
 * This is where we will store the database during run-time
 * I Don't quite know how we are gonna hold this yet.
 */
class Database {

    @NotNull
    private static Connection getNewConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:./external_resources/BaseballDatabase.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return getNewConnection();
        }
    }

    /**
     * @param year year to get from the database. Year >= 2016
     * @return a List of models.Team that played in that year
     * Opens a new connection and produces a list of all teams that played that year
     */
    @NotNull
    static ArrayList<Team> getTeamsForYear(@NotNull Integer year) {
        ArrayList<Team> teams = new ArrayList<>();
        if (year >= 2015) {
            try {
                String select = "SELECT * FROM TeamBattingYear WHERE year = ?" +
                        " ";
                Connection c = getNewConnection();
                //make the prepared statement and execute
                PreparedStatement statement = c.prepareStatement(select);
                statement.setInt(1, year);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Team team = new Team(result.getString("team_id"), result.getString("name"),
                            result.getString("league"), result.getString("division"));
                    teams.add(team);
                }
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }
        return teams;
    }

}
