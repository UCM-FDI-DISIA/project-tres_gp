package gp;

import javafx.application.Platform;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Stage testStage;

    @BeforeEach
    void setUp() {
        Platform.startup(() -> {
            testStage = new Stage();
        });
    }

    @AfterEach
    void tearDown() {
        Platform.exit();
    }

    @Test
    void start_shouldLoadInitialScene() {
        Main mainApp = new Main();
        try {
            mainApp.start(testStage);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        assertNotNull(testStage);
        assertFalse(testStage.isShowing());
        assertEquals(null, testStage.getTitle()); 
    }
}
