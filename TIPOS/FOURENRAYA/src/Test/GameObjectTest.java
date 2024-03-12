package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.GameObjects.GameObject;
import gp.logic.Game;
import gp.logic.Position;

public class GameObjectTest {

    private GameObject gameObject;
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        Position position = new Position(1, 1);
        gameObject = new GameObject(game, position) {
            @Override
            protected String getSymbol() {
                return "X";
            }

            @Override
            public String toString() {
                return "Test Object";
            }
        };
    }

    @Test
    void testGetPosition() {
        assertEquals(new Position(1, 1), gameObject.getPosition());
    }

    @Test
    void testIsOnPositionWithSamePosition() {
        assertTrue(gameObject.isOnPosition(new Position(1, 1)));
    }

    @Test
    void testIsOnPositionWithDifferentPosition() {
        assertFalse(gameObject.isOnPosition(new Position(2, 2)));
    }

    @Test
    void testGetTurn() {
        assertEquals(0, gameObject.getTurn());
    }

    @Test
    void testToString() {
        assertEquals("Test Object", gameObject.toString());
    }
}

