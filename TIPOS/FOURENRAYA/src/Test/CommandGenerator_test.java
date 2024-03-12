package Test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.commands.Command;
import gp.commands.CommandGenerator;
import gp.exceptions.CommandParseException;

public class CommandGenerator_test {

    @Test
    public void testParseValidCommand() throws CommandParseException {
        String[] commandWords = {"help"};
        Command command = CommandGenerator.parse(commandWords);
        assertNotNull(command);
        
    }

    @Test
    public void testParseUnknownCommand() {
        String[] commandWords = {"invalidCommand"};
        assertThrows(CommandParseException.class, () -> CommandGenerator.parse(commandWords));
    }

    @Test
    public void testCommandHelp() {
        String expectedHelpText = "HelpCommand : Help for Help Command\n" +
                                 "PlaceCommand : Help for Place Command\n" +
                                 "ExitCommand : Help for Exit Command\n" +
                                 "ResetCommand : Help for Reset Command\n";
        assertEquals(expectedHelpText, CommandGenerator.commandHelp());
    }

}
