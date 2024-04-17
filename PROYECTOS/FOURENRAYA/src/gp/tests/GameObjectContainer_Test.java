package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.GameObjects.*;
import gp.exceptions.*;
import gp.logic.*;

public class GameObjectContainer_Test {

    private GameObjectContainer container;

    @BeforeEach
    public void setUp() {
        container = new GameObjectContainer();
    }

    @Test
    public void testAddObject() {
        // Arrange
        GameObject obj = new Piece();

        // Act
        container.add(obj);

        // Assert
        assertTrue(container.contains(obj));
    }

    @Test
    public void testRemoveObject() {
        // Arrange
        GameObject obj = new Piece();
        container.add(obj);

        // Act
        container.remove(obj);

        // Assert
        assertFalse(container.contains(obj));
    }

    @Test
    public void testFindRowValid() throws FullColumnException {
        // Arrange
        int col = 0;
        int expectedRow = 0;

        // Act
        int actualRow = container.findRow(col);

        // Assert
        assertEquals(expectedRow, actualRow);
    }

    @Test
    public void testBomb() {
        // Arrange
        Position pos = new Position(2, 2);
        GameObject obj1 = new Piece();
        GameObject obj2 = new Piece();
        GameObject obj3 = new Piece();
        container.add(obj1);
        container.add(obj2);
        container.add(obj3);

        // Act
        container.bomb(pos);

        // Assert
        assertFalse(container.contains(obj1));
        assertFalse(container.contains(obj2));
        assertFalse(container.contains(obj3));
    }

    @Test
    public void testAnvil() {
        // Arrange
        Position pos = new Position(2, 2);
        GameObject obj1 = new Piece();
        GameObject obj2 = new Piece();
        GameObject obj3 = new Piece();
        container.add(obj1);
        container.add(obj2);
        container.add(obj3);

        // Act
        container.anvil(pos);

        // Assert
        assertFalse(container.contains(obj1));
        assertFalse(container.contains(obj2));
        assertFalse(container.contains(obj3));
    }


}

