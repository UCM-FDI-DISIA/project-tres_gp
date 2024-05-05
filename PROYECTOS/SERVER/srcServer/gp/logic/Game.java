package gp.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import gp.GameObjects.Anvil;
import gp.GameObjects.Arrow;
import gp.GameObjects.Bomb;
import gp.GameObjects.GameObject;
import gp.GameObjects.Piece;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Game {
	public  final static int DIM_X = 7;
	public  final static int DIM_Y = 6;
	private boolean bot = false;
	private GameObjectContainer container;
	private int turn = 1;
	private boolean doExit = false;
	private boolean someoneWin = false;
	public Game() {
		container = new GameObjectContainer();
	}

	public String positionToString(int col, int row) {
		return container.toString(new Position(col, row));
	}
	
	public List<GameObject> getGameObjectContainer() {
		return container.getListGameObjects();
	}
	public void setGameObjectContainer(List<GameObject> lista) {
		this.container.setListGameObjects(lista);
	}
	
	
	public void updateBot() {
		if (!someoneWin()) {
			flip();
		}
		bot = !bot;
	}
	
	public void popOut(int col, GridPane gridPane){
		container.popOut(col, gridPane);
	}
	
	protected int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
	
	public void flip() {
		if(turn ==1)
			turn = 2;
		else
			turn = 1;
	}
	
	
	public int place(int col){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Piece(this, pos, getTurn()));
			return row;
	}
	
	public int placeOnline(int col, boolean cliente){
		int row= findRow(col);
		Position pos = new Position(col, row);
		if(cliente)
			addObject(new Piece(this, pos, 1));
		else
			addObject(new Piece(this, pos, 2));
		return row;
	}
	
	public void bomb(int col, GridPane gridPane){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Bomb(this, pos));
			try {
				Parent ficha = FXMLLoader.load(getClass().getResource("/gp/superfichas/FICHA BOMBA J%s.fxml".formatted(getTurn())));
				gridPane.add(ficha, col, row);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			container.bomb(pos, gridPane);
	}
	
	public void anvil(int col, GridPane gridPane){
			int row= findRow(col);
			Position pos = new Position(col, row);
			addObject(new Anvil(this, pos));
			try {
				Parent ficha = FXMLLoader.load(getClass().getResource("/gp/superfichas/FICHA YUNQUE J%s.fxml".formatted(getTurn())));
				gridPane.add(ficha, col, row);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			container.anvil(pos, gridPane);
	}
	
	public void arrow(int col, GridPane gridPane){
			int row= findRow(col);
			Position pos = new Position(col, row);
			try {
				Parent ficha = FXMLLoader.load(getClass().getResource("/gp/superfichas/FICHA FLECHA J%s.fxml".formatted(getTurn())));
				gridPane.add(ficha, col, row);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addObject(new Arrow(this, pos));
			container.arrow(pos, gridPane);
	}
	
	public boolean isOnBoard(int col) {
		return (col >= 0) && (col < DIM_X);
	}
	public int findRow(int col){
		return container.findRow(col);		
	}
	public void addObject(GameObject object) {
		container.add(object);
		
	}
	
	public void remove(GameObject object) {
		container.remove(object);
	}

	public void exit() {
		doExit = true;
	}
	
	public boolean isFinished() {
		return doExit || someoneWin;
	}
	
	public boolean someoneWin() {
		return container.isFinished(turn, DIM_X);
	}
	public boolean someoneWin5() {
		return container.isFinished(turn, 9);
	}
	public int getTurn() {
		return turn;
	}
	
	public void reset(GridPane gridPane) {
		container.reset(gridPane);
	}

	public List<Position> getFreePositions() {
	    List<Position> freePositions = new ArrayList<>();

	    for (int col = 0; col < DIM_X; col++) {
	        int nextFreeRow = container.findRow(col); // Suponiendo que esta función devuelve la siguiente fila libre
	        if (nextFreeRow != -1) {
	            freePositions.add(new Position(col, nextFreeRow));
	        }
	    }
	    return freePositions;
	}


	private static final int MAX_DEPTH = 4; // Profundidad máxima para Minimax
	private static final int[] SCORES = {0, 1, 10, 100, 1000}; // Puntajes para fichas consecutivas

	private int evaluateBoard() {
	    int player1Score = getScoreForPlayer(1);
	    int player2Score = getScoreForPlayer(2);
	    return player1Score - player2Score;
	}

	private int getScoreForPlayer(int player) {
	    int totalScore = 0;
	    for (int col = 0; col < DIM_X; col++) {
	        for (int row = 0; row < DIM_Y; row++) {
	            Position pos = new Position(col, row);
	            GameObject obj = container.findObject(pos);
	            if (obj != null && obj.getTurn() == player) {
	                // Evaluación horizontal
	                totalScore += evaluateDirection(pos, 1, 0, player);
	                // Evaluación vertical
	                totalScore += evaluateDirection(pos, 0, 1, player);
	                // Evaluación diagonal hacia arriba
	                totalScore += evaluateDirection(pos, 1, 1, player);
	                // Evaluación diagonal hacia abajo
	                totalScore += evaluateDirection(pos, 1, -1, player);
	            }
	        }
	    }
	    return totalScore;
	}

	private int evaluateDirection(Position pos, int dirX, int dirY, int player) {
	    int score = 0;
	    for (int i = 1; i < 4; i++) {
	        int col = pos.getCol() + i * dirX;
	        int row = pos.getRow() + i * dirY;
	        if (!isOnBoard(col) || !isOnBoard(row)) {
	            break; // Nos salimos del tablero
	        }
	        GameObject obj = container.findObject(new Position(col, row));
	        if (obj == null) {
	            break; // No hay ficha en esta dirección
	        }
	        if (obj.getTurn() != player) {
	            break; // La ficha es del oponente
	        }
	        score += SCORES[i]; // Aumentamos el puntaje por fichas consecutivas del jugador
	    }
	    return score;
	}

	public int getBestColumn() {
	    int bestScore = Integer.MIN_VALUE;
	    int bestCol = -1;

	    for (int col = 0; col < DIM_X; col++) {
	        if (isOnBoard(col)) {
	            // Simular el movimiento del bot en esta columna
	            int row = place(col);

	            // Calcular el puntaje utilizando el algoritmo Minimax con profundidad limitada
	            int score = minimax(0, false);
	            Position pos = new Position(col, row);
	            // Deshacer el movimiento simulado
	            container.deletePiece(pos, null);

	            // Actualizar la mejor columna si se encontró un puntaje mejor
	            if (score > bestScore) {
	                bestScore = score;
	                bestCol = col;
	            }
	        }
	    }

	    // Devolver la mejor columna encontrada
	    return bestCol;
	}

	public int botEasyMove() {
	    List<Position> freePositions = getFreePositions();
	    if (freePositions.isEmpty()) {
	        // No hay posiciones libres, el bot no puede moverse
	        return -1;
	    }

	    Random random = new Random();
	    int index = random.nextInt(freePositions.size());
	    Position chosenPosition = freePositions.get(index);

	    // Coloca la ficha en la posición elegida
	    int col = chosenPosition.getCol();

	    // Devuelve la columna donde se colocó la ficha
	    return col;
	}
	
	private int minimax(int depth, boolean isMaximizingPlayer) {
	    if (depth == MAX_DEPTH || someoneWin() || isBoardFull()) {
	        return evaluateBoard();
	    }

	    if (isMaximizingPlayer) {
	        int bestScore = Integer.MIN_VALUE;
	        for (int col = 0; col < DIM_X; col++) {
	            if (isOnBoard(col)) {
	                int row = place(col);
	                Position pos = new Position(col, row);
	                
	                int score = minimax(depth + 1, false);
	                // Deshacer el movimiento simulado
	                container.deletePiece(pos, null);
	                bestScore = Math.max(bestScore, score);
	            }
	        }
	        return bestScore;
	    } else {
	        int bestScore = Integer.MAX_VALUE;
	        for (int col = 0; col < DIM_X; col++) {
	            if (isOnBoard(col)) {
	                int row = place(col);
	                Position pos = new Position(col, row);
	                int score = minimax(depth + 1, true);
	                // Deshacer el movimiento simulado
	                container.deletePiece(pos, null);
	                bestScore = Math.min(bestScore, score);
	            }
	        }
	        return bestScore;
	    }
	}

	
	private boolean isBoardFull() {
		List<Position> freePositions = getFreePositions();
		if(freePositions.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public int botMediumMove() {
	    // Definir el umbral de probabilidad
	    double threshold = 0.5; // Por ejemplo, umbral del 50%

	    // Generar un número aleatorio entre 0 y 1
	    double randomValue = Math.random();

	    if (randomValue < threshold) {
	        // Realizar un movimiento aleatorio
	        return botEasyMove();
	    } else {
	        // Utilizar el algoritmo Minimax para encontrar la mejor columna
	        return getBestColumn();
	    }
	}
	public List<List<Position>> getWinners() {
		return container.getWinners();
	}

}
