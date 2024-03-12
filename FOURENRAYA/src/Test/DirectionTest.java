package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Direction;

public class DirectionTest {

    @Test
    void testGetX() {
        assertEquals(-1, Direction.LEFT.getX());
        assertEquals(1, Direction.RIGHT.getX());
        assertEquals(0, Direction.DOWN.getX());
        assertEquals(0, Direction.UP.getX());
        assertEquals(-1, Direction.UP_LEFT.getX());
        assertEquals(1, Direction.UP_RIGHT.getX());
        assertEquals(-1, Direction.DOWN_LEFT.getX());
        assertEquals(1, Direction.DOWN_RIGHR.getX());
    }

    @Test
    void testGetY() {
        assertEquals(0, Direction.LEFT.getY());
        assertEquals(0, Direction.RIGHT.getY());
        assertEquals(1, Direction.DOWN.getY());
        assertEquals(-1, Direction.UP.getY());
        assertEquals(-1, Direction.UP_LEFT.getY());
        assertEquals(-1, Direction.UP_RIGHT.getY());
        assertEquals(1, Direction.DOWN_LEFT.getY());
        assertEquals(1, Direction.DOWN_RIGHR.getY());
    }
}
