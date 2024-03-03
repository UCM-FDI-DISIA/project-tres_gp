package gp.commands;

import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;

public class ExitCommand extends Command{

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getShortcut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
