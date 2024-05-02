package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BombTest {

    @Test
    public void testDefaultConstructor() {
    
        Bomb bomb = new Bomb();

        // Assert
        assertNotNull(bomb);
        assertNull(bomb.getPosition());
    }
}
