package gp.superfichas;


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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SuperfichaController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	private boolean Bomb;
	private boolean Anvil;
	private boolean Arrow;
	private boolean Ice;
    @FXML
    private Button btnBomba1;

    @FXML
    private Button btnBomba2;

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
    private Button btnFlecha1;

    @FXML
    private Button btnFlecha2;

    @FXML
    private Button btnHielo1;

    @FXML
    private Button btnHielo2;

    @FXML
    private MenuItem btnVolver;

    @FXML
    private Button btnYunque1;

    @FXML
    private Button btnYunque2;

    @FXML
    private MenuButton menuButton;
    
    public SuperfichaController() {
    	this.game = new Game();
    }
    @FXML
    private void colocarFicha(MouseEvent event){
    	Node source = (Node) event.getSource();
	    Node parent = source;
	    parent = parent.getParent();
	    GridPane gridPane = (GridPane) parent.getParent();
	    Integer columnaInteger = GridPane.getColumnIndex(parent);

	    // Verificar si la columna es null y asignar 0 como valor predeterminado
	    int columna = (columnaInteger != null) ? columnaInteger : 0;

	    try {
	    	if(Bomb == true) {
	    		game.bomb(columna);
	    		//Algo así en todas
	    		Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA BOMBA J%s.fxml".formatted(game.getTurn())));
	    		//gridPane.add(ficha, columna, fila);
	    		Bomb = false;
	    	}
	    	else if(Arrow == true) {
	    		game.arrow(columna);
	    		Arrow = false;
	    	}
	    	else if(Anvil == true) {
	    		game.anvil(columna);
	    		Anvil = false;
	    	}
	    	else if(Ice ==true) {
	    		game.ice(columna);
	    		Ice = false;
	    		
	    	}
	        // Cargamos la ficha
	        Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
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
    void bombButton() {
    	Bomb = true;
    	Anvil = false;
    	Arrow = false;
    	Ice = false;
    }
    @FXML
    void anvilButton() {
    	Bomb = false;
    	Anvil = true;
    	Arrow = false;
    	Ice = false;
    }
    @FXML
    void iceButton() {
    	Bomb = false;
    	Anvil = false;
    	Arrow = false;
    	Ice = true;
    }
    @FXML
    void arrowButton() {
    	Bomb = false;
    	Anvil = false;
    	Arrow = true;
    	Ice = false;
    }

}
