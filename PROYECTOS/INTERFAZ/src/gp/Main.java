package gp;

import gp.control.Controller;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    	try {
    		   Parent root = FXMLLoader.load(getClass().getResource("PORTADA INICIAL.fxml"));
    		   Scene scene = new Scene(root);
    		   stage.setScene(scene);
    		   stage.show();
    		   
    		  } catch(Exception e) {
    		   e.printStackTrace();
    		  }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}