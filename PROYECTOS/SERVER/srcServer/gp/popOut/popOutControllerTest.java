package gp.popOut;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class popOutControllerTest {

    private popOutController controller;

    @BeforeEach
    public void setUp() {
        controller = new popOutController();
    }

    @Test
    public void testShowWinners() {
        assertDoesNotThrow(() -> controller.showWinners());
    }
}
