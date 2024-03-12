package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.view.BoardPrinter;

public class BoardPrinterTest {

    private Game game;
    private BoardPrinter boardPrinter;

    @BeforeEach
    void setUp() {
        game = new Game();
        boardPrinter = new BoardPrinter(game);
    }

    @Test
    void testGetInfo() {
        String expected = "Number of cycles: 0\nTurn for player: 1\n";
        assertEquals(expected, boardPrinter.getInfo());
    }

    @Test
    void testToString() {
        String expected = "Number of cycles: 0\nTurn for player: 1\n " +
                          "┌──────┬──────┬──────┬──────┬──────┬──────┬──────┐\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          " |      |      |      |      |      |      |      |\n" +
                          "└──────┴──────┴──────┴──────┴──────┴──────┴──────┘\n";
        assertEquals(expected, boardPrinter.toString());
    }

    @Test
    void testEndMessage() {
        assertEquals("Game Over!", boardPrinter.endMessage());
    }
}
