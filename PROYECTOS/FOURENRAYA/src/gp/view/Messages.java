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
		
	public static final String COMMAND_RESET_NAME = "reset";
	public static final String COMMAND_RESET_SHORTCUT = "r";
	public static final String COMMAND_RESET_DETAILS = "[r]eset [filename]";
	public static final String COMMAND_RESET_HELP = "resets the game using the initial configuration in file filename. If no file is given the standard configuration is used";
	
	public static final String POSITION = "(%s, %s)";
	public static final String OFF_WORLD_MESSAGE = "Column %s is off board";
	public static final String COMMAND_INCORRECT_PARAMETER_NUMBER = "Incorrect parameter number";
	public static final String UNKNOWN_COMMAND = "Unknown command";
	public static final String FULL_COLUMN_MESSAGE = "Column %s is full";
	public static final String GAME_OVER = "Game Over";
	public static final String ERROR = "[ERROR] Error: %s%n";

	
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

