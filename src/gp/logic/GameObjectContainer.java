package gp.logic;

import java.util.List;

import gp.GameObjects.GameObject;

import java.util.ArrayList;

public class GameObjectContainer {
	private List<GameObject> objects;
	
	public GameObjectContainer() {
		objects = new ArrayList<>();
	}
	
	public String toString(Position position) {
		String obj=" ";
		int i=0;
		
		while(obj==" " && i<objects.size()) {
			GameObject c=objects.get(i);
			obj = c.toString(c,position);
			i++;
		}
		return obj;
	}
}
