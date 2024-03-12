package gp.logic;

import java.util.List;
import gp.GameObjects.GameObject;
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
	
	public int findRow(int col){
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
        if(!find && row > 0) return Game.DIM_Y - 1;
        else return -1;


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
		if (objects.size()>5) {
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
		}
		return isFinished;
	}
	
	// Comprueba si desde una posicion cabe una longitud de fichas en una determinada posición
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
	int findConsecutiveTurn(Position pos) {
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
	
}
