package gp.view;

public class Messages {
	//Info
	public static final String GAME_NAME = "Cuatro en Raya Fury";
	public static final String CLASSIC = "Clásico";
	public static final String WELCOME = String.format("%s %s%n", GAME_NAME, CLASSIC);
	public static final String PROMPT = "Command > ";
	public static final String LINE_SEPARATOR = System.lineSeparator();
	public static final String HELP_AVAILABLE_COMMANDS = "Available commands: ";
	public static final String PLAYER_WINS = "Player %s win!";
	public static final String PLAYER_QUITS = "Player %s leaves the game";
	public static final String DEBUG = "%n[DEBUG] Executing: %s%n";
	public static final String NUMBER_OF_CYCLES = "Number of cycles:";
	
	//GameObject
	public static final String FICHA1 = "X";
	public static final String FICHA2 = "O";
	public static final String BOMB = "*";
	public static final String ANVIL = "+";
	public static final String ARROW = "➤";
	public static final String ICE = "❏";
		
	//Commands
	
	public static final String COMMAND_EXIT_NAME = "exit";
	public static final String COMMAND_EXIT_SHORTCUT = "e";
	public static final String COMMAND_EXIT_DETAILS = "[e]xit";
	public static final String COMMAND_EXIT_HELP = "exits the game";

	public static final String COMMAND_HELP_NAME = "help";
	public static final String COMMAND_HELP_SHORTCUT = "h";
	public static final String COMMAND_HELP_DETAILS = "[h]elp";
	public static final String COMMAND_HELP_HELP = "shows this help";
		
	public static final String COMMAND_PLACE_NAME = "place";
	public static final String COMMAND_PLACE_SHORTCUT = "p";
	public static final String COMMAND_PLACE_DETAILS = "[p]lace col";
	public static final String COMMAND_PLACE_HELP = "places the piece in the indicated column";
	
	public static final String COMMAND_BOMB_NAME = "bomb";
	public static final String COMMAND_BOMB_SHORTCUT = "b";
	public static final String COMMAND_BOMB_DETAILS = "[b]omb col";
	public static final String COMMAND_BOMB_HELP = "remove all adjacent pieces";
	
	public static final String COMMAND_ANVIL_NAME = "anvil";
	public static final String COMMAND_ANVIL_SHORTCUT = "a";
	public static final String COMMAND_ANVIL_DETAILS = "[a]nvil col";
	public static final String COMMAND_ANVIL_HELP = "remove all pieces of the column";
	
	public static final String COMMAND_ARROW_NAME = "arrow";
	public static final String COMMAND_ARROW_SHORTCUT = "w";
	public static final String COMMAND_ARROW_DETAILS = "arro[w] col";
	public static final String COMMAND_ARROW_HELP = "move one row to the left";
	
	public static final String COMMAND_ICE_NAME = "ice";
	public static final String COMMAND_ICE_SHORTCUT = "i";
	public static final String COMMAND_ICE_DETAILS = "[i]ce col";
	public static final String COMMAND_ICE_HELP = "freeze your opponent's turn";
		
	public static final String COMMAND_RESET_NAME = "reset";
	public static final String COMMAND_RESET_SHORTCUT = "r";
	public static final String COMMAND_RESET_DETAILS = "[r]eset";
	public static final String COMMAND_RESET_HELP = "resets the game using the initial configuration in file filename. If no file is given the standard configuration is used";
	
	public static final String COMMAND_5ROW_NAME = "fiveinrow";
	public static final String COMMAND_5ROW_SHORTCUT = "f";
	public static final String COMMAND_5ROW_DETAILS = "[f]ive in row";
	public static final String COMMAND_5ROW_HELP = "change to 5 in Row Mode";
	
	public static final String COMMAND_POPOUT_NAME = "popout";
	public static final String COMMAND_POPOUT_SHORTCUT = "o";
	public static final String COMMAND_POPOUT_DETAILS = "pop [o]ut";
	public static final String COMMAND_POPOUT_HELP = "change to Pop Out Mode";

	
	public static final String POSITION = "(%s, %s)";
	public static final String OFF_WORLD_MESSAGE = "Column %s is off board";
	public static final String COMMAND_INCORRECT_PARAMETER_NUMBER = "Incorrect parameter number";
	public static final String UNKNOWN_COMMAND = "Unknown command";
	public static final String FULL_COLUMN_MESSAGE = "Column %s is full";
	public static final String NO_WIN_MESSAGE= "you cannot use this super piece to win";
	public static final String GAME_OVER = "Game Over";
	public static final String ERROR = "[ERROR] Error: %s%n";
	public static final String CANTCHANGEMODE = "You cannot change modes because you are already playing a game";
	public static final String EMPTY_COLUMN_MESSAGE = "[Column %s is empty";
	public static final String OPPONENT_PIECE_MESSAGE = "This piece belongs to your opponent, you cannot pop out it";

	
	public static final String debug(String message) {
		return Messages.DEBUG.formatted(message);
	}
	public static final String error(String message) {
		return Messages.ERROR.formatted(message);
	}
	
	public static final String win(int turn) {
		return Messages.PLAYER_WINS.formatted(turn);
	}
	
	public static final String exit(int turn) {
		return Messages.PLAYER_QUITS.formatted(turn);
	}

}

