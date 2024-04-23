package gp.GameObjects;


import gp.logic.Game;
import gp.logic.Position;
import gp.view.Messages;

public abstract class GameObject {
	protected Position pos;
	protected Game game;
	protected int turn;
	private boolean winner = false;

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
	
	public String toString() {
		if (winner) return Messages.WIN;
		else if (turn == 1) return Messages.FICHA1;
		else return Messages.FICHA2;
	}
	
	public void changeWinner() {
		winner = true;
	}
	
}
