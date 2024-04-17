package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class PopOutCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        PopOutCommand command = new PopOutCommand();
        command.col = 3; // Columna para la prueba

        // Verificar que el método game.popOut() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertTrue(result);
        });
        verify(game, times(1)).popOut(3);
    }

    @Test
    public void testExecuteWithExceptions() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        PopOutCommand command = new PopOutCommand();
        command.col = 3; // Columna para la prueba

        // Configurar el comportamiento del mock para que arroje una OffWorldException
        doThrow(new OffWorldException("")).when(game).popOut(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });

        // Configurar el comportamiento del mock para que arroje una EmptyColumnException
        doThrow(new EmptyColumnException("")).when(game).popOut(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });

        // Configurar el comportamiento del mock para que arroje una OpponentPieceException
        doThrow(new OpponentPieceException("")).when(game).popOut(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });
    }

    @Test
    public void testParse() {
        PopOutCommand command = new PopOutCommand();

        // Prueba de comando válido
        String[] validCommand = { "popout", "3" };
        assertDoesNotThrow(() -> {
            Command parsedCommand = command.parse(validCommand);
            assertNotNull(parsedCommand);
            assertEquals(command, parsedCommand);
            assertEquals(3, command.col);
        });

        // Prueba de comando con número incorrecto de parámetros
        String[] incorrectCommand = { "popout" };
        assertThrows(CommandParseException.class, () -> {
            command.parse(incorrectCommand);
        });

        // Prueba de comando con parámetro no numérico
        String[] invalidParameterCommand = { "popout", "abc" };
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
        PopOutCommand command = new PopOutCommand();
        assertEquals(Messages.COMMAND_POPOUT_NAME, command.getName());
        assertEquals(Messages.COMMAND_POPOUT_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_POPOUT_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_POPOUT_HELP, command.getHelp());
    }
}

