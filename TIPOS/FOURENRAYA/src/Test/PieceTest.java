package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;

public class PieceTest {

    private Piece piece;
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        piece = new Piece(game, new Position(1, 1));
    }

    @Test
    void testToStringDefault() {
        assertEquals("", new Piece().toString());
    }
}

