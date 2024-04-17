package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.commands.Command;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import gp.control.*;

public class Controller_Test {

    private Game game;
    private Controller controller;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        game = new Game(); // Aquí podemos inicializar el juego con un estado específico
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        controller = new Controller(game, new Scanner(inputStream));
    }

    @Test
    public void testRun() throws CommandParseException, CommandExecuteException {
        // Se puede simular la entrada del usuario y verificar la salida esperada
        String input = "place 1\nexit\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        controller = new Controller(game, new Scanner(inputStream));

        // Ejecutar el método run y verificar la salida
        assertDoesNotThrow(() -> controller.run());
        assertTrue(outputStream.toString().contains(Messages.WELCOME)); // Verificar si el mensaje de bienvenida se imprime
        assertTrue(outputStream.toString().contains(Messages.PROMPT)); // Verificar si se muestra el prompt

        // Restaurar la salida estándar
        System.setOut(originalSystemOut);
    }

}

