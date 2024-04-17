package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.*;

public class Direction_Test {

    @Test
    public void testGetX() {
        // Arrange & Act
        int leftX = Direction.LEFT.getX();
        int rightX = Direction.RIGHT.getX();
        
        // Assert
        assertEquals(-1, leftX);
        assertEquals(1, rightX);
    }

    @Test
    public void testGetY() {
        // Arrange & Act
        int downY = Direction.DOWN.getY();
        int upY = Direction.UP.getY();
        
        // Assert
        assertEquals(1, downY);
        assertEquals(-1, upY);
    }

}

