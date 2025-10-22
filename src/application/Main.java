//Katherine Hoadley
package application;

// Main Class

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {
	


    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Game game = new Game(root, 800, 600);
        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();
        
        scene.setOnKeyPressed(event -> {
        	KeyCode code = event.getCode();

        	if (code == KeyCode.SPACE) {
        		if (!game.getIsRunning()) { 
        			if (game.getLives() == 3 || game.getEnvironment().getBrickWall().getBrickWall().isEmpty()) {
        				game.startGame();
        			} else {
        				game.startAfterLifeLost();
        			}
        		}
        	} else if (code == KeyCode.RIGHT) {
        		game.getEnvironment().getPaddle().startMovingRight();
        	} else if (code == KeyCode.LEFT) {
        		game.getEnvironment().getPaddle().startMovingLeft();
        	}
        });
        
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                game.getEnvironment().getPaddle().stopMovingRight();
            } else if (event.getCode() == KeyCode.LEFT) {
                game.getEnvironment().getPaddle().stopMovingLeft();
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            		game.getEnvironment().getPaddle().update();
                game.step();
                
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}