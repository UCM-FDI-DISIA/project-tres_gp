package gp.GameObjects;

import gp.logic.Game;
import gp.logic.Position;

public class SuperPiece extends Piece {
	public SuperPiece(Game game, Position pos) {
		super(game, pos);
	}
	public SuperPiece() {
		super();
	}

	public void die() {
		game.addObject(new Piece(game, pos));
		game.remove(this);
	}
	
}
