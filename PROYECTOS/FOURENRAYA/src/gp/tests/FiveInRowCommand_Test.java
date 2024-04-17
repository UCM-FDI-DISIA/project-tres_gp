package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CantChangeModeException;
import gp.exceptions.CommandExecuteException;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

public class FiveInRowCommand_Test {

    @Test
    public void testExecute() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        FiveInRowCommand command = new FiveInRowCommand();

        // Verificar que el método game.fiveInRow() es llamado
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertTrue(result);
        });
        verify(game, times(1)).fiveInRow();
    }

    @Test
    public void testExecuteWithCantChangeModeException() {
        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        FiveInRowCommand command = new FiveInRowCommand();

        // Configurar el comportamiento del mock para que arroje una CantChangeModeException
        doThrow(new CantChangeModeException("")).when(game).fiveInRow();

        // Verificar que se lanza una CommandExecuteException cuando se produce una CantChangeModeException
        assertThrows(CommandExecuteException.class, () -> {
            command.execute(game);
        });
    }

    @Test
    public void testCommandDetails() {
        FiveInRowCommand command = new FiveInRowCommand();
        assertEquals(Messages.COMMAND_5ROW_NAME, command.getName());
        assertEquals(Messages.COMMAND_5ROW_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_5ROW_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_5ROW_HELP, command.getHelp());
    }
}
