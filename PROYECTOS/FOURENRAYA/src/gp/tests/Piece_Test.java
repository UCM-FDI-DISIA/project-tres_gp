package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;
import gp.GameObjects.*;

public class Piece_Test {

    @Test
    public void testToString() {
        // Arrange
        Game game = new Game();
        Position pos = new Position(0, 0);
        Piece piece = new Piece(game, pos, 1);
        
        // Act
        String resultPlayer1 = piece.toString();
        
        // Assert
        assertEquals(Messages.FICHA1, resultPlayer1);
        
        // Arrange
        Piece piece2 = new Piece(game, pos, 2);
        
        // Act
        String resultPlayer2 = piece2.toString();
        
        // Assert
        assertEquals(Messages.FICHA2, resultPlayer2);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Piece piece = new Piece();
        
        // Assert
        assertNotNull(piece);
    }

}
