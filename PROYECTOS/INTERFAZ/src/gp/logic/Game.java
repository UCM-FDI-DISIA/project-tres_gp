package gp.logic;

import java.util.Random;


import gp.GameObjects.Anvil;
import gp.GameObjects.Arrow;
import gp.GameObjects.Bomb;
import gp.GameObjects.GameObject;
import gp.GameObjects.Ice;
import gp.GameObjects.Piece;

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
	
	public void fiveInRow (){
		if (currentCycle == 0) {
			DIM_X = 9;
			int random = generateRandomNumber(1,2);
			if (random == 1) {
				placeRow(1,2);
			}
			else placeRow(2,1);
		}
	}
	
	public void placeRow(int first, int second) {
		for (int i = DIM_Y - 1; i >= 0; i--) {
			if (i % 2 == 0) {
				addObject(new Piece(this, new Position(0, i), first));
				addObject(new Piece(this, new Position(DIM_X - 1, i), second));
			}
			else {
				addObject(new Piece(this, new Position(0, i), second));
				addObject(new Piece(this, new Position(DIM_X - 1, i), first));
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
