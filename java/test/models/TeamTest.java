package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TeamTest {

    @Test
    void testHashCode() {
        //ensure two teams that are the same by league, division and id are the same
        Team team = new Team("PIT", "Pirates", "NL", "C", 0);
        Team team1 = new Team("PIT", "Pirates", "NL", "C", 0);
        Team team2 = new Team("ARI", "Pirates", "AL", "C", 0);
        assertEquals(team.hashCode(), team1.hashCode());
        assertNotEquals(team.hashCode(), team2.hashCode());
    }

    @Test
    void equals() {
        // a different average but same hashes would be a different year for that team and should not be equal
        Team team1 = new Team("PIT", "Pirates", "NL", "C", 0);
        Team team2 = new Team("PIT", "Pirates", "NL", "C", 0);
        Team team3 = new Team("PIT", "Pirates", "NL", "C", 1);
        assertEquals(team1, team2);
        assertNotEquals(team1, team3);
    }
}