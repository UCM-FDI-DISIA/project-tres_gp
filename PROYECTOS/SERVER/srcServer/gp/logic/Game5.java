package gp.logic;

import java.io.IOException;

import gp.GameObjects.Piece;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Game5 extends Game{
	public  final static int DIM_X = 9;
	
	public Game5() {
		super();
	}
	
	public void fiveInRow (GridPane gridPane){
		if (super.currentCycle == 0) {
			int random = super.generateRandomNumber(1,2);
			if (random == 1) {
				placeRow(1,2, gridPane);
			}
			else placeRow(2,1, gridPane);
		}
	}
	
	public void placeRow(int first, int second, GridPane gridPane) {
		for (int i = DIM_Y - 1; i >= 0; i--) {
            Parent ficha;
			if (i % 2 == 0) {

				try {
					ficha = FXMLLoader.load(getClass().getResource(
							"/gp/cincoenRaya/FICHA J1 5 IN ROW.fxml"));
	                gridPane.add(ficha, 1, i + 2);
					ficha = FXMLLoader.load(getClass().getResource(
							"/gp/cincoenRaya/FICHA J2 5 IN ROW.fxml"));
	                gridPane.add(ficha, DIM_X, i + 2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				addObject(new Piece(this, new Position(0, i), first));
				addObject(new Piece(this, new Position(DIM_X, i), second));
			}
			else {
				try {
					ficha = FXMLLoader.load(getClass().getResource(
							"/gp/cincoenRaya/FICHA J2 5 IN ROW.fxml"));
	                gridPane.add(ficha, 1, i + 2);
					ficha = FXMLLoader.load(getClass().getResource(
							"/gp/cincoenRaya/FICHA J1 5 IN ROW.fxml"));
	                gridPane.add(ficha, DIM_X, i + 2);
				} catch (IOException e) {
					e.printStackTrace();
				}                
				addObject(new Piece(this, new Position(1, i), second));
				addObject(new Piece(this, new Position(DIM_X, i),first ));
			}
		}
	}
	
}
