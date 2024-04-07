package gp.GameObjects;

import gp.logic.Game;

import gp.logic.Position;



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
}
