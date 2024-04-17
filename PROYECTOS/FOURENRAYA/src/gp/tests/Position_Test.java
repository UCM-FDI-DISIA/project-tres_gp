package gp.tests;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gp.logic.*; 

public class Position_Test {

    @Test
    void testEquals() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(5, 6);

        assertTrue(pos1.equals(pos2));
        assertFalse(pos1.equals(pos3));
    }

    @Test
    void testIsOnBoard() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(-1, 4);
        Position pos3 = new Position(3, -1);
        Position pos4 = new Position(7, 4);
        Position pos5 = new Position(3, 6);

        assertTrue(pos1.isOnBoard());
        assertFalse(pos2.isOnBoard());
        assertFalse(pos3.isOnBoard());
        assertFalse(pos4.isOnBoard());
        assertFalse(pos5.isOnBoard());
    }
}

