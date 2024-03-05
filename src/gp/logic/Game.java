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
	private Piece piece;
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
		currentCycle++;
	}
	
	public void place(int col) throws OffWorldException, FullColumnException{
		if(isOnBoard(col)) {
			piece = new Piece();
			piece.place(col);
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
}