package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gp.commands.PlaceCommand;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.logic.Game;
import gp.view.Messages;

public class PlaceCommandTest {

    @Test
    public void testGetName() {
        PlaceCommand placeCommand = new PlaceCommand();
        assertEquals(Messages.COMMAND_PLACE_NAME, placeCommand.getName());
    }

    @Test
    public void testGetShortcut() {
        PlaceCommand placeCommand = new PlaceCommand();
        assertEquals(Messages.COMMAND_PLACE_SHORTCUT, placeCommand.getShortcut());
    }

    @Test
    public void testGetDetails() {
        PlaceCommand placeCommand = new PlaceCommand();
        assertEquals(Messages.COMMAND_PLACE_DETAILS, placeCommand.getDetails());
    }

    @Test
    public void testGetHelp() {
        PlaceCommand placeCommand = new PlaceCommand();
        assertEquals(Messages.COMMAND_PLACE_HELP, placeCommand.getHelp());
    }

    @Test
    public void testExecute() throws CommandExecuteException, OffWorldException, FullColumnException {
        PlaceCommand placeCommand = new PlaceCommand();
        Game game = new Game(); 
        assertTrue(placeCommand.execute(game)); 
    }

    @Test
    public void testExecuteOffWorldException() {
        PlaceCommand placeCommand = new PlaceCommand();
        Game game = new Game(); 
        assertThrows(CommandExecuteException.class, () -> placeCommand.execute(game));
    }

    @Test
    public void testParseCorrectCommand() throws CommandParseException {
        PlaceCommand placeCommand = new PlaceCommand();
        String[] commandWords = {"PLACE", "2"};
        assertEquals(placeCommand, placeCommand.parse(commandWords));
    }

    @Test
    public void testParseIncorrectParameterNumber() {
        PlaceCommand placeCommand = new PlaceCommand();
        String[] commandWords = {"PLACE"};
        assertThrows(CommandParseException.class, () -> placeCommand.parse(commandWords));
    }
}
