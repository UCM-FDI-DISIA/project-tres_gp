package gp.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gp.GameObjects.Anvil;
import gp.GameObjects.Arrow;
import gp.GameObjects.Bomb;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;
import javafx.scene.layout.GridPane;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testPositionToString() {
        String result = game.positionToString(2, 3);
        assertEquals("", result);
    }

    @Test
    public void testGetGameObjectContainer() {
        List<GameObject> objects = game.getGameObjectContainer();
        assertNotNull(objects);
        assertEquals(0, objects.size());
    }

    @Test
    public void testSetGameObjectContainer() {
        List<GameObject> objects = List.of(new Piece(game, new Position(1, 2)));
        game.setGameObjectContainer(objects);
        assertEquals(objects, game.getGameObjectContainer());
    }

    @Test
    public void testFlip() {
        game.flip();
        assertEquals(2, game.getTurn());
        game.flip();
        assertEquals(1, game.getTurn());
    }

    @Test
    public void testPlace() {
        int row = game.place(3);
        List<GameObject> objects = game.getGameObjectContainer();
        assertEquals(1, objects.size());
        assertTrue(objects.get(0) instanceof Piece);
        assertEquals(3, objects.get(0).getPosition().getCol());
        assertEquals(row, objects.get(0).getPosition().getRow());
        assertEquals(game.getTurn(), objects.get(0).getTurn());
    }

    @Test
    public void testPlaceOnline() {
        int row = game.placeOnline(4, true);
        List<GameObject> objects = game.getGameObjectContainer();
        assertEquals(1, objects.size());
        assertTrue(objects.get(0) instanceof Piece);
        assertEquals(4, objects.get(0).getPosition().getCol());
        assertEquals(row, objects.get(0).getPosition().getRow());
        assertEquals(1, objects.get(0).getTurn());
    }


    @Test
    public void testIsOnBoard() {
        assertTrue(game.isOnBoard(3));
        assertFalse(game.isOnBoard(-1));
        assertFalse(game.isOnBoard(7));
    }

    @Test
    public void testFindRow() {
        int row = game.findRow(4);
        assertEquals(5, row); 
    }

    @Test
    public void testAddObject() {
        GameObject obj = new Piece(game, new Position(2, 3));
        game.addObject(obj);
        List<GameObject> objects = game.getGameObjectContainer();
        assertEquals(1, objects.size());
        assertEquals(obj, objects.get(0));
    }

    @Test
    public void testRemove() {
        GameObject obj = new Piece(game, new Position(2, 3));
        game.addObject(obj);
        game.remove(obj);
        List<GameObject> objects = game.getGameObjectContainer();
        assertEquals(0, objects.size());
    }

    @Test
    public void testExit() {
        assertFalse(game.isFinished());
        game.exit();
        assertTrue(game.isFinished());
    }

    @Test
    public void testSomeoneWin() {
        assertFalse(game.someoneWin());
    }

    @Test
    public void testSomeoneWin5() {
        assertFalse(game.someoneWin5());
    }

    @Test
    public void testReset() {
        game.place(2);
        game.place(3);
        GridPane gridPane = new GridPane();
        game.reset(gridPane);
        List<GameObject> objects = game.getGameObjectContainer();
        assertEquals(2, objects.size());
    }
    
    @Test
    public void testBotEasyMove() {
        int move = game.botEasyMove();
        assertTrue(move >= 0 && move < Game.DIM_X);
    }

    @Test
    public void testBotMediumMove() {
        int move = game.botMediumMove();
        assertTrue(move >= 0 && move < Game.DIM_X);
    }

    @Test
    public void testGetWinners() {
        List<List<Position>> winners = game.getWinners();
        assertNotNull(winners);
    }
}
