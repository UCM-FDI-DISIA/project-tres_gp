package gp.GameObjects;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;

public class PieceTest {

    @Test
    public void testConstructorWithGameAndPosition() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        Piece piece = new Piece(game, pos);
        assertEquals(pos, piece.getPosition());
    }

    @Test
    public void testConstructorWithGamePositionAndTurn() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        int turn = 2;
        Piece piece = new Piece(game, pos, turn);
        assertEquals(pos, piece.getPosition());
        assertEquals(turn, piece.getTurn());
    }

    @Test
    public void testDie() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        Piece piece = new Piece(game, pos);
        piece.die();
    }
}
