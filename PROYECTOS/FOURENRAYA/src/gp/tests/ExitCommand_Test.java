package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class ExitCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        ExitCommand command = new ExitCommand();

        // Verificar que el método game.exit() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertFalse(result);
        });
        verify(game, times(1)).exit();
    }

    @Test
    public void testCommandDetails() {
        ExitCommand command = new ExitCommand();
        assertEquals(Messages.COMMAND_EXIT_NAME, command.getName());
        assertEquals(Messages.COMMAND_EXIT_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_EXIT_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_EXIT_HELP, command.getHelp());
    }
}
