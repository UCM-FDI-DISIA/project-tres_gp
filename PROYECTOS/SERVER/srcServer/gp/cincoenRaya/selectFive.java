package gp.cincoenRaya;

import java.io.IOException;
import java.util.List;

import gp.clasico.selectClassic;
import gp.logic.Game5;
import gp.logic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class selectFive extends selectClassic {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game5 game;
	
	
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
    private Button btnStart;

    @FXML
    private MenuItem btnVolver;
    
    @FXML
    private MenuItem btnReset;
    
    @FXML
    private GridPane gridPane;

    @FXML
    private MenuButton menuButton;
    
    public selectFive() {
    	this.game = new Game5();    	
    }
    @FXML
    private void colocarFicha(MouseEvent event) throws IOException{
    	if(!super.isFinished) {
    		Node obt = (Node)event.getSource();
    		obt = obt.getParent();
            int columna = GridPane.getColumnIndex(obt);
            
            try {
                Parent ficha = FXMLLoader.load(getClass().getResource(
                		"FICHA J%s 5 IN ROW.fxml".formatted(game.getTurn())));
                int fila = game.place(columna);
                gridPane.add(ficha, columna, fila + 2);
                
                List<Position> free = game.getFreePositions();
            	if (free.isEmpty()) {
            		isFinished =  true;
            	}
        		if(game.someoneWin5()) {
        			super.isFinished = true;
        			showWinners(gridPane);
        		}
        		else {game.flip();}
            } 
            catch (IOException e) {e.printStackTrace();}
    	}
    	else super.endMessage();
    }
    
    protected void showWinners(GridPane gridPane) throws IOException {
    	List<List<Position>> winners = game.getWinners();
    	for (List<Position> winner : winners) {
    	    for (Position pos : winner) {
            	Parent fichaGanadora = FXMLLoader.load(getClass().getResource("/gp/FICHA GANADORA.fxml"));
    	        int fila = pos.getRow();
    	        int columna = pos.getCol();
    	        gridPane.add(fichaGanadora, columna, fila +2);
    	    }
    	}
    }
    
    @FXML
    private void configurarTablero(ActionEvent event) {
    	game.fiveInRow(gridPane);
    }
    @FXML
    protected void onMouseEntered(MouseEvent event) {
        // Código para el efecto al entrar con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.45); // Cambiar la opacidad para simular una luz encendida
    }

    @FXML
    protected void onMouseExited(MouseEvent event) {
        // Código para revertir el efecto al salir con el mouse
        Button button = (Button) event.getSource();
        button.setOpacity(0.0); // Restaurar la opacidad original para apagar la "luz"
    }
    
	@FXML
	protected void switchToScene2(MouseEvent event) throws IOException {
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
	protected void switchToScene2Menu(ActionEvent event) throws IOException {
	    MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
	    Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
	    Scene scene = parent.getScene(); // Obtener la escena
	    Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
	    root = FXMLLoader.load(getClass().getResource("/gp/SEGUNDA PORTADA.fxml"));
	    stage.setScene(new Scene(root));
	    stage.show();
	}

}
