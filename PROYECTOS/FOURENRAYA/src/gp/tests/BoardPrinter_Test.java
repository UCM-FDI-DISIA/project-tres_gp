package gp.tests;


import gp.logic.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gp.view.*;

public class BoardPrinter_Test {

    @Test
    void testToString() {
        // Configuración del juego
        Game game = new Game();
        game.place(0);
        game.place(1);
        game.place(2);
        game.place(3);
        game.place(4);
        game.place(5);
        game.place(6);
        game.place(0);
        game.place(1);
        game.place(2);
        game.place(3);
        game.place(4);
        game.place(5);
        game.place(6);

        // Inicialización del objeto BoardPrinter
        BoardPrinter printer = new BoardPrinter(game);

        // Resultado esperado (se puede ajustar según el diseño del tablero)
        String expected = "  ──────  ──────  ──────  ──────  ──────  ──────  ────── \n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "|       ||       ||       ||       ||       ||       ||       |\n" +
                          "  ──────  ──────  ──────  ──────  ──────  ──────  ────── \n";

        // Comparación
        assertEquals(expected, printer.toString());
    }

    @Test
    void testEndMessage() {
        // Configuración del juego
        Game game = new Game();
        game.place(0);
        game.place(1);
        game.place(2);
        game.place(3);
        game.place(4);
        game.place(5);
        game.place(6);

        // Inicialización del objeto BoardPrinter
        BoardPrinter printer = new BoardPrinter(game);

        // Resultado esperado
        String expected = "Player 1 wins!";
        
        // Comparación
        assertEquals(expected, printer.endMessage());
    }
}
