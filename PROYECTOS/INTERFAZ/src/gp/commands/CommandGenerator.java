package gp.commands;

import java.util.Arrays;
import java.util.List;
import gp.exceptions.CommandParseException;
import gp.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new PlaceCommand(),
		new ExitCommand()
	);
	
	public static Command parse(String[] commandWords) throws CommandParseException{	
		Command command = null;
		for (Command c: availableCommands) {
			if(c.matchCommandName(commandWords[0])) {
				command=c.parse(commandWords);
			}
		}
		if(command == null) {
			 throw new CommandParseException(Messages.UNKNOWN_COMMAND);
		}
		return command;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			commands.append(c.helpText());
			commands.append(System.lineSeparator());
		}
		return commands.toString();
	}

}