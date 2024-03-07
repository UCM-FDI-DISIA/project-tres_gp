package gp.logic;

import java.util.List;

import gp.GameObjects.GameObject;
import gp.exceptions.FullColumnException;
import gp.view.Messages;

import java.util.ArrayList;

public class GameObjectContainer {
	private List<GameObject> objects;
	
	public GameObjectContainer() {
		objects = new ArrayList<>();
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
            if(!find) {
                return row;
            }
            else {
                find = false;
                row--;
            }
        }
        if(!find) {
            return Game.DIM_Y - 1;
        }
        else throw new FullColumnException(Messages.FULL_COLUMN_MESSAGE.formatted(col));


    }
}
