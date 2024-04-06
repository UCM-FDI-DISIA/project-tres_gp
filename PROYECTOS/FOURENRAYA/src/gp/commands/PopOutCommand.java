package gp.commands;

import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.exceptions.EmptyColumnException;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.logic.Game;
import gp.view.Messages;

public class PopOutCommand extends Command {
	private int col;
	public PopOutCommand() {
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_POPOUT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_POPOUT_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_POPOUT_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_POPOUT_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean execution = true;
		try {
			game.popOut(col);
			game.update();
			return execution;
		}
		catch(OffWorldException | EmptyColumnException e){
			throw new CommandExecuteException("",e);
		}
	}
	
	@Override
	public Command parse(String[] commandWords)  throws CommandParseException{
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