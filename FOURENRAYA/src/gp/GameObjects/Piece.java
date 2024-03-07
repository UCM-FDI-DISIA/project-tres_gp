package gp.GameObjects;

import gp.exceptions.FullColumnException;
import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Piece extends GameObject{

	public Piece(Game game, Position pos) {
		super(game, pos);
	}
	public Piece() {
		super();
	}
	public String toString() {
		int turno = game.getTurno();
		if (turno == 1) 
			return Messages.FICHA1;
		else
			return Messages.FICHA2;
	}

	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}
}
