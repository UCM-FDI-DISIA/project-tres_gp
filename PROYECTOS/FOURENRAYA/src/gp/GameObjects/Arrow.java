package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Arrow extends SuperPiece {
	public Arrow(Game game, Position pos) {
		super(game, pos);
	}
	public Arrow() {
		super();
	}
	
	public String toString() {
		return Messages.ARROW;
	}

}