package gp.control;

import java.util.Scanner;

import gp.commands.Command;
import gp.commands.CommandGenerator;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.BoardPrinter;
import gp.view.GamePrinter;
import gp.view.Messages;

public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new BoardPrinter(game);
	}

	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(Messages.debug(line));

		return words;
	}

	public void run()  throws CommandParseException, CommandExecuteException{

		printGame();

		while (!game.isFinished()) {
			String[] parameters = prompt();
			try {
				Command command = CommandGenerator.parse(parameters);
	
				if (command != null) {
					boolean shouldDraw = command.execute(game);
					if (shouldDraw) {
						printGame();
					} 
					
				} else {
					 throw new CommandParseException(Messages.UNKNOWN_COMMAND);
				}
			}
			catch (CommandParseException | CommandExecuteException e) {
	            System.out.println(e.getMessage());
	            Throwable cause = e.getCause();
	            if (cause != null) {
	                System.out.println(cause.getMessage());
	            }
	        }
		}

		printEndMessage();
	}
	

	
	/**
	 * Draws/prints the game
	 */
	private void printGame() {
		System.out.println(printer);
	}
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

}
