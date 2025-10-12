//Katherine Hoadley
package application;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {

    private Game game;

    @Override
    public void start(Stage stage) {
    	
        Group root = new Group();

        game = new Game(root);
        
        root.getChildren().add(game.getPaddle().getPaddle());
        root.getChildren().add(game.getBall().getBall());
        root.getChildren().add(game.getText());


        // Add bricks
        for (Brick brick : game.getBrickWall().getBrickWall()) {
            root.getChildren().add(brick.getBrick());
        }

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!game.getIsRunning()) { 
                    game.getBall().launchBall();
                    game.startGame();
                }
            }
            game.getPaddle().handleKeyInput(event.getCode());
        });

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.step();
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}