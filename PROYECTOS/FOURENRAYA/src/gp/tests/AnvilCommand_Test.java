package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.exceptions.FullColumnException;
import gp.exceptions.OffWorldException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class AnvilCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego y la columna para la prueba
        Game game = mock(Game.class);
        AnvilCommand command = new AnvilCommand();
        command.col = 3; // Columna para la prueba

        // Verificar que el método game.anvil() es llamado
        assertDoesNotThrow(() -> {
            command.execute(game);
        });
        verify(game, times(1)).anvil(3);
        verify(game, times(1)).update();
    }

    @Test
    public void testParse() {
        AnvilCommand command = new AnvilCommand();

        // Prueba de comando válido
        String[] validCommand = { "anvil", "3" };
        assertDoesNotThrow(() -> {
            Command parsedCommand = command.parse(validCommand);
            assertNotNull(parsedCommand);
            assertEquals(command, parsedCommand);
            assertEquals(3, command.col);
        });

        // Prueba de comando con número incorrecto de parámetros
        String[] incorrectCommand = { "anvil" };
        assertThrows(CommandParseException.class, () -> {
            command.parse(incorrectCommand);
        });

        // Prueba de comando con parámetro no numérico
        String[] invalidParameterCommand = { "anvil", "abc" };
        CommandParseException exception = assertThrows(CommandParseException.class, () -> {
            command.parse(invalidParameterCommand);
        });
        assertEquals(Messages.OFF_WORLD_MESSAGE + "abc", exception.getMessage());

        // Prueba de comando con nombre incorrecto
        String[] wrongCommand = { "wrong", "3" };
        assertNull(command.parse(wrongCommand));
    }
}
