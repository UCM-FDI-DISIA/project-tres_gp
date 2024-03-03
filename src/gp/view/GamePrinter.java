package gp.view;


import gp.logic.Game;

public abstract class GamePrinter {
	
	protected Game game;
	
	public GamePrinter(Game game) {
		this.game = game;
	}

	public abstract String endMessage();
	
}