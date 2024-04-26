package gp.logic;

import java.util.List;
import java.util.Set;

import gp.GameObjects.GameObject;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class GameObjectContainer {
	private List<GameObject> objects;
	private List<List<Position>> winners;

	public GameObjectContainer() {
		objects = new ArrayList<>();
		winners = new ArrayList<List<Position>>();
	}
	public List<GameObject> getListGameObjects(){
		return objects;
	}
	public void setListGameObjects(List<GameObject> lista){
		this.objects = lista;
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
        if(!find) return Game.DIM_Y - 1;
        else return (Integer) null;


    }
	
	public void bomb(Position pos, GridPane gridPane) {
		for(Direction dir : Direction.values()) {
			if (fitIn(1, dir, pos)) {
				Position newPos = new Position(pos.getCol() + 1 * dir.getY(),
						pos.getRow() + 1 * dir.getX());
				deletePiece(newPos, gridPane);
			}
		}
		
		GameObject object = findObject(pos);
		object.die();
		for(int i = 0; i < 3; i++)
			makePiecesFall(gridPane);
	}
	
	public void anvil(Position pos, GridPane gridPane) {
		for (int i = pos.getRow() + 1; i < Game.DIM_Y; i++) {
			Position newPos = new Position(pos.getCol(), i);
			deletePiece(newPos, gridPane);
		}
		GameObject object = findObject(pos);
		object.die();
		for(int i = 0; i < 5; i++)
			makePiecesFall(gridPane);
	}
	
	public void arrow(Position pos, GridPane gridPane) {
		if(pos.getCol() != 0) {
			for (int i = 0; i <= pos.getCol()-1; i++) {
				Position newPos = new Position(i, pos.getRow());
				GameObject currentObject = findObject(newPos);
				if (currentObject != null) {
					movePieceLeft(gridPane, currentObject);
				}
			}	
			Platform.runLater(() -> {
		        gridPane.requestLayout();
		    });
		}
		
		GameObject object = findObject(pos);
		object.die();
		for(int i = 0; i < 5; i++)
			makePiecesFall(gridPane);
	}
		
	private void movePieceLeft(GridPane gridPane, GameObject obj) {
	    Position currentPos = obj.getPosition();
	    
	    if (currentPos.getCol() == 0)
	    	deletePiece(currentPos, gridPane);
	    else {
	    	Position newPos = new Position(currentPos.getCol() - 1, currentPos.getRow());
	    	Node nodeToRemove = findNodeInGridPane(gridPane, currentPos.getCol(), currentPos.getRow());
			if (nodeToRemove != null) {    
				 gridPane.getChildren().remove(nodeToRemove); 
				// Insertamos la ficha justo antes del nodo que desencadena el evento
	            gridPane.add(nodeToRemove, currentPos.getCol() - 1, currentPos.getRow(), 1, 1);
	                
			}
	    	obj.getPosition().setCol(newPos.getCol());
	    }
	}
	
	public void deletePiece(Position pos, GridPane gridPane) {
		GameObject obj = findObject(pos);
		Node nodeToRemove = null;
		if(gridPane != null) {
			nodeToRemove = findNodeInGridPane(gridPane, pos.getCol(), pos.getRow());
		}
		if (obj != null)
			objects.remove(obj);
		if (nodeToRemove != null) {    
            gridPane.getChildren().remove(nodeToRemove);
            gridPane.requestLayout(); // Solicita una actualización de layout
		}
	}
	
	public GameObject findObject(Position pos) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject currentObject  = objects.get(i);
			if (pos.equals(currentObject.getPosition())) {
				return currentObject;
			}
		}
		return null;
	}
	
	private void makePiecesFall(GridPane gridPane) {
		for (int col = 0; col < Game.DIM_X; col++) {
		    for (int row = Game.DIM_Y - 1; row > 0; row--) {
		        Position currentPos = new Position(col, row);
		        GameObject currentObject = findObject(currentPos);

		        if (currentObject == null) {
		            Position abovePos = new Position(col, row - 1);
		            GameObject aboveObject = findObject(abovePos);
		           

		            if (aboveObject != null) {
		                movePiece(findObject(abovePos), currentPos);
		                movePieceInGridPane(gridPane, col, row - 1, row);	                
		            }	            
		        }	        
		    }
		}
	    // After moving pieces internally, update the view
	    Platform.runLater(() -> {
	        gridPane.requestLayout();
	    });
	    for (int row = 0; row < Game.DIM_Y; row++) {
	        for (int col = 0; col < Game.DIM_X; col++) {
	            GameObject obj = findObject(new Position(col, row));
	            if (obj != null) {
	                System.out.print(obj.getTurn() + " ");
	            } else {
	                System.out.print("- ");
	            }
	        }
	        System.out.println();
	    }
	    System.out.println();
	}


	private Node findNodeInGridPane(GridPane gridPane, int col, int row) {
		int contador = 0;
	    for (Node child : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(child) != null && GridPane.getColumnIndex(child) == col &&
	                GridPane.getRowIndex(child) != null && GridPane.getRowIndex(child) == row) {
	        	contador++;
	        	if (contador == 2 | col == 0 | row == 0)
	        		return child;
	        }
	    }
	    return null;
	}
	
	private void movePieceInGridPane(GridPane gridPane, int col, int originalRow, int newRow) {
	    Platform.runLater(() -> {
	        Node nodeToMove = findNodeInGridPane(gridPane, col, originalRow);
	        
	        if (nodeToMove != null) {
	            GridPane.setRowIndex(nodeToMove, newRow); // Mueve la ficha a la nueva fila
	        }
	    });
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
	public boolean isFinished(int turn, int dim) {
		boolean isFinished = false;
		int length;
		if (dim == 7) length = 3;
		else length = 4;
		for (int i = 0; i < objects.size(); i++) {
			
			GameObject currentObject  = objects.get(i);
			Position currentPosition = currentObject.getPosition();
			
			for(Direction dir : Direction.values()) {
				if (fitIn(length, dir, currentPosition) &&
						checkConsecutiveTurns(turn, length, dir, currentPosition)) {
					isFinished = true;
					win(dir, currentPosition, dim);
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
	private void win(Direction dir, Position pos, int dim) {
		int length;
		if (dim == 7) length = 4;
		else length = 3;
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

	public void popOut(int col, GridPane gridPane) {
	    GameObject object = findObject(new Position(col, Game.DIM_Y - 1));
	    
	    if (object != null) {
	        System.out.println("Objeto existente");
	        
	        // Elimina visualmente la ficha
	        Node nodeToRemove = findNodeInGridPane(gridPane, col, Game.DIM_Y - 1);
	        if (nodeToRemove != null) {    
	                gridPane.getChildren().remove(nodeToRemove);
	                gridPane.requestLayout(); // Solicita una actualización de layout
	        }
	        
	        remove(object);
	        makePiecesFall(gridPane); // Hacer que las piezas "caigan"
	    }
	}
	
	public void reset(GridPane gridPane) {
	    int size = objects.size();
	    int columnIndex = -1; // Columna anterior
	    int rowIndex = -1; // Fila anterior
	    int removedCount = 0; // Contador de fichas eliminadas
	    
	    Iterator<Node> iterator = gridPane.getChildren().iterator();
	    while (iterator.hasNext()) {
	        Node child = iterator.next();
	        Integer currentColumnIndex = GridPane.getColumnIndex(child);
	        Integer currentRowIndex = GridPane.getRowIndex(child);
	        if (currentColumnIndex != null && currentRowIndex != null) {
	            // Si cambia de posición, reinicia el contador
	            if (currentColumnIndex != columnIndex || currentRowIndex != rowIndex) {
	                columnIndex = currentColumnIndex;
	                rowIndex = currentRowIndex;
	                removedCount = 0;
	            }
	            for (int i = 0; i < size; i++) {
	                GameObject currentObject = objects.get(i);
	                int col = currentObject.getPosition().getCol();
	                int row = currentObject.getPosition().getRow();
	                if (currentColumnIndex == col && currentRowIndex == row) {
	                    if (removedCount == 1) { // Si ya se ha eliminado una ficha
	                        iterator.remove(); // Elimina la ficha del GridPane
	                        break;
	                    } else {
	                        removedCount++; // Incrementa el contador
	                    }
	                }
	            }
	        }
	    }
	}


	public List<List<Position>> getWinners() {
		return winners;
	}


	
}


