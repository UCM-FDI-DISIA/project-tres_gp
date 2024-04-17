package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.view.Messages;
import static org.mockito.Mockito.*;
import gp.commands.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelpCommand_Test {

    @Test
    public void testExecute() {
        // Redirigir la salida estándar a un ByteArrayOutputStream para poder verificarla
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Configuración del juego para la prueba
        Game game = mock(Game.class);
        HelpCommand command = new HelpCommand();

        // Verificar que el método execute imprime la ayuda correcta
        assertDoesNotThrow(() -> {
            boolean result = command.execute(game);
            assertFalse(result);
            assertTrue(outputStream.toString().contains(Messages.HELP_AVAILABLE_COMMANDS));
        });

        // Restaurar la salida estándar
        System.setOut(System.out);
    }

    @Test
    public void testCommandDetails() {
        HelpCommand command = new HelpCommand();
        assertEquals(Messages.COMMAND_HELP_NAME, command.getName());
        assertEquals(Messages.COMMAND_HELP_SHORTCUT, command.getShortcut());
        assertEquals(Messages.COMMAND_HELP_DETAILS, command.getDetails());
        assertEquals(Messages.COMMAND_HELP_HELP, command.getHelp());
    }
}

