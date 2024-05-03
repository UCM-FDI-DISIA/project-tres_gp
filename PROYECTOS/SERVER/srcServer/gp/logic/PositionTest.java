package gp.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testConstructorAndGetters() {
        int col = 3;
        int row = 4;

        Position position = new Position(col, row);

        // Assert
        assertEquals(col, position.getCol());
        assertEquals(row, position.getRow());
    }

    @Test
    public void testSetters() {
        Position position = new Position(0, 0);

        position.setCol(3);
        position.setRow(4);

        // Assert
        assertEquals(3, position.getCol());
        assertEquals(4, position.getRow());
    }

    @Test
    public void testEquals() {
        Position position1 = new Position(3, 4);
        Position position2 = new Position(3, 4);
        Position position3 = new Position(4, 3);

        // Assert
        assertTrue(position1.equals(position2));
        assertFalse(position1.equals(position3));
    }

    @Test
    public void testIsOnBoard() {
        Position positionInsideBoard = new Position(3, 4);
        Position positionOutsideBoard1 = new Position(-1, 4);
        Position positionOutsideBoard2 = new Position(3, -1);
        Position positionOutsideBoard3 = new Position(7, 4);
        Position positionOutsideBoard4 = new Position(3, 6);

        // Assert
        assertTrue(positionInsideBoard.isOnBoard());
        assertFalse(positionOutsideBoard1.isOnBoard());
        assertFalse(positionOutsideBoard2.isOnBoard());
        assertFalse(positionOutsideBoard3.isOnBoard());
        assertFalse(positionOutsideBoard4.isOnBoard());
    }
}
