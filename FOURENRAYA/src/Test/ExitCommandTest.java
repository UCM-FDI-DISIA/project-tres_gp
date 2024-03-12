package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gp.commands.ExitCommand;
import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;

public class ExitCommandTest {

    @Test
    public void testGetName() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(Messages.COMMAND_EXIT_NAME, exitCommand.getName());
    }

    @Test
    public void testGetShortcut() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(Messages.COMMAND_EXIT_SHORTCUT, exitCommand.getShortcut());
    }

    @Test
    public void testGetDetails() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(Messages.COMMAND_EXIT_DETAILS, exitCommand.getDetails());
    }

    @Test
    public void testGetHelp() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(Messages.COMMAND_EXIT_HELP, exitCommand.getHelp());
    }

    @Test
    public void testExecute() throws CommandExecuteException {
        ExitCommand exitCommand = new ExitCommand();
        Game game = new Game();  
        assertFalse(exitCommand.execute(game)); 
        
    }
}

