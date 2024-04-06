package gp.logic;

import java.util.List;

import gp.GameObjects.GameObject;
import gp.exceptions.EmptyColumnException;
import gp.exceptions.FullColumnException;
import gp.view.Messages;

import java.util.ArrayList;

public class GameObjectContainer {
	private List<GameObject> objects;
	private List<List<Position>> winners;

	public GameObjectContainer() {
		objects = new ArrayList<>();
		winners = new ArrayList<List<Position>>();
	}
	
	public String toString(Position pos) {
        String draw = "";
        for (int i=0;i<objects.size();i++) {
            if (objects.get(i).isOnPosition(pos)) {
                draw = objects.get(i).toString();
            }
        }
        return draw;
    }

	public void add(GameObject object) {
		objects.add(object);
	}
	
	public int findRow(int col) throws FullColumnException{
        boolean find = false;
        int row = 5;
        while(!find &&  row >= 0) {
            Position p = new Position(col, row);
            for (int i = 0; i < objects.size(); i++) {
                GameObject c=objects.get(i);
                if(c.isOnPosition(p)) {
                    find = true;
                }
            }
            if(!find) return row;
            else {
                find = false;
                row--;
            }
        }
        if(!find) return Game.DIM_Y - 1;
        else throw new FullColumnException(Messages.FULL_COLUMN_MESSAGE.formatted(col));


    }
	
	public void bomb(Position pos) {
		for(Direction dir : Direction.values()) {
			if (fitIn(1, dir, pos)) {
				Position newPos = new Position(pos.getCol() + 1 * dir.getY(),
						pos.getRow() + 1 * dir.getX());
				deletePiece(newPos);
			}
		}
		
		GameObject object = findObject(pos);
		object.die();
		makePiecesFall();
	}
	
	public void anvil(Position pos) {
		for (int i = pos.getRow() + 1; i < Game.DIM_Y; i++) {
			Position newPos = new Position(pos.getCol(), i);
			GameObject currentObject = findObject(newPos);
			objects.remove(currentObject);
		}
		GameObject object = findObject(pos);
		object.die();
		makePiecesFall();
	}
	
	public void arrow(Position pos) {
		for (int i = 0; i <= pos.getCol(); i++) {
			Position newPos = new Position(i, pos.getRow());
			GameObject currentObject = findObject(newPos);
			if (currentObject != null) 
	            movePieceLeft(currentObject);
		}
		GameObject object = findObject(pos);
		object.die();
		makePiecesFall();
	}
	
	public void ice(Position pos) {
		GameObject object = findObject(pos);
		object.die();
	}
		
	private void movePieceLeft(GameObject obj) {
	    Position currentPos = obj.getPosition();
	    Position newPos = new Position(currentPos.getCol() - 1, currentPos.getRow());
	    if (newPos.getCol() < 0)
	    	objects.remove(obj);
	    else obj.getPosition().setCol(newPos.getCol());
	}
	
	private void deletePiece(Position pos) {
		GameObject obj = findObject(pos);
		if (obj != null)
			objects.remove(obj);
	}
	
	private GameObject findObject(Position pos) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject currentObject  = objects.get(i);
			if (pos.equals(currentObject.getPosition())) {
				return currentObject;
			}
		}
		return null;
	}
	
	private void makePiecesFall() {
	    for (int col = 0; col < Game.DIM_X; col++) { 
	        for (int row = Game.DIM_Y - 1; row >= 0; row--) { 
	            Position currentPos = new Position(col, row);
	            GameObject currentObject = findObject(currentPos);
	            if (currentObject != null) {
	                int fallDistance = 1;
	                while (row + fallDistance < Game.DIM_Y && findObject(new Position(col, row + fallDistance)) == null) {
	                    fallDistance++;
	                }
	                if (fallDistance > 1) {
	                    Position newPos = new Position(col, row + fallDistance - 1);
	                    movePiece(currentObject, newPos);
	                }
	            }
	        }
	    }
	}

	private void movePiece(GameObject obj, Position newPos) {
	    obj.getPosition().setCol(newPos.getCol());
	    obj.getPosition().setRow(newPos.getRow());
	}
	
	// Comprueba si existe un ganador.
	// Recorre todos los objetos del contenedor, 
	// Por cada uno, comprueba en cada direccion dos condiciones:
		// 1. Que caben 4 fichas en esa direccion
		// 2. Que las fichas consecuetes sean del mismo turno
	// Si se encuentra un posible ganador, no se deja de buscar, pues sde pueden hacer dos 
			// conecta 4 con solo una ficha
			// Por tanto, se almacena esa solucion en un lista de listas de posiciones
	public boolean isFinished(int turn) {
		boolean isFinished = false;
		int length = 3;
		for (int i = 0; i < objects.size(); i++) {
			
			GameObject currentObject  = objects.get(i);
			Position currentPosition = currentObject.getPosition();
			
			for(Direction dir : Direction.values()) {
				if (fitIn(length, dir, currentPosition) &&
						checkConsecutiveTurns(turn, length, dir, currentPosition)) {
					isFinished = true;
					win(dir, currentPosition);
				}	
			}
		}
		return isFinished;
	}
	
	// Comprueba si desde una posicion cabe una longitud de fichas en una determinada dirección
	private boolean fitIn(int length , Direction dir, Position pos) {
		Position newPos = new Position(pos.getCol() + length * dir.getY(),
				pos.getRow() + length * dir.getX());
		return newPos.isOnBoard();
	}
	
	// Comprueba si desde una posicion en una determianda dirección la siguiente ficha es del mismo
		// turno que la anterior
	private boolean checkConsecutiveTurns(int turn, int length, Direction dir, Position pos) {
		boolean ok = true;
	    int consecutiveCount = 0;
		while(ok && (consecutiveCount <= length)) {
			Position newPos = new Position(pos.getCol() + consecutiveCount * dir.getY(),
					pos.getRow() + consecutiveCount * dir.getX());
			int consecutiveTurn = findConsecutiveTurn(newPos);
			if (turn == consecutiveTurn) {
				consecutiveCount++;
			}
			else ok = false;
		}
		return ok;
	}
	
	// Agrega la solución encontrada a la lista de soluciones
	private void win(Direction dir, Position pos) {
		int length = 4;
		List<Position> positionsList = new ArrayList<Position>();
		for (int i = 0; i < length; i++) {
			Position newPos = new Position(pos.getCol() + i * dir.getY(),
					pos.getRow() + i * dir.getX());
			positionsList.add(newPos);
		}
		winners.add(positionsList);
	}
	
	// Encuentra si existe una ficha en esa posicíon y devuelve su turno
	private int findConsecutiveTurn(Position pos) {
		int turn = -1, i = 0;
		boolean find = false;
		while(!find && (i < objects.size())) {
			GameObject currentObject  = objects.get(i);
			if (currentObject.isOnPosition(pos)) {
				find = true;
				turn = currentObject.getTurn();
			}
			else i++;
		}
		return turn;
	}
	
	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void popOut(int col) throws EmptyColumnException {
		GameObject object = findObject(new Position(col,Game.DIM_Y - 1));
		if (object != null) {
			remove(object);
			makePiecesFall();
		}
		else throw new EmptyColumnException(Messages.EMPTY_COLUMN_MESSAGE);
	}

	public void reset() {
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			GameObject currentObject  = objects.get(0);
			remove(currentObject);
		}
	}
	
	
}
