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
	
	// Serializa esta Piece a un String
    public String serialize() {
        return pos.getCol() + "," + pos.getRow() + "," + turn;
    }

    // Deserializa un String a un Piece
    public static Piece deserialize(Game game, String data) {
        String[] parts = data.split(",");
        int col = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        int turn = Integer.parseInt(parts[2]);
        return new Piece(game, new Position(col, row), turn);
    }
    public static Position deserializePos(Game game, String data) {
        String[] parts = data.split(",");
        int col = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        return new Position(col, row);
    }
    public static int deserializeTurn(Game game, String data) {
        String[] parts = data.split(",");
        int turn = Integer.parseInt(parts[2]);
        return turn;
    }

}
