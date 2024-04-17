package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.commands.*;

public class Command_Test {

    @Test
    public void testMatchCommandName() {
        // Crear una implementación simple de la clase Command para realizar pruebas
        Command command = new Command() {
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
            public boolean execute(Game game) throws CommandExecuteException {
                return true;
            }

            @Override
            public Command parse(String[] commandWords) throws CommandParseException {
                return null;
            }
        };

        // Verificar que el método matchCommandName funciona correctamente
        assertTrue(command.matchCommandName("shortcut"));
        assertTrue(command.matchCommandName("name"));
        assertFalse(command.matchCommandName("wrong"));
    }

    @Test
    public void testHelpText() {
        // Crear una implementación simple de la clase Command para realizar pruebas
        Command command = new Command() {
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
            public boolean execute(Game game) throws CommandExecuteException {
                return true;
            }

            @Override
            public Command parse(String[] commandWords) throws CommandParseException {
                return null;
            }
        };

        // Verificar que el método helpText genera el texto de ayuda correctamente
        assertEquals("details : help\n", command.helpText());
    }
}
