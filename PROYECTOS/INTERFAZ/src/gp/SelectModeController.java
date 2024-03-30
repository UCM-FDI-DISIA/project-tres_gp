package gp;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import gp.GameObjects.Piece;
import gp.exceptions.FullColumnException;
import gp.logic.*;
import gp.view.Messages;

public class SelectModeController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	@FXML
	private Button btnVolver;
	
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
    private GridPane gridPane;
    
    public SelectModeController() {
    	this.game = new Game();
    }

    @FXML
    void switchToTableroNormal(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TABLERO MEJOR DISEÑO.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void colocarFicha(MouseEvent event) throws FullColumnException {
        //if (event.getSource() instanceof Button) {
            int columna = GridPane.getColumnIndex((Node)event.getSource());
            
            try {
                Parent ficha = FXMLLoader.load(getClass().getResource(
                		"FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
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
    void switchToTablero5en(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TABLERO 5 IN ROW.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void onMouseEntered(MouseEvent event) {
        // Código para el efecto al entrar con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.35); // Cambiar la opacidad para simular una luz encendida
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        // Código para revertir el efecto al salir con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.0); // Restaurar la opacidad original para apagar la "luz"
    }

    @FXML
    void switchToScene2(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SEGUNDA PORTADA.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
