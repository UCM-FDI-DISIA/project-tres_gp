package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.GameObjects.*;

public class GameObject_Test {

    @Test
    public void testGetPosition() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        GameObject gameObject = new GameObjectMock(game, pos);
        
        // Act
        Position result = gameObject.getPosition();
        
        // Assert
        assertEquals(pos, result);
    }

    @Test
    public void testIsOnPosition() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        GameObject gameObject = new GameObjectMock(game, pos);
        
        // Act & Assert
        assertTrue(gameObject.isOnPosition(pos));
        assertFalse(gameObject.isOnPosition(new Position(1, 1)));
    }

    
    // Clase mock para propósitos de prueba
    private class GameObjectMock extends GameObject {
        public GameObjectMock(Game game, Position pos) {
            super(game, pos);
        }

        @Override
        protected String getSymbol() {
            return null;
        }

        @Override
        public String toString() {
            return null;
        }
    }
}

