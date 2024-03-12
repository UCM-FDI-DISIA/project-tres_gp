package gp.commands;

import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;

public class ExitCommand extends NoParamsCommand{

	@Override
	public String getName() {
		
		return Messages.COMMAND_EXIT_NAME;
	}

	@Override
	public String getShortcut() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_DETAILS;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return Messages.COMMAND_EXIT_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.exit();
		return false;
	}

}
