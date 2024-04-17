package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;
import gp.GameObjects.*;

public class Arrow_Test {

    @Test
    public void testToString() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        Arrow arrow = new Arrow(game, pos);
        
        // Act
        String result = arrow.toString();
        
        // Assert
        assertEquals(Messages.ARROW, result);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Arrow arrow = new Arrow();
        
        // Assert
        assertNotNull(arrow);
    }

}

