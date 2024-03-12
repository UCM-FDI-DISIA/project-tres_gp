package gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import gp.logic.Game;
import static gp.view.Messages.error;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
	private static final int TITLE_SIZE = 80;
	private Disc[][] grid = new Disc[Game.DIM_X][Game.DIM_Y];
	private Pane discRoot = new Pane();
	private static Game game; 
	public static void main(String[] args) {
		game = new Game();
        try {
            launch(args);
		} catch (Exception e) {
			System.out.println(error(e.getMessage()));
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception{
		stage.setScene(new Scene(createContent()));
		stage.show();
	}
	
	private Parent createContent() {
		Pane root = new Pane();
		root.getChildren().add(discRoot);
		
		Shape gridShape = makeGrid();
		
		root.getChildren().add(gridShape);
		root.getChildren().addAll(makeColumns());
		return root;
	}
	private Shape makeGrid() {
	    Shape shape = new Rectangle((Game.DIM_X + 1) * TITLE_SIZE, (Game.DIM_Y + 1) * TITLE_SIZE);
	    for (int i = 0; i < Game.DIM_Y; i++) {
	        for (int j = 0; j < Game.DIM_X; j++) {
	            Circle circle = new Circle(TITLE_SIZE / 2);
	            circle.setCenterX(TITLE_SIZE / 2);
	            circle.setCenterY(TITLE_SIZE / 2);
	            circle.setTranslateX(j * (TITLE_SIZE + 5) + TITLE_SIZE / 4);
	            circle.setTranslateY(i * (TITLE_SIZE + 5) + TITLE_SIZE / 4);

	            shape = Shape.subtract(shape, circle);
	        }
	    }
	    Light.Distant light = new Light.Distant();
	    light.setAzimuth(45.0);
	    light.setElevation(30.0);
	    
	    Lighting lighting = new Lighting();
	    lighting.setLight(light);
	    lighting.setSurfaceScale(5.0);    
	   
	    shape.setFill(Color.BLUE);
	    shape.setEffect(lighting);
	    
	    return shape;
	}
	
	private List<Rectangle> makeColumns() {
	    List<Rectangle> list = new ArrayList<>();

	    for (int i = 0; i < Game.DIM_X; i++) {
	        Rectangle rect = new Rectangle(TITLE_SIZE, (Game.DIM_Y + 1) * TITLE_SIZE);
	        rect.setTranslateX(i * (TITLE_SIZE + 5) + TITLE_SIZE / 4);
	        rect.setFill(Color.TRANSPARENT);

	        rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
	        rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

	        final int column = i;
	        rect.setOnMouseClicked(e -> {
	            if (game.getTurn() == 1) {
	                placeDisc(new Disc(), column);
	            } else {
	                placeDisc(new Disc(), column);
	            }
	        });

	        list.add(rect);
	    }
	    return list;
	}
	private void placeDisc(Disc disc, int column) {
		int row = Game.DIM_Y -1;
		do {
			if(!getDisc(column, row).isPresent())
				break;
			row--;		
		} while(row >= 0);
		if(row<0)
			return;
		grid[column][row] = disc;
		
		discRoot.getChildren().add(disc);
		disc.setTranslateX(column * (TITLE_SIZE + 5) + TITLE_SIZE / 4);
		
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
		animation.setToY(row * (TITLE_SIZE + 5) + TITLE_SIZE / 4);
		animation.setOnFinished(e ->{
			if(game.isFinished()) {
				gameOver();
			}
			game.update();
		});
		animation.play();
	}
	private void gameOver() {
		System.out.println("Winner:"+ (game.getTurn()  == 1? "RED": "YELLOW"));
	}
	
	private Optional<Disc> getDisc(int column, int row){
		if (column < 0 || column >= Game.DIM_X
				|| row <0 ||row>=Game.DIM_Y) {
			return Optional.empty();
		}
		return Optional.ofNullable(grid[column][row]);
	}
	private static class Disc extends Circle {
		public Disc() {
			super(TITLE_SIZE / 2 , game.getTurn() == 1 ? Color.RED:Color.YELLOW);
			setCenterX(TITLE_SIZE / 2);
			setCenterY(TITLE_SIZE / 2);
		}
	}


}
