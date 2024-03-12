package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import gp.view.Messages;

public class MessagesTest {

    @Test
    void testWelcomeMessage() {
        assertEquals("Cuatro en Raya Fury Clásico\n", Messages.WELCOME);
    }

    @Test
    void testPlayerWinsMessages() {
        assertEquals("Player 1 wins!", Messages.PLAYER_1_WINS);
        assertEquals("Player 2 wins!", Messages.PLAYER_2_WINS);
    }

    @Test
    void testDebugMessage() {
        assertEquals("%n[DEBUG] Executing: test%n", Messages.debug("test"));
    }

    @Test
    void testErrorMessage() {
        assertEquals("[ERROR] Error: test%n", Messages.error("test"));
    }

    @Test
    void testCommandDetails() {
        assertEquals("[e]xit", Messages.COMMAND_EXIT_DETAILS);
        assertEquals("[h]elp", Messages.COMMAND_HELP_DETAILS);
        assertEquals("[p]lace col", Messages.COMMAND_PLACE_DETAILS);
        assertEquals("[r]eset [filename]", Messages.COMMAND_RESET_DETAILS);
    }

    @Test
    void testCommandHelp() {
        assertEquals("exits the game", Messages.COMMAND_EXIT_HELP);
        assertEquals("shows this help", Messages.COMMAND_HELP_HELP);
        assertEquals("places the piece in the indicated column", Messages.COMMAND_PLACE_HELP);
        assertEquals("resets the game using the initial configuration in file filename. If no file is given the standard configuration is used", Messages.COMMAND_RESET_HELP);
    }

    @Test
    void testPositionMessage() {
        assertEquals("(%s, %s)", Messages.POSITION);
    }

    @Test
    void testOffWorldMessage() {
        assertEquals("Column %s is off board", Messages.OFF_WORLD_MESSAGE);
    }

    @Test
    void testIncorrectParameterNumberMessage() {
        assertEquals("Incorrect parameter number", Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
    }

    @Test
    void testUnknownCommandMessage() {
        assertEquals("Unknown command", Messages.UNKNOWN_COMMAND);
    }

    @Test
    void testFullColumnMessage() {
        assertEquals("Column %s is full", Messages.FULL_COLUMN_MESSAGE);
    }

    @Test
    void testGameOverMessage() {
        assertEquals("Game Over", Messages.GAME_OVER);
    }

    @Test
    void testErrorMessageFormat() {
        assertEquals("[ERROR] Error: test%n", Messages.error("test"));
    }
}

