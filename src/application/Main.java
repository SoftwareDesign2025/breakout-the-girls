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
        GameManager manager = new GameManager(root);
        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Arcade");
        stage.setScene(scene);
        stage.show();
        
        scene.setOnKeyPressed(event -> {
        	KeyCode code = event.getCode();
        	
            if (code == KeyCode.A) {
                manager.loadGame("Breakout");

            } else if (code == KeyCode.B) {
                manager.loadGame("Galaga");

            }
            
            Game game = manager.getGame();
        	
            if (code == KeyCode.SPACE) {
                // If the game isn't running, start it
                if (!game.getIsRunning()) { 
                    if (game.getLives() == 3 || game.getBrickWall().getWall().isEmpty()) {
                        game.startRound();
                    } else {
                        game.startAfterLifeLost();
                    }
                } else {
                    // If game is running, shoot a bullet
                    if (game.getEnvironment() instanceof GalagaEnvironment galagaEnv) 
                    {
                        galagaEnv.shootBullet();
                    }
                }
        	} else if (code == KeyCode.RIGHT) {
        		game.getController().startMovingRight();
        	} else if (code == KeyCode.LEFT) {
        		game.getController().startMovingLeft();
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
            Game game = manager.getGame();
            if (event.getCode() == KeyCode.RIGHT) {
                game.getController().stopMovingRight();
            } else if (event.getCode() == KeyCode.LEFT) {
                game.getController().stopMovingLeft();
            }
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Game game = manager.getGame();
                if (game != null) {
                    game.getController().update();
                    game.step();
                }
            }
        };
        timer.start();
    }
    
    //Launches the JavaFX application and triggers start method
    public static void main(String[] args) {
        launch(args);
    }
}