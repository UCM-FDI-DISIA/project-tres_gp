package gp.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gp.GameObjects.Piece;
import javafx.scene.layout.GridPane;

class Game5Test {

    @Test
    void testFiveInRow() {
        Game5 game5 = new Game5();
        
        GridPane gridPane = new GridPane();
        
        game5.fiveInRow(gridPane);
        
        assertFalse(isPieceAdded(game5, 1, 2)); 
        assertTrue(isPieceAdded(game5, 9, 2)); 
    }
    
    private boolean isPieceAdded(Game5 game, int column, int row) {
        var container = game.getGameObjectContainer();

        for (var gameObject : container) {
            if (gameObject instanceof Piece) {
                Piece piece = (Piece) gameObject;
                if (piece.getPosition().getCol() == column && piece.getPosition().getRow() == row) {
                    return true;
                }
            }
        }

        return false;
    }
}
