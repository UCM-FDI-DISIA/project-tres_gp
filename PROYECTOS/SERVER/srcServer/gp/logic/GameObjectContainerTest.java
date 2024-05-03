package gp.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gp.GameObjects.GameObject;
import gp.logic.GameObjectContainer;
import gp.logic.Position;

public class GameObjectContainerTest {

    private GameObjectContainer container;

    @Before
    public void setUp() {
        container = new GameObjectContainer();
    }

    @Test
    public void testConstructor() {
        List<GameObject> objects = container.getListGameObjects();
        assertTrue(objects.isEmpty());

        List<List<Position>> winners = container.getWinners();
        assertTrue(winners.isEmpty());
    }

    // Resto de tests tendrían que ser implementados con la interfaz
}
