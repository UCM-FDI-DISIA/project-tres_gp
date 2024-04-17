package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.GameObjects.*;

public class SuperPiece_Test {

    @Test
    public void testDie() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        SuperPiece superPiece = new SuperPiece(game, pos);
        
        // Act
        superPiece.die();
        
        // Assert
        assertTrue(game.getObjects().stream().anyMatch(obj -> obj instanceof Piece));
        assertFalse(game.getObjects().stream().anyMatch(obj -> obj instanceof SuperPiece));
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        SuperPiece superPiece = new SuperPiece();
        
        // Assert
        assertNotNull(superPiece);
    }

}

