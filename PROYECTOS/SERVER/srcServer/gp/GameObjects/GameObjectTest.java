package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {

    @Test
    public void testDefaultConstructor() {
        GameObject gameObject = new MockGameObject();

        // Assert
        assertNotNull(gameObject);
        assertNull(gameObject.getPosition());
    }


    @Test
    public void testDie() {
        
        GameObject gameObject = new MockGameObject();

        gameObject.die();

        // Assert
    }

    @Test
    public void testDeserializePos() {
        
        Position position = GameObject.deserializePos(null, "3,4");

        // Assert
        assertNotNull(position);
        assertEquals(3, position.getCol());
        assertEquals(4, position.getRow());
    }

    @Test
    public void testDeserializeTurn() {
      
        int turn = GameObject.deserializeTurn(null, "3,4,0");

        // Assert
        assertEquals(0, turn);
    }

    private static class MockGameObject extends GameObject {
        public MockGameObject(Game game, Position pos) {
            super(game, pos);
        }

        public MockGameObject() {
            super();
        }

        @Override
        public void die() {
        }
    }
}
