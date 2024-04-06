package gp.commands;

import gp.exceptions.CantChangeModeException;
import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;

public class FiveInRowCommand extends NoParamsCommand {
	public FiveInRowCommand() {
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_5ROW_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_5ROW_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_5ROW_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_5ROW_HELP;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean execution = true;
		try {
			game.fiveInRow();
			return execution;
		}
		catch(CantChangeModeException e){
			throw new CommandExecuteException("",e);
		}
	}

}
