package gp.cincoenRaya;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

import gp.logic.Game5;
import gp.logic.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class selectFiveTest {

	@Test
    void testSelectFiveConstructor() {
        selectFive selectFive = new selectFive();

        assertNotNull(selectFive.game);
        assertTrue(selectFive.game instanceof Game5);
    }
	
	@Test
	void testShowWinners() throws IOException {
        GridPane gridPane = new GridPane();

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0, 0));
        positions.add(new Position(0, 1));
        List<List<Position>> winners = new ArrayList<>();
        winners.add(positions);

        selectFive selectFive = new selectFive();
        selectFive.showWinners(gridPane);

        assertFalse(gridPane.getChildren().size() > 0);
    }
}
