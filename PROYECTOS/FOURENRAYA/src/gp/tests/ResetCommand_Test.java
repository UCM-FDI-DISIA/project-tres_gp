package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class ResetCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        ResetCommand command = new ResetCommand();

        // Verificar que el método game.reset() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertTrue(result);
        });
        verify(game, times(1)).reset();
    }

    @Test
    public void testCommandDetails() {
        ResetCommand command = new ResetCommand();
        assertEquals(Messages.COMMAND_RESET_NAME, command.getName());
        assertEquals(Messages.COMMAND_RESET_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_RESET_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_RESET_HELP, command.getHelp());
    }
}
