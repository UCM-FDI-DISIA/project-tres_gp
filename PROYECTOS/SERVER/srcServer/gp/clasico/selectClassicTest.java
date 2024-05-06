package gp.clasico;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

import gp.logic.Game;
import gp.logic.Position;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

	class selectClassicTest {

		@Test
	    void testUpdateGridPane() throws IOException {
	        GridPane gridPane = new GridPane();

	        selectClassic selectClassic = new selectClassic();
	        selectClassic.gridPane = gridPane; 

	        int columna = 0; 
	        selectClassic.updateGridPane(columna);

	        assertTrue(gridPane.getChildren().size() > 0); 
	    }

	    @Test
	    void testShowWinners() throws IOException {
	        GridPane gridPane = new GridPane();

	        selectClassic selectClassic = new selectClassic();
	        selectClassic.gridPane = gridPane; 

	        List<Position> positions = new ArrayList<>();
	        positions.add(new Position(0, 0));
	        positions.add(new Position(0, 1));
	        List<List<Position>> winners = new ArrayList<>();
	        winners.add(positions);

	        selectClassic.showWinners(gridPane);

	        assertFalse(gridPane.getChildren().size() > 0);
	    }
	    
	    
	    @Test
	    void testSelectClassicConstructor() {
	        selectClassic selectClassic = new selectClassic();

	        assertNotNull(selectClassic.game);
	        assertTrue(selectClassic.game instanceof Game);
	    }
}
