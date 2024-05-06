package gp.GameObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.GameObjects.SuperPiece;
import gp.logic.Game;
import gp.logic.Position;

public class SuperPieceTest {

    @Test
    public void testConstructorWithGameAndPosition() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        SuperPiece superPiece = new SuperPiece(game, pos);
        assertEquals(pos, superPiece.getPosition());
    }

    @Test
    public void testDefaultConstructor() {
        SuperPiece superPiece = new SuperPiece();
    }

    @Test
    public void testDie() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        SuperPiece superPiece = new SuperPiece(game, pos);
        superPiece.die();
        assertTrue(game.getGameObjectContainer().stream().anyMatch(obj -> obj instanceof Piece));
    }
}
