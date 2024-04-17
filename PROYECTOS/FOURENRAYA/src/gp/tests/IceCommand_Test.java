package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;

public class IceCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        IceCommand command = new IceCommand();
        command.col = 3; // Columna para la prueba

        // Verificar que el método game.ice() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertTrue(result);
        });
        verify(game, times(1)).ice(3);
    }

    @Test
    public void testExecuteWithExceptions() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        IceCommand command = new IceCommand();
        command.col = 3; // Columna para la prueba

        // Configurar el comportamiento del mock para que arroje una OffWorldException
        doThrow(new OffWorldException("")).when(game).ice(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });

        // Configurar el comportamiento del mock para que arroje una FullColumnException
        doThrow(new FullColumnException("")).when(game).ice(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });

        // Configurar el comportamiento del mock para que arroje una InvalidWinException
        doThrow(new InvalidWinException("")).when(game).ice(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });
    }

    @Test
    public void testParse() {
        IceCommand command = new IceCommand();

        // Prueba de comando válido
        String[] validCommand = { "ice", "3" };
        assertDoesNotThrow(() -> {
            Command parsedCommand = command.parse(validCommand);
            assertNotNull(parsedCommand);
            assertEquals(command, parsedCommand);
            assertEquals(3, command.col);
        });

        // Prueba de comando con número incorrecto de parámetros
        String[] incorrectCommand = { "ice" };
        assertThrows(CommandParseException.class, () -> {
            command.parse(incorrectCommand);
        });

        // Prueba de comando con parámetro no numérico
        String[] invalidParameterCommand = { "ice", "abc" };
        CommandParseException exception = assertThrows(CommandParseException.class, () -> {
            command.parse(invalidParameterCommand);
        });
        assertEquals(Messages.OFF_WORLD_MESSAGE + "abc", exception.getMessage());

        // Prueba de comando con nombre incorrecto
        String[] wrongCommand = { "wrong", "3" };
        assertNull(command.parse(wrongCommand));
    }

    @Test
    public void testCommandDetails() {
        IceCommand command = new IceCommand();
        assertEquals(Messages.COMMAND_ICE_NAME, command.getName());
        assertEquals(Messages.COMMAND_ICE_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_ICE_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_ICE_HELP, command.getHelp());
    }
}
