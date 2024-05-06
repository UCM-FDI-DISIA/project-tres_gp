package gp.GameObjects;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import gp.GameObjects.GameObject;
import gp.logic.Game;
import gp.logic.Position;

public class GameObjectTest {

    @Test
    public void testGetPosition() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        GameObject gameObject = new GameObject(game, pos) {
            @Override
            public void die() {}
        };
        assertEquals(pos, gameObject.getPosition());
    }

    @Test
    public void testIsOnPosition() {
        Game game = new Game();
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(5, 6);
        GameObject gameObject = new GameObject(game, pos1) {
            @Override
            public void die() {}
        };
        assertEquals(true, gameObject.isOnPosition(pos1));
        assertEquals(false, gameObject.isOnPosition(pos2));
    }

    @Test
    public void testSerialize() {
        Game game = new Game();
        Position pos = new Position(3, 4);
        GameObject gameObject = new GameObject(game, pos) {
            @Override
            public void die() {}
        };
        String serialized = gameObject.serialize();
        assertEquals("3,4,1", serialized);
    }

    @Test
    public void testDeserialize() {
        Game game = new Game();
        String data = "3,4,1";
        GameObject gameObject = GameObject.deserialize(game, data);
        assertEquals(3, gameObject.getPosition().getCol());
        assertEquals(4, gameObject.getPosition().getRow());
        assertEquals(1, gameObject.getTurn());
    }

    @Test
    public void testDeserializePos() {
        Game game = new Game();
        String data = "3,4";
        Position pos = GameObject.deserializePos(game, data);
        assertEquals(3, pos.getCol());
        assertEquals(4, pos.getRow());
    }

    @Test
    public void testDeserializeTurn() {
        Game game = new Game();
        String data = "3,4,2";
        int turn = GameObject.deserializeTurn(game, data);
        assertEquals(2, turn);
    }
}
