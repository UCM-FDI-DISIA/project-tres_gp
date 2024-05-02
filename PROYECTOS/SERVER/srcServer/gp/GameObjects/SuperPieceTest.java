package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuperPieceTest {

    @Test
    public void testDie() {

        Game game = new Game();
        Position position = new Position(0, 0);
        SuperPiece superPiece = new SuperPiece(game, position);

       
        superPiece.die();

        // Assert
        // Verificar que la pieza se haya eliminado del juego
        assertFalse(game.getGameObjectContainer().contains(superPiece));
    }
    
    @Test
    public void testDefaultConstructor() {
        SuperPiece superPiece = new SuperPiece();

        // Assert
        assertNotNull(superPiece);
        assertNull(superPiece.getPosition());
    }

}
