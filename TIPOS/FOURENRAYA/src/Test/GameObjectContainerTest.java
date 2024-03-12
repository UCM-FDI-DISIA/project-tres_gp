package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.exceptions.FullColumnException;
import gp.logic.Direction;
import gp.logic.GameObjectContainer;
import gp.logic.Position;

public class GameObjectContainerTest {

    private GameObjectContainer container;

    @BeforeEach
    void setUp() {
        container = new GameObjectContainer();
    }

    @Test
    void testToString() {
        assertEquals("", container.toString(new Position(0, 0)));
    }

    @Test
    void testAdd() {
        container.add(null); // Just to cover the method
    }

    @Test
    void testFindRow() {
        assertThrows(FullColumnException.class, () -> container.findRow(0));
    }

    @Test
    void testIsFinished() {
        assertFalse(container.isFinished(1));
    }

    @Test
    void testFitIn() {
        assertTrue(container.fitIn(3, Direction.DOWN, new Position(0, 0)));
        assertFalse(container.fitIn(3, Direction.UP, new Position(0, 0)));
    }

    @Test
    void testCheckConsecutiveTurns() {
        assertTrue(container.checkConsecutiveTurns(1, 3, Direction.DOWN, new Position(0, 0)));
        assertFalse(container.checkConsecutiveTurns(2, 3, Direction.DOWN, new Position(0, 0)));
    }

    @Test
    void testWin() {
        container.win(Direction.DOWN, new Position(0, 0)); // Just to cover the method
    }

    @Test
    void testFindConsecutiveTurn() {
        assertEquals(-1, container.findConsecutiveTurn(new Position(0, 0)));
    }
}
