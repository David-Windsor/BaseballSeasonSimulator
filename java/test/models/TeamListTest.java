package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamListTest {

    private TeamList teamList = new TeamList(2017);

    @Test
    void getTeamsByLeague() {
        assertEquals(15, teamList.getTeamsByLeague("AL").size());
        assertEquals(15, teamList.getTeamsByLeague("NL").size());
        assertTrue(!teamList.getTeamsByLeague("AL").containsAll(teamList.getTeamsByLeague("NL")));
    }

    @Test
    void getTeamsByLeagueAndDivision() {
        assertEquals(5, teamList.getTeamsByLeagueAndDivision("AL", "E").size());

    }
}