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
    private void colocarFicha(MouseEvent event){
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
    void switchToScene2(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SEGUNDA PORTADA.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
