package gp.GameObjects;

import gp.logic.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IceTest {

    @Test
    public void testDefaultConstructor() {
        Ice ice = new Ice();

        // Assert
        assertNotNull(ice);
        assertNull(ice.getPosition());
    }
}
