import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {

    private Game game;

    @Override
    public void start(Stage stage) {
    	
        Group root = new Group();

        game = new Game(root);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
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