package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import gp.control.Controller;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;

public class ControllerTest {

    private Controller controller;
    private Game game;
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private ByteArrayOutputStream sysOutContent;

    @BeforeEach
    void setUp() {
        game = new Game();
        sysInBackup = System.in;
        sysOutBackup = System.out;
        sysOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOutContent));
    }

    @Test
    void testRunWithValidInput() throws CommandParseException, CommandExecuteException {
        String input = "play white 1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        controller = new Controller(game, new Scanner(System.in));
        assertDoesNotThrow(controller::run);
        assertTrue(sysOutContent.toString().contains("Whites move"));
    }

    @Test
    void testRunWithInvalidCommand() {
        String input = "invalid command";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        controller = new Controller(game, new Scanner(System.in));
        assertThrows(CommandParseException.class, controller::run);
        assertTrue(sysOutContent.toString().contains("Unknown command"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"play", "play black", "play white 1 2"})
    void testRunWithInvalidParameters(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        controller = new Controller(game, new Scanner(System.in));
        assertThrows(CommandParseException.class, controller::run);
        assertTrue(sysOutContent.toString().contains("Invalid parameters"));
    }

    @Test
    void testPrintEndMessage() {
        controller = new Controller(game, new Scanner(System.in));
        controller.printEndMessage();
        assertTrue(sysOutContent.toString().contains("Game over"));
    }
}
