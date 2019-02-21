package database;

import com.sun.istack.internal.NotNull;
import models.Team;

import java.sql.*;
import java.util.ArrayList;


/**
 * Class responsible for interacting directly with the database. Available to classes withing the database package only
 * @version 1
 * @author David Windsor
 * @author Dan Jackson
 */
class Database {
    /**
     * @return a new connection to the database
     */
    @NotNull
    private static Connection getNewConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:./external_resources/BaseballDatabaseOld.db");
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
                String select = "SELECT * FROM TeamBattingYear INNER JOIN Team on TeamBattingYear.team_id = Team.team_id" +
                        " WHERE TeamBattingYear.year = ?" ;

                Connection c = getNewConnection();
                //make the prepared statement and execute
                PreparedStatement statement = c.prepareStatement(select);
                statement.setInt(1, year);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    // we only need batting average to the thousandths place
                    int battingAverage = (int) (result.getInt("hits") * 1000.0 / result.getInt("at_bat"));
                    //TODO THIS WILL PUT THE BATTING AVERAGE AT THE PRECISION WE WANT, BUT WE SHOULD ADAPT IT TO USE INT
                    Team team = new Team(result.getString("team_id"), result.getString("name"),
                            result.getString("league_id"), result.getString("division_id"),
                            battingAverage);
                    teams.add(team);
                }
                c.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }

        return teams;
    }

}
