//Katherine Hoadley

package application;

//This is the main entry point for the Breakout game application.
// It initializes the JavaFX stage, sets up the game environment,
// handles user keyboard input, and manages the main animation loop.

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {
	

	// This method sets up the JavaFX stage, creates the game environment,
	// defines keyboard controls for gameplay, and starts the main animation loop.
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
        			if (game.getLives() == 3 || game.getBrickWall().getWall().isEmpty()) {
        				game.startRound();
        			} else {
        				game.startAfterLifeLost();
        			}
        		}
        	} else if (code == KeyCode.RIGHT) {
        		game.getPaddle().startMovingRight();
        	} else if (code == KeyCode.LEFT) {
        		game.getPaddle().startMovingLeft();
        	}
        	
        	//easter eggs
        	else if(code ==KeyCode.Q) {
        		game.skipToLevel(2);
        	}
        	else if(code ==KeyCode.W) {
        		game.skipToLevel(3);
        	}
        });
        
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                game.getPaddle().stopMovingRight();
            } else if (event.getCode() == KeyCode.LEFT) {
                game.getPaddle().stopMovingLeft();
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            		game.getPaddle().update();
                game.step();
                
            }
        };
        timer.start();
    }
    
    //Launches the JavaFX application and triggers start method
    public static void main(String[] args) {
        launch(args);
    }
}