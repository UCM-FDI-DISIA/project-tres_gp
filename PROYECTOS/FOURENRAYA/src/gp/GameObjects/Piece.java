package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Piece extends GameObject{
	public Piece(Game game, Position pos) {
		super(game, pos);
	}
	
	public Piece(Game game, Position pos, int turn) {
		super(game, pos, turn);
	}
	
	public Piece() {
		super();
	}
	public String toString() {
		if (turn == 1) return Messages.FICHA1;
		else return Messages.FICHA2;
	}

	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
