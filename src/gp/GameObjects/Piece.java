package gp.GameObjects;

import gp.exceptions.FullColumnException;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Piece extends GameObject{
	public boolean free = true;
	private Game game;
	public Piece(Game game, Position pos) {
		this.game = game;
	}
	public Piece() {
		super();
	}
	public String toString(GameObject c, Position p, int turno) {
		if (turno == 1) 
			return Messages.FICHA1;
		else
			return Messages.FICHA2;
	}

	public void place(int col) throws FullColumnException{
		if(free) {
			int row = findRow(col);
			free = false;
			Position pos = new Position(col, row);
			game.addObject(new Piece(game, pos));
		}		
	}
	
	private int findRow(int col) throws FullColumnException{
		return game.findRow(col);		
	}
	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(GameObject c, Position position) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
