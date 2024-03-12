package gp;

import java.util.Scanner;


import gp.control.Controller;
import gp.exceptions.CommandExecuteException;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.logic.GameObjectContainer;
import gp.view.Messages;
import static gp.view.Messages.error;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(game, scanner);
        
        System.out.println(Messages.WELCOME);
        try {
			controller.run();
		} catch (Exception e) {
			System.out.println(error(e.getMessage()));
			e.printStackTrace();
			//E
		}
	}

}
