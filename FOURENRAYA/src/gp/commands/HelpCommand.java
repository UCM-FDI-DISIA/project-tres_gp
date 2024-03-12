package gp.commands;

import gp.logic.Game;
import gp.view.Messages;

public class HelpCommand extends NoParamsCommand {

	@Override
	public String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	@Override
	public String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(Messages.HELP_AVAILABLE_COMMANDS);		
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

}
