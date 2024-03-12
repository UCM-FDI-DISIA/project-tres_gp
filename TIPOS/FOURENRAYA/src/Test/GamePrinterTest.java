package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gp.logic.Game;
import gp.view.GamePrinter;

public class GamePrinterTest {

    private Game game;
    private GamePrinter gamePrinter;

    @BeforeEach
    void setUp() {
        game = new Game();
        gamePrinter = new GamePrinter(game) {
            @Override
            public String endMessage() {
                return "Game Over!";
            }
        };
    }
    
    @Test
    void testEndMessage() {
        assertEquals("Game Over!", gamePrinter.endMessage());
    }
}
