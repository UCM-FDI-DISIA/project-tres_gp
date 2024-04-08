package gp.popOut;


import java.io.IOException;
import gp.GameObjects.Piece;
import gp.logic.Game;
import gp.logic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class popOutController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	private boolean Bomb;
	private boolean Anvil;
	private boolean Arrow;
	private boolean Ice;
	@FXML
	private GridPane gridPane;
	@FXML
	private MenuItem btnReset;
	
	@FXML
    private Button btnDetras;

    @FXML
    private Button btnDetras1;

    @FXML
    private Button btnDetras2;

    @FXML
    private Button btnDetras3;

    @FXML
    private Button btnDetras4;

    @FXML
    private Button btnDetras5;

    @FXML
    private Button btnDetras6;
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
    private Button btnFicha51;
    @FXML
    private MenuButton menuButton;
    
    public popOutController() {
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

        // Cargamos la ficha
        Parent ficha;
        try {
            ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
            int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
            
            Position pos = new Position(columna, fila);
            game.addObject(new Piece(game, pos));

            // Obtener el índice del nodo que desencadena el evento
            int index = gridPane.getChildren().indexOf(source);

            // Insertamos la ficha justo antes del nodo que desencadena el evento
            gridPane.add(ficha, columna, fila, 1, 1);
            
            if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
                System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
                // Mostrar una alerta o pantalla de victoria
                Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
                gridPane.add(alertRoot, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
                GridPane.setHalignment(alertRoot, HPos.CENTER);
                GridPane.setValignment(alertRoot, VPos.CENTER);
            }
            game.update(); // Actualiza el estado del juego    
        } catch (IOException e) {
            // Manejar la excepción adecuadamente
            e.printStackTrace();
        }
    }


    @FXML
    void popOut(MouseEvent event) {
    	Node source = (Node) event.getSource();
	    Node parent = source;
	    parent = parent.getParent();
	    GridPane gridPane = (GridPane) parent.getParent();
	    Integer columnaInteger = GridPane.getColumnIndex(parent);

	    // Verificar si la columna es null y asignar 0 como valor predeterminado
	    int columna = (columnaInteger != null) ? columnaInteger : 0;
    	game.popOut(columna, gridPane);
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
    
    @FXML
    void switchToScene2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void reset(ActionEvent event) {
        if (gridPane != null) {
            game.reset(gridPane);
        } else {
            System.out.println("GridPane no encontrado.");
        }
    }

}
