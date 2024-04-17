package gp.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gp.GameObjects.*;
import gp.exceptions.*;
import gp.logic.*;

public class Game_Test {

    private Game game;
    private GameObjectContainer container;

    @BeforeEach
    public void setUp() {
        container = new GameObjectContainer();
        game = new Game();
        game.setContainer(container);
    }

    @Test
    public void testPlace() throws OffWorldException, FullColumnException {
        // Arrange
        int col = 3;
        Position position = new Position(col, 0);
        Piece piece = new Piece(game, position);

        // Act
        game.place(col);

        // Assert
        assertTrue(container.contains(piece));
    }


}

