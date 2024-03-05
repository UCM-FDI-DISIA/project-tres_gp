package gp.commands;

import gp.logic.Game;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.view.Messages;

public class PlaceCommand extends Command {

	public PlaceCommand() {
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_PLACE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_PLACE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_PLACE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_PLACE_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean execution = true;
		try {
			game.place(0);//solucionatlo
			return execution;
		}
		catch(OffWorldException | FullColumnException e){
			throw new CommandExecuteException("",e);
		}
	}

	@Override
	public Command parse(String[] commandWords)  throws CommandParseException{
		int col;
		if (matchCommandName(commandWords[0])) {
	 		if(commandWords.length == 2) {
	 			try {
	 				col = Integer.parseInt(commandWords[1]);
	 				return this;
	 			}
	 			catch (IllegalArgumentException e) {
	 				throw new CommandParseException(Messages.OFF_WORLD_MESSAGE + commandWords[1]);
	 			}
	 		} else throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	} else return null;
	}

}
