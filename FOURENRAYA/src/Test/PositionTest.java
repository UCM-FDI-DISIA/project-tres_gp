package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;

public class PositionTest {

    @Test
    void testConstructorAndGetters() {
        Position pos = new Position(1, 2);
        assertEquals(1, pos.getCol());
        assertEquals(2, pos.getRow());
    }

    @Test
    void testSetters() {
        Position pos = new Position(1, 2);
        pos.setCol(3);
        pos.setRow(4);
        assertEquals(3, pos.getCol());
        assertEquals(4, pos.getRow());
    }

    @Test
    void testEquals() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1, 2);
        Position pos3 = new Position(3, 4);
        assertTrue(pos1.equals(pos2));
        assertFalse(pos1.equals(pos3));
    }

    @Test
    void testIsOnBoard() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(-1, 2);
        Position pos3 = new Position(1, -2);
        Position pos4 = new Position(Game.DIM_X, 2);
        Position pos5 = new Position(1, Game.DIM_Y);
        assertTrue(pos1.isOnBoard());
        assertFalse(pos2.isOnBoard());
        assertFalse(pos3.isOnBoard());
        assertFalse(pos4.isOnBoard());
        assertFalse(pos5.isOnBoard());
    }
}

