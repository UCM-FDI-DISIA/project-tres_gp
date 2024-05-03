// No funciona por el paquete de JavaFX, funcionaba para la consola

/*package gp.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gp.GameObjects.Arrow;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import javafx.scene.layout.GridPane;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testConstructor() {
        assertNotNull(game);
    }

    @Test
    public void testPositionToString() {
        String positionString = game.positionToString(3, 4);
        assertNotNull(positionString);
        assertEquals("3,4", positionString);
    }

    @Test
    public void testGetCycle() {
        int cycle = game.getCycle();
        assertEquals(0, cycle);
    }

    @Test
    public void testGetGameObjectContainer() {
        List<GameObject> gameObjectContainer = game.getGameObjectContainer();
        assertNotNull(gameObjectContainer);
        assertTrue(gameObjectContainer.isEmpty());
    }

    @Test
    public void testSetGameObjectContainer() {
        List<GameObject> gameObjectList = List.of(new Piece(), new Arrow());
        game.setGameObjectContainer(gameObjectList);
        List<GameObject> updatedList = game.getGameObjectContainer();
        assertNotNull(updatedList);
        assertEquals(gameObjectList.size(), updatedList.size());
    }

    @Test
    public void testUpdate() {
        assertDoesNotThrow(() -> game.update());
    }

    @Test
    public void testUpdateBot() {
        assertDoesNotThrow(() -> game.updateBot());
    }

    @Test
    public void testPopOut() {
        assertDoesNotThrow(() -> game.popOut(2, new GridPane()));
    }
}
*/
