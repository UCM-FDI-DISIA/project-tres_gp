package gp;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
		root = FXMLLoader.load(getClass().getResource("TABLERO BÁSICO.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
        Node node = (Node) event.getSource();
        node.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        // Código para revertir el efecto al salir con el mouse
        Node node = (Node) event.getSource();
        node.setStyle("-fx-background-color: transparent;");
    }
}
