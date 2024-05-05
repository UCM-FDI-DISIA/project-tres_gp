package gp.superfichas;


import java.io.IOException;
import java.util.List;

import gp.clasico.selectClassic;
import gp.logic.Game;
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

public class SuperfichaController extends selectClassic {
	private Parent root;
	private Game game;
	private boolean Bomb;
	private boolean Anvil;
	private boolean Arrow;
	private int contBomb1 = 2;
	private int contBomb2 = 2;
	private int contAnvil1 = 2;
	private int contAnvil2 = 2;
	private int contArrow1 = 2;
	private int contArrow2 = 2;
	
    @FXML
    private Button btnBomba1;
    @FXML
    private GridPane gridPane;

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
    private MenuItem btnVolver;
    
    @FXML
    private MenuItem btnReset;

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
    private void colocarFicha(MouseEvent event) throws IOException{
    	if (!super.isFinished) {
    		Node source = (Node) event.getSource();
    	    Node parent = source;
    	    parent = parent.getParent();
    	    GridPane gridPane = (GridPane) parent.getParent();
    	    Integer columnaInteger = GridPane.getColumnIndex(parent);

    	    // Verificar si la columna es null y asignar 0 como valor predeterminado
    	    int columna = (columnaInteger != null) ? columnaInteger : 0;

    	    try {
    	    	if(Bomb == true) {
    	    		game.bomb(columna, gridPane);
    	    		Bomb = false;
    	    		if(game.getTurn() == 1)
    	    			contBomb1--;
    	    		else
    	    			contBomb2--;
    	    	}
    	    	else if(Arrow == true) {
    	    		game.arrow(columna, gridPane);
    	    		Arrow = false;
    	    		if(game.getTurn() == 1)
    	    			contArrow1--;
    	    		else
    	    			contArrow2--;
    	    	}
    	    	else if(Anvil == true) {
    	    		game.anvil(columna, gridPane);
    	    		Anvil = false;
    	    		if(game.getTurn() == 1)
    	    			contAnvil1--;
    	    		else
    	    			contAnvil2--;
    	    	}
    	    	else {
    		        // Cargamos la ficha
    		        Parent ficha = FXMLLoader.load(getClass().getResource("/gp/FICHA JUGADOR %s.fxml".formatted(game.getTurn())));
    		        int fila = game.place(columna); // Suponemos que esto coloca la ficha lógicamente y devuelve la fila donde se colocó
    		        gridPane.add(ficha, columna, fila); // Añadimos la ficha físicamente al GridPane
    	    	}
    	    	
    	    	List<Position> free = game.getFreePositions();
            	if (free.isEmpty()) {
            		isFinished =  true;
            	}
    	    	if (game.someoneWin()) { // Si alguien gana después de colocar la ficha
    	    		super.isFinished = true;
                    showWinners(gridPane);
    	        }
    	    	else game.flip(); // Actualiza el estado del juego
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
    	        gridPane.add(fichaGanadora, columna, fila);
    	    }
    	}
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
    void switchToTableroSuperFichas(ActionEvent event) throws IOException {
	    MenuItem menuItem = (MenuItem) event.getSource(); // Obtener el MenuItem
	    Parent parent = (Parent) menuItem.getParentPopup().getOwnerNode(); // Obtener el nodo padre del menú emergente
	    Scene scene = parent.getScene(); // Obtener la escena
	    Stage stage = (Stage) scene.getWindow(); // Obtener el Stage
	    root = FXMLLoader.load(getClass().getResource("TABLERO SUPERFICHAS SELECCION.fxml"));
	    stage.setScene(new Scene(root));
	    stage.show();
    }
    
    @FXML
    void bombButton(MouseEvent event) {
    	if (Bomb)
    		Bomb = false;
    	else
    		Bomb = true;
    	if(game.getTurn() == 1 && contBomb1 == 0)
    		Bomb = false;
    	if(game.getTurn() == 2 && contBomb2 == 0)
    		Bomb = false;
    	Anvil = false;
    	Arrow = false;
    }
    @FXML
    void anvilButton(MouseEvent event) {
    	Bomb = false;
    	if (Anvil)
    		Anvil = false;
    	else
    		Anvil = true;
    	if(game.getTurn() == 1 && contAnvil1 == 0)
    		Anvil = false;
    	if(game.getTurn() == 2 && contAnvil2 == 0)
    		Anvil = false;
    	Arrow = false;
    }
    @FXML
    void arrowButton(MouseEvent event) {
    	Bomb = false;
    	Anvil = false;
    	if (Arrow)
    		Arrow = false;
    	else
    		Arrow = true;
    	if(game.getTurn() == 1 && contArrow1 == 0)
    		Arrow = false;
    	if(game.getTurn() == 2 && contArrow2 == 0)
    		Arrow = false;
    }

}


