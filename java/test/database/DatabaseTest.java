package database;

import models.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Test
    void getTeamsForYear() {
        ArrayList<Team> teams = Database.getTeamsForYear(2015);
        assertEquals(30, teams.size());
    }
}