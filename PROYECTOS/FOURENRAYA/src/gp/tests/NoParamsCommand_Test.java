package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandParseException;
import gp.view.Messages;
import gp.commands.*;

public class NoParamsCommand_Test {

    @Test
    public void testParseWithValidCommand() {
        // Crear un comando sin parámetros para realizar la prueba
        NoParamsCommand command = new NoParamsCommand() {
            @Override
            protected String getName() {
                return "name";
            }

            @Override
            protected String getShortcut() {
                return "shortcut";
            }

            @Override
            protected String getDetails() {
                return "details";
            }

            @Override
            protected String getHelp() {
                return "help";
            }

            @Override
            public boolean execute(Game game) {
                return true;
            }
        };

        // Prueba de comando válido
        String[] validCommand = { "shortcut" };
        assertDoesNotThrow(() -> {
            Command parsedCommand = command.parse(validCommand);
            assertNotNull(parsedCommand);
            assertEquals(command, parsedCommand);
        });
    }

    @Test
    public void testParseWithInvalidCommand() {
        // Crear un comando sin parámetros para realizar la prueba
        NoParamsCommand command = new NoParamsCommand() {
            @Override
            protected String getName() {
                return "name";
            }

            @Override
            protected String getShortcut() {
                return "shortcut";
            }

            @Override
            protected String getDetails() {
                return "details";
            }

            @Override
            protected String getHelp() {
                return "help";
            }

            @Override
            public boolean execute(Game game) {
                return true;
            }
        };

        // Prueba de comando con número incorrecto de parámetros
        String[] invalidCommand = { "shortcut", "extra" };
        CommandParseException exception = assertThrows(CommandParseException.class, () -> {
            command.parse(invalidCommand);
        });
        assertEquals(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER, exception.getMessage());

        // Prueba de comando con nombre incorrecto
        String[] wrongCommand = { "wrong" };
        assertNull(command.parse(wrongCommand));
    }

    @Test
    public void testCommandDetails() {
        // Crear un comando sin parámetros para realizar la prueba
        NoParamsCommand command = new NoParamsCommand() {
            @Override
            protected String getName() {
                return "name";
            }

            @Override
            protected String getShortcut() {
                return "shortcut";
            }

            @Override
            protected String getDetails() {
                return "details";
            }

            @Override
            protected String getHelp() {
                return "help";
            }

            @Override
            public boolean execute(Game game) {
                return true;
            }
        };

        assertEquals("name", command.getName());
        assertEquals("shortcut", command.getShortcut());
        assertEquals("details", command.getDetails());
        assertEquals("help", command.getHelp());
    }
}
