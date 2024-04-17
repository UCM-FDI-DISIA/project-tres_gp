package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;
import gp.GameObjects.*;

public class Anvil_Test {

    @Test
    public void testToString() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        Anvil anvil = new Anvil(game, pos);
        
        // Act
        String result = anvil.toString();
        
        // Assert
        assertEquals(Messages.ANVIL, result);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Anvil anvil = new Anvil();
        
        // Assert
        assertNotNull(anvil);
    }

}

