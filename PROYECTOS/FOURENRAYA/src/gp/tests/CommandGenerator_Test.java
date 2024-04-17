package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandParseException;

public class CommandGenerator_Test {

    @Test
    public void testParseValidCommand() {
        // Prueba de comando válido
        String[] validCommand = { "place", "3" };
        assertDoesNotThrow(() -> {
            Command command = CommandGenerator.parse(validCommand);
            assertNotNull(command);
            assertTrue(command instanceof PlaceCommand);
            assertEquals(3, ((PlaceCommand) command).getColumn());
        });
    }

    @Test
    public void testParseInvalidCommand() {
        // Prueba de comando inválido
        String[] invalidCommand = { "invalid" };
        CommandParseException exception = assertThrows(CommandParseException.class, () -> {
            CommandGenerator.parse(invalidCommand);
        });
        assertEquals(Messages.UNKNOWN_COMMAND, exception.getMessage());
    }

    @Test
    public void testCommandHelp() {
        // Verificar que la ayuda de los comandos se genera correctamente
        String helpText = CommandGenerator.commandHelp();
        assertNotNull(helpText);
        assertTrue(helpText.contains("place"));
        assertTrue(helpText.contains("exit"));
        assertTrue(helpText.contains("reset"));
        assertTrue(helpText.contains("bomb"));
        assertTrue(helpText.contains("anvil"));
        assertTrue(helpText.contains("arrow"));
        assertTrue(helpText.contains("ice"));
        assertTrue(helpText.contains("fiveinrow"));
        assertTrue(helpText.contains("popout"));
    }
}
