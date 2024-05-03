package gp.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    public void testGetX() {
        int leftX = Direction.LEFT.getX();
        int rightX = Direction.RIGHT.getX();
        int downX = Direction.DOWN.getX();
        int upX = Direction.UP.getX();

        // Assert
        assertEquals(-1, leftX);
        assertEquals(1, rightX);
        assertEquals(0, downX);
        assertEquals(0, upX);
    }

    @Test
    public void testGetY() {
        int leftY = Direction.LEFT.getY();
        int rightY = Direction.RIGHT.getY();
        int downY = Direction.DOWN.getY();
        int upY = Direction.UP.getY();

        // Assert
        assertEquals(0, leftY);
        assertEquals(0, rightY);
        assertEquals(1, downY);
        assertEquals(-1, upY);
    }
}
