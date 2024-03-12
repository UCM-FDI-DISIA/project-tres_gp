package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.logic.Game;
import gp.logic.Position;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testPositionToString() {
        assertEquals("", game.positionToString(0, 0));
    }

    @Test
    void testGetCycle() {
        assertEquals(0, game.getCycle());
    }

    @Test
    void testUpdate() {
        game.update();
        assertEquals(1, game.getCycle());
    }

    @Test
    void testIsOnBoard() {
        assertTrue(game.isOnBoard(0));
        assertFalse(game.isOnBoard(-1));
        assertFalse(game.isOnBoard(Game.DIM_X));
    }

    @Test
    void testFindRow() {
        assertThrows(FullColumnException.class, () -> game.findRow(0));
    }

    @Test
    void testAddObject() {
        game.addObject(null);
    }

    @Test
    void testExit() {
        game.exit();
        assertTrue(game.isFinished());
    }

    @Test
    void testIsFinished() {
        assertFalse(game.isFinished());
    }

    @Test
    void testGetTurn() {
        assertEquals(1, game.getTurn());
    }

    @Test
    void testReset() {
        game.reset();
        assertEquals(0, game.getCycle());
        assertEquals(1, game.getTurn());
    }

    @Test
    void testPlace() throws OffWorldException, FullColumnException {
        assertThrows(OffWorldException.class, () -> game.place(-1));
    }
}
