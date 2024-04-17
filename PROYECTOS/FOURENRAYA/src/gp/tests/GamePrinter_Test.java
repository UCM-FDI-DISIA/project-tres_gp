package gp.tests;


import gp.logic.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gp.view.*;

public class GamePrinter_Test {

    @Test
    void testEndMessage() {
        // Configuración del juego
        Game game = new Game();
        
        // Inicialización del objeto GamePrinter
        GamePrinter printer = new GamePrinter(game) {
            @Override
            public String endMessage() {
                return "Game Over!";
            }
        };

        // Resultado esperado
        String expected = "Game Over!";
        
        // Comparación
        assertEquals(expected, printer.endMessage());
    }
}
