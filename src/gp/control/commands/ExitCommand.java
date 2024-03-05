package gp.commands;

import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;

public class ExitCommand extends NoParamsCommand{

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_NAME;
	}

	@Override
	protected String getShortcut() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_DETAILS;
	}

	@Override
	protected String getHelp() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.exit();
		return false;
	}
}
