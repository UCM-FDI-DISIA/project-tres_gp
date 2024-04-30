package gp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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