package gp.view;


import gp.logic.Game;

public abstract class GamePrinter {
	
	protected Game game;
	
	public GamePrinter(Game game) {
		this.game = game;
	}
	public String whowins() {
		if (game.getTurn() == 1){
			return Messages.PLAYER_1_WINS;
		}
		else {
			return Messages.PLAYER_2_WINS;
		}
	}
	public abstract String endMessage();
	
}