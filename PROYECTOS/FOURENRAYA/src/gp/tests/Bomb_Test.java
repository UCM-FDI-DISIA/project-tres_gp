package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;
import gp.GameObjects.*;

public class Bomb_Test {

    @Test
    public void testToString() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        Bomb bomb = new Bomb(game, pos);
        
        // Act
        String result = bomb.toString();
        
        // Assert
        assertEquals(Messages.BOMB, result);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Bomb bomb = new Bomb();
        
        // Assert
        assertNotNull(bomb);
    }

}

