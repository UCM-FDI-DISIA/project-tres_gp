package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gp.commands.HelpCommand;
import gp.logic.Game;
import gp.view.Messages;

public class HelpCommandTest {

    @Test
    public void testGetName() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(Messages.COMMAND_HELP_NAME, helpCommand.getName());
    }

    @Test
    public void testGetShortcut() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(Messages.COMMAND_HELP_SHORTCUT, helpCommand.getShortcut());
    }

    @Test
    public void testGetDetails() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(Messages.COMMAND_HELP_DETAILS, helpCommand.getDetails());
    }

    @Test
    public void testGetHelp() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(Messages.COMMAND_HELP_HELP, helpCommand.getHelp());
    }

    @Test
    public void testExecute() {
        HelpCommand helpCommand = new HelpCommand();
        Game game = new Game();  
        assertFalse(helpCommand.execute(game)); 
    }
}
