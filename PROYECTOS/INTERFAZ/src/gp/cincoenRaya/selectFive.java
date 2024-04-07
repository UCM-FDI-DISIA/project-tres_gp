package gp.cincoenRaya;

import java.io.IOException;

import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class selectFive {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	@FXML
	private Button btnVolver;
	
	@FXML
	private Button btnEmpieza;
	
    @FXML
    private Button btnFicha;

    @FXML
    private Button btnFicha1;

    @FXML
    private Button btnFicha2;

    @FXML
    private Button btnFicha3;

    @FXML
    private Button btnFicha4;

    @FXML
    private Button btnFicha5;

    @FXML
    private Button btnFicha6;
    
    @FXML
    private Button btnFicha7;

    @FXML
    private Button btnFicha8;
    
    @FXML
    private Button btnFicha9;
    
    @FXML
    private Button btnFicha10;

    @FXML
    private GridPane gridPane;
    
    public selectFive() {
    	this.game = new Game();
    }
    @FXML
    private void colocarFicha(MouseEvent event){
        //if (event.getSource() instanceof Button) {
    		Node obt = (Node)event.getSource();
    		obt = obt.getParent();
            int columna = GridPane.getColumnIndex(obt);
            
            try {
                Parent ficha = FXMLLoader.load(getClass().getResource(
                		"FICHA J%s 5 IN ROW.fxml".formatted(game.getTurn())));
                int fila = game.place(columna);
                gridPane.add(ficha, columna, fila);
        		Position pos = new Position(columna, fila);
        		game.addObject(new Piece(game, pos));
        		if(game.someoneWin()) {
        			System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
        			Parent alertRoot = FXMLLoader.load(getClass().getResource("VOLVER A INICIAL.fxml"));
        			gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                    GridPane.setHalignment(alertRoot, HPos.CENTER);
                    GridPane.setValignment(alertRoot, VPos.CENTER);
        		}
                game.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }
    @FXML
    private void configurarTablero(MouseEvent event) {
    	Node source = (Node) event.getSource();
    	source = source.getParent();
    	GridPane gridPane = (GridPane) source.getParent();
    	game.fiveInRow(gridPane);
    	gridPane.getChildren().remove(source);
    }
    @FXML
    void onMouseEntered(MouseEvent event) {
        // Código para el efecto al entrar con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.45); // Cambiar la opacidad para simular una luz encendida
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        // Código para revertir el efecto al salir con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.0); // Restaurar la opacidad original para apagar la "luz"
    }
    
}
