package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gp.commands.Command;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;

public class Command_test {

    private static class TestCommand extends Command {
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
            return false;
        }

        @Override
        public Command parse(String[] commandWords) throws CommandParseException {
            return null;
        }
    }

    @Test
    public void testMatchCommandName() {
        TestCommand testCommand = new TestCommand();
        assertTrue(testCommand.matchCommandName("TestCommand"));
        assertTrue(testCommand.matchCommandName("TC"));
        assertFalse(testCommand.matchCommandName("InvalidCommand"));
    }

    @Test
    public void testHelpText() {
        TestCommand testCommand = new TestCommand();
        String expectedHelpText = "Test Command : Help for Test Command\n";
        assertEquals(expectedHelpText, testCommand.helpText());
    }
}

