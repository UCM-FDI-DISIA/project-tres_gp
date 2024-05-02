package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrowTest {

    @Test
    public void testDefaultConstructor() {
        
        Arrow arrow = new Arrow();

        // Assert
        assertNotNull(arrow);
        assertNull(arrow.getPosition());
    }
}
