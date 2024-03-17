package gp;

import gp.control.Controller;
import gp.exceptions.CommandParseException;
import gp.logic.Game;
import gp.view.Messages;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	try {
    	    System.out.println("Cargando archivo FXML...");
    	    Parent root = FXMLLoader.load(getClass().getResource("PORTADA INICIAL.fxml"));
    	    System.out.println("Archivo FXML cargado correctamente.");
    	    Scene scene = new Scene(root);
    	    primaryStage.setScene(scene);
    	    primaryStage.show();
    	} catch (IOException e) {
    	    System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
    	    e.printStackTrace();
    	}
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}