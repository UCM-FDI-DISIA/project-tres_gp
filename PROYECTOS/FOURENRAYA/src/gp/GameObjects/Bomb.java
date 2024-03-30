package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Bomb extends SuperPiece {
	public Bomb(Game game, Position pos) {
		super(game, pos);
	}
	public Bomb() {
		super();
	}
	
	public String toString() {
		return Messages.BOMB;
	}

}
