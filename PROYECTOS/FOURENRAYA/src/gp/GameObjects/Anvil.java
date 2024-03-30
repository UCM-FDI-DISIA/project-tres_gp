package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public class Anvil extends SuperPiece {
	public Anvil(Game game, Position pos) {
		super(game, pos);
	}
	public Anvil() {
		super();
	}
	
	public String toString() {
		return Messages.ANVIL;
	}
}
