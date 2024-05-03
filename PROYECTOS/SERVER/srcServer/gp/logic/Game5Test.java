// No funciona por el paquete de JavaFX, funcionaba para la consola


/*package gp.logic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.logic.Game5;
import gp.logic.Position;
import javafx.scene.layout.GridPane;

public class Game5Test {

    @Test
    public void testConstructor() {
        Game5 game = new Game5();
        assertEquals(0, game.getCycle());
    }
    
    @Test
    public void testFiveInRow() {
        Game5 game = new Game5();
        GridPane gridPane = new GridPane();
        
        game.fiveInRow(gridPane);
        
        List<GameObject> gameObjects = game.getGameObjectContainer();
        assertEquals(2 * Game5.DIM_Y, gameObjects.size());
        for (int i = 0; i < Game5.DIM_Y; i++) {
            Piece firstPiece = (Piece) gameObjects.get(i);
            Piece secondPiece = (Piece) gameObjects.get(Game5.DIM_Y + i);
            assertEquals(1, firstPiece.getTurn());
            assertEquals(2, secondPiece.getTurn());
            Position firstPosition = firstPiece.getPosition();
            Position secondPosition = secondPiece.getPosition();
            assertEquals(0, firstPosition.getCol());
            assertEquals(i, firstPosition.getRow());
            assertEquals(Game5.DIM_X, secondPosition.getCol());
            assertEquals(i, secondPosition.getRow());
        }
    }
}
*/