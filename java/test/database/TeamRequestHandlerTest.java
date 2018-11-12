package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamRequestHandlerTest {

    @Test
    void getTeamsForYear() {
        TeamRequestHandler requestHandler = new TeamRequestHandler();
        assertEquals(30, requestHandler.getTeamsForYear(2017).size());

    }
}