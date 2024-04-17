package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;
import gp.GameObjects.*;

public class Ice_Test {

    @Test
    public void testToString() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        Ice ice = new Ice(game, pos);
        
        // Act
        String result = ice.toString();
        
        // Assert
        assertEquals(Messages.ICE, result);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Ice ice = new Ice();
        
        // Assert
        assertNotNull(ice);
    }

}

