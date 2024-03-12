package gp.commands;

import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;

public class ResetCommand extends NoParamsCommand{
	@Override
	protected String getName() {
		
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.reset();
		return false;
	}
}
