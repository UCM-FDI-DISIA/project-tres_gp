package gp.logic;

import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.view.Messages;

public class Game {
	public static final int DIM_X = 7;
	public static final int DIM_Y = 6;
	private int currentCycle;
	private GameObjectContainer container;
	private int turn = 1;
	private boolean doExit = false;
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
		flip();
		currentCycle++;
	}
	
	private void flip() {
		if(turn ==1)
			turn = 2;
		else
			turn = 1;
	}
	
	public void place(int col) throws OffWorldException, FullColumnException{
		if(isOnBoard(col)) {
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Piece(this, pos));
		}
		else
			throw new OffWorldException(Messages.OFF_WORLD_MESSAGE.formatted(col));
	}
	public boolean isOnBoard(int col) {
		return (col >= 0) && (col < Game.DIM_X );
	}
	public int findRow(int col) throws FullColumnException{
		return container.findRow(col);		
	}
	public void addObject(GameObject object) {
		container.add(object);
		
	}

	public void exit() {
		doExit = true;
		System.out.println(Messages.GAME_OVER);
	}
	public boolean isFinished() {
		return doExit || container.isFinished(turn);
	}

	public int getTurn() {
		return turn;
	}
	public void reset() {
		container = new GameObjectContainer();
		currentCycle = 0;
		turn = 1;
	}
	
}
