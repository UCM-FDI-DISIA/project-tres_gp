package gp.clasico;

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

public class selectClassic {
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

	public selectClassic() {
		this.game = new Game();
	}

	@FXML
	private void colocarFicha(MouseEvent event) {
	    Node source = (Node) event.getSource();
	    Node parent = source;
	    parent = parent.getParent();
	    GridPane gridPane = (GridPane) parent.getParent();
	    Integer columnaInteger = GridPane.getColumnIndex(parent);

	    // Verificar si la columna es null y asignar 0 como valor predeterminado
	    int columna = (columnaInteger != null) ? columnaInteger : 0;

	    try {
	        // Cargamos la ficha
	        Parent ficha = FXMLLoader.load(getClass().getResource("FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
	        int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
	        gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
	        Position pos = new Position(columna, fila);
	        game.addObject(new Piece(game, pos));
	        if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
	            System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
	            // Mostrar una alerta o pantalla de victoria
	            Parent alertRoot = FXMLLoader.load(getClass().getResource("VOLVER A INICIAL.fxml"));
	            gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
	            GridPane.setHalignment(alertRoot, HPos.CENTER);
	            GridPane.setValignment(alertRoot, VPos.CENTER);
	        }
	        game.update(); // Actualiza el estado del juego
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	@FXML
	void switchToScene2(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
}
