package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    public void testDefaultConstructor() {
        Piece piece = new Piece();

        // Assert
        assertNotNull(piece);
        assertNull(piece.getPosition());
        assertEquals(0, piece.getTurn());
    }
}
