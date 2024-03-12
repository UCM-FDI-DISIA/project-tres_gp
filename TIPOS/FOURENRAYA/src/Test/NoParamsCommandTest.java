package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gp.commands.Command;
import gp.commands.NoParamsCommand;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;

public class NoParamsCommandTest {

    private static class TestNoParamsCommand extends NoParamsCommand {
        @Override
        protected String getName() {
            return "TestCommand";
        }

        @Override
        protected String getShortcut() {
            return "TC";
        }

        @Override
        protected String getDetails() {
            return "Test Command";
        }

        @Override
        protected String getHelp() {
            return "Help for Test Command";
        }

		@Override
		public boolean execute(Game game) throws CommandExecuteException {
			// TODO Auto-generated method stub
			return false;
		}
    }

    @Test
    public void testParseCorrectCommand() throws CommandParseException {
        TestNoParamsCommand testCommand = new TestNoParamsCommand();
        String[] commandWords = {"TC"};
        assertEquals(testCommand, testCommand.parse(commandWords));
    }

    @Test
    public void testParseIncorrectCommand() {
        TestNoParamsCommand testCommand = new TestNoParamsCommand();
        String[] commandWords = {"InvalidCommand"};
        assertThrows(CommandParseException.class, () -> testCommand.parse(commandWords));
    }

    @Test
    public void testParseIncorrectParameterNumber() {
        TestNoParamsCommand testCommand = new TestNoParamsCommand();
        String[] commandWords = {"TC", "extraParameter"};
        assertThrows(CommandParseException.class, () -> testCommand.parse(commandWords));
    }
}

