package gp.logic;

import java.io.IOException;
import java.util.Random;


import gp.GameObjects.Anvil;
import gp.GameObjects.Arrow;
import gp.GameObjects.Bomb;
import gp.GameObjects.GameObject;
import gp.GameObjects.Ice;
import gp.GameObjects.Piece;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Game {
	public static int DIM_X = 7;
	public static final int DIM_Y = 6;
	private int currentCycle;
	private GameObjectContainer container;
	private int turn = 1;
	private boolean doExit = false;
	private boolean someoneWin = false;
	public Game() {
		container = new GameObjectContainer();
		currentCycle = 0;
	}
	public String positionToString(int col, int row) {
		return container.toString(new Position(col, row));
	}
	public int getCycle() {
		return currentCycle;
	}
	
	public void update() {
		if (!someoneWin()) {
			flip();
			currentCycle++;
		}
	}
	
	public void fiveInRow (GridPane gridPane){
		if (currentCycle == 0) {
			DIM_X = 9;
			int random = generateRandomNumber(1,2);
			if (random == 1) {
				placeRow(1,2, gridPane);
			}
			else placeRow(2,1, gridPane);
		}
	}
	
	public void placeRow(int first, int second, GridPane gridPane) {
		for(int j = 0; j<2;j++) {
			for (int i = DIM_Y - 1; i >= 0; i--) {
	            Parent ficha;
				if (i % 2 == 0) {
	
					try {
						ficha = FXMLLoader.load(getClass().getResource(
								"/gp/cincoenRaya/FICHA J1 5 IN ROW.fxml"));
		                gridPane.add(ficha, 0 + j, i);
						ficha = FXMLLoader.load(getClass().getResource(
								"/gp/cincoenRaya/FICHA J1 5 IN ROW.fxml"));
		                gridPane.add(ficha, DIM_X + j, i);
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
		                gridPane.add(ficha, 0 + j, i);
						ficha = FXMLLoader.load(getClass().getResource(
								"/gp/cincoenRaya/FICHA J2 5 IN ROW.fxml"));
		                gridPane.add(ficha, DIM_X + j, i);
					} catch (IOException e) {
						e.printStackTrace();
					}                
					addObject(new Piece(this, new Position(0, i), second));
					addObject(new Piece(this, new Position(DIM_X - 1, i), first));
				}
			}
		}
	}
	
	public void popOut(int col){
		if(isOnBoard(col)) {
			container.popOut(col);
		}
	}
	
	private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
	
	private void flip() {
		if(turn ==1)
			turn = 2;
		else
			turn = 1;
	}
	
	public int place(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Piece(this, pos));
			return row;
	}
	
	public void bomb(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Bomb(this, pos));
			container.bomb(pos);
	}
	
	public void anvil(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Anvil(this, pos));
			container.anvil(pos);
	}
	
	public void arrow(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Arrow(this, pos));
			container.arrow(pos);
	}
	
	public void ice(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Ice(this, pos));
			if (!someoneWin()) {
				currentCycle++;
			}
			container.ice(pos);

	}
	
	
	public boolean isOnBoard(int col) {
		return (col >= 0) && (col < Game.DIM_X );
	}
	public int findRow(int col){
		return container.findRow(col);		
	}
	public void addObject(GameObject object) {
		container.add(object);
		
	}
	
	public void remove(GameObject object) {
		container.remove(object);
	}

	public void exit() {
		doExit = true;
	}
	
	public boolean isFinished() {
		return doExit || someoneWin;
	}
	
	public boolean someoneWin() {
		if (container.isFinished(turn)) someoneWin = true;
		return someoneWin;
	}

	public int getTurn() {
		return turn;
	}
	
	public void reset() {
		currentCycle = 0;
		container.reset();
	}


	
}
