package gp.superfichas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SuperfichaControllerTest {

    private SuperfichaController controller;

    @BeforeEach
    public void setUp() {
        controller = new SuperfichaController();
    }

    @Test
    public void testShowWinners() {
        GridPane gridPane = new GridPane();
        assertDoesNotThrow(() -> controller.showWinners(gridPane));
    }

    @Test
    public void testBombButton() {
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
        assertDoesNotThrow(() -> controller.bombButton(event));
    }

    @Test
    public void testAnvilButton() {
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
        assertDoesNotThrow(() -> controller.anvilButton(event));
    }

    @Test
    public void testArrowButton() {
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
        assertDoesNotThrow(() -> controller.arrowButton(event));
    }
}
