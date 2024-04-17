package gp.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import gp.Main;

public class Main_Test {

    @Test
    public void testMain() {
        // Preparar datos de entrada simulados
        String input = "1\n"; // Simula la entrada del usuario
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capturar salida estándar para poder verificarla
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Ejecutar el método main
        Main.main(null);

        // Verificar que la salida esperada se imprime en la consola
        String expectedOutput = "¡Bienvenido al juego!\n" + ">> Tu turno:\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
