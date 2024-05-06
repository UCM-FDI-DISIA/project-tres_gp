package gp.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import gp.GameObjects.GameObject;
import gp.logic.Direction;
import gp.logic.GameObjectContainer;
import gp.logic.Position;
import javafx.scene.layout.GridPane;

class GameObjectContainerTest {

    private static class TestGameObject extends GameObject {
        public TestGameObject(Position position, int turn) {
            super(null, position, turn);
        }
    }

    @Test
    void testAddAndFindObject() {
        GameObjectContainer container = new GameObjectContainer();
        TestGameObject obj = new TestGameObject(new Position(1, 2), 1);
        container.add(obj);
        assertEquals(obj, container.findObject(new Position(1, 2)));
    }

    @Test
    void testDeletePiece() {
        GameObjectContainer container = new GameObjectContainer();
        GridPane gridPane = new GridPane(); 
        TestGameObject obj = new TestGameObject(new Position(1, 2), 1);
        container.add(obj);
        container.deletePiece(new Position(1, 2), gridPane);
        assertNull(container.findObject(new Position(1, 2)));
    }


    @Test
    void testReset() {
        GameObjectContainer container = new GameObjectContainer();
        GridPane gridPane = new GridPane(); 
        container.add(new TestGameObject(new Position(1, 2), 1));
        container.add(new TestGameObject(new Position(3, 4), 2));
        container.add(new TestGameObject(new Position(5, 6), 1));
        container.add(new TestGameObject(new Position(7, 8), 2));
        container.reset(gridPane);
        assertEquals(0, gridPane.getChildren().size());
    }

    @Test
    void testIsFinished() {
        GameObjectContainer container = new GameObjectContainer();
        container.add(new TestGameObject(new Position(0, 0), 1));
        container.add(new TestGameObject(new Position(1, 0), 1));
        container.add(new TestGameObject(new Position(2, 0), 1));
        container.add(new TestGameObject(new Position(3, 0), 1));
        assertTrue(container.isFinished(1, 7)); 
    }

    @Test
    void testGetWinners() {
        GameObjectContainer container = new GameObjectContainer();
        container.add(new TestGameObject(new Position(0, 0), 1));
        container.add(new TestGameObject(new Position(1, 0), 1));
        container.add(new TestGameObject(new Position(2, 0), 1));
        container.add(new TestGameObject(new Position(3, 0), 1));
        List<List<Position>> winners = container.getWinners();
        assertEquals(0, winners.size());
    }
}
