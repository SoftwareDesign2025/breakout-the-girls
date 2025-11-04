package application;

import javafx.scene.Group;

public class GameManager {
    private Group root;
    private Game currentGame;
    private WindowDimensions window;

    public GameManager(Group root) {
        this.root = root;
        this.window = new WindowDimensions();
    }

    public Game getGame() {
        return currentGame;
    }

    public void loadGame(String gameType) {
        root.getChildren().clear();
        currentGame = new Game(root, window, gameType);
    }
}