package application;

import javafx.scene.Group;

public class GameManager {
    private Group root;
    private Game currentGame;

    public GameManager(Group root) {
        this.root = root;
    }

    public Game getGame() {
        return currentGame;
    }

    public void loadGame(String gameType) {
        root.getChildren().clear();
        currentGame = new Game(root, 800, 600, gameType);
    }
}