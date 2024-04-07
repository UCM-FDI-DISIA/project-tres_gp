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
import gp.logic.*;

public class SelectModeController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button btn5en;

    @FXML
    private Button btnClasico;

    @FXML
    private Button btnSuper;

    @FXML
    private Button btnpop;
    
    @FXML
    void switchToTableroNormal(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/clasico/TABLERO MEJOR DISENO.fxml"));
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
    void switchToTablero5en(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/cincoenRaya/TABLERO 5 IN ROW.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    } 
    @FXML
    void switchToTableroSuperFichas(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/superfichas/TABLERO SUPERFICHAS SELECCION.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    @FXML
    void switchToTableroPopOut(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/popOut/TABLERO POP-OUT.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
