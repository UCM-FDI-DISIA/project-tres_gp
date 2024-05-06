package gp.logic;

import static org.junit.Assert.*;
import org.junit.Test;
import gp.logic.Position;
import gp.logic.Game;

public class PositionTest {

    @Test
    public void testConstructorAndGetters() {
        Position pos = new Position(3, 4);
        assertEquals(3, pos.getCol());
        assertEquals(4, pos.getRow());
    }

    @Test
    public void testEquals() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(2, 4);
        assertTrue(pos1.equals(pos2));
        assertFalse(pos1.equals(pos3));
    }

    @Test
    public void testIsOnBoardInsideBoard() {
        Position pos = new Position(3, 4);
        assertTrue(pos.isOnBoard());
    }

    @Test
    public void testIsOnBoardOutsideBoard() {
        Position pos1 = new Position(-1, 4);
        Position pos2 = new Position(3, 6);
        assertFalse(pos1.isOnBoard());
        assertFalse(pos2.isOnBoard());
    }

    @Test
    public void testIsOnBoardOnEdge() {
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(6, 5);
        assertTrue(pos1.isOnBoard());
        assertTrue(pos2.isOnBoard());
    }
}
