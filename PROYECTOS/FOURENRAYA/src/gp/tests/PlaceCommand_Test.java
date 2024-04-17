package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class PlaceCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        PlaceCommand command = new PlaceCommand();
        command.col = 3; // Columna para la prueba

        // Verificar que el método game.place() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertTrue(result);
        });
        verify(game, times(1)).place(3);
    }

    @Test
    public void testExecuteWithExceptions() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        PlaceCommand command = new PlaceCommand();
        command.col = 3; // Columna para la prueba

        // Configurar el comportamiento del mock para que arroje una OffWorldException
        doThrow(new OffWorldException("")).when(game).place(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });

        // Configurar el comportamiento del mock para que arroje una FullColumnException
        doThrow(new FullColumnException("")).when(game).place(3);
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });
    }

    @Test
    public void testParse() {
        PlaceCommand command = new PlaceCommand();

        // Prueba de comando válido
        String[] validCommand = { "place", "3" };
        assertDoesNotThrow(() -> {
            Command parsedCommand = command.parse(validCommand);
            assertNotNull(parsedCommand);
            assertEquals(command, parsedCommand);
            assertEquals(3, command.col);
        });

        // Prueba de comando con número incorrecto de parámetros
        String[] incorrectCommand = { "place" };
        assertThrows(CommandParseException.class, () -> {
            command.parse(incorrectCommand);
        });

        // Prueba de comando con parámetro no numérico
        String[] invalidParameterCommand = { "place", "abc" };
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
        PlaceCommand command = new PlaceCommand();
        assertEquals(Messages.COMMAND_PLACE_NAME, command.getName());
        assertEquals(Messages.COMMAND_PLACE_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_PLACE_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_PLACE_HELP, command.getHelp());
    }
}

