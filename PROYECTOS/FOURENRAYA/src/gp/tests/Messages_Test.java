package gp.tests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gp.view.*;

public class Messages_Test {

    @Test
    void testWinMessage() {
        int turn = 1;
        String expected = "Player 1 win!";
        String actual = Messages.win(turn);
        assertEquals(expected, actual);
    }

    @Test
    void testExitMessage() {
        int turn = 2;
        String expected = "Player 2 leaves the game";
        String actual = Messages.exit(turn);
        assertEquals(expected, actual);
    }

}
