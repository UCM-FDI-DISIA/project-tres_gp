package gp.GameObjects;


import gp.logic.Game;
import gp.logic.Position;

public abstract class GameObject {
	protected Position pos;
	protected Game game;
	protected int turn;
	
	public GameObject(Game game, Position pos) {	
		this.pos = pos;
		this.game = game;
		turn = game.getTurn();
	}
	
	public GameObject(Game game, Position pos, int turn) {	
		this.pos = pos;
		this.game = game;
		this.turn = turn;
	}
	
	public GameObject() {}
	
	public Position getPosition() {return pos;}
	
	public boolean isOnPosition(Position pos) {
		return this.getPosition().equals(pos);
	}
	
	public void die() {};
	
	public int getTurn() {return turn;}
	
	protected abstract String getSymbol();
	public abstract String toString();
	
}
