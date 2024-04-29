package gp.cincoenRaya;

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

public class selectFive {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	
	
    @FXML
    private MenuItem btnEmpieza;
    
    @FXML
    private Button btnFicha;
    
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
    private MenuItem btnVolver;
    
    @FXML
    private MenuItem btnReset;
    
    @FXML
    private GridPane gridPane;

    @FXML
    private MenuButton menuButton;
    
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
                gridPane.add(ficha, columna, fila + 2);
        		if(game.someoneWin5()) {
        			System.out.println("Gana el Jugador%s".formatted(game.getTurn()));
        			Parent alertRoot = FXMLLoader.load(getClass().getResource("/gp/clasico/VOLVER A INICIAL.fxml"));
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
    private void configurarTablero(ActionEvent event) {
    	game.fiveInRow(gridPane);
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
	void switchToScene2(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

    @FXML
    void switchToTablero5INROW(ActionEvent event) throws IOException {
	    MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
	    Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
	    Scene scene = parent.getScene(); // Obtener la escena
	    Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
	    root = FXMLLoader.load(getClass().getResource("TABLERO 5 IN ROW.fxml"));
	    stage.setScene(new Scene(root));
	    stage.show();
    }
    
	@FXML
	void switchToScene2Menu(ActionEvent event) throws IOException {
	    MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
	    Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
	    Scene scene = parent.getScene(); // Obtener la escena
	    Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
	    root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
	    stage.setScene(new Scene(root));
	    stage.show();
	}

}
