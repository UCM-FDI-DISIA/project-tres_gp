package gp;

import java.util.Scanner;

import gp.control.Controller;
import gp.logic.Game;
import gp.logic.GameObjectContainer;
import gp.view.Messages;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(game, scanner);
        
        System.out.println(Messages.WELCOME);
        controller.run();
        System.out.println(Messages.PROMPT);
	}

}
