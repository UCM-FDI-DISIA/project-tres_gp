package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Ice extends SuperPiece {
	public Ice(Game game, Position pos) {
		super(game, pos);
	}
	public Ice() {
		super();
	}
	
	public String toString() {
		return Messages.ICE;
	}

}
