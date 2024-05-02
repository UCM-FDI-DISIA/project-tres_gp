package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnvilTest {

    @Test
    public void testDefaultConstructor() {
        
        Anvil anvil = new Anvil();

        // Assert
        assertNotNull(anvil);
        assertNull(anvil.getPosition());
    }
}
