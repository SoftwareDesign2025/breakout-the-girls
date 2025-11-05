package application;

import javafx.scene.Group;

public class GameManager {
    private Group root;
    private Game currentGame;
    private WindowDimensions window;
    TitleScreen titleScreen;

    public GameManager(Group root) {
        this.root = root;
        this.window = new WindowDimensions();
        this.titleScreen = new TitleScreen(root, window);
    }
    

    public Game getGame() {
        return currentGame;
    }

    public void loadGame(String gameType) {
    	titleScreen.hide();
        root.getChildren().clear();
        currentGame = new Game(root, window, gameType);
    }
    
    public void showTitleScreen() {
        titleScreen.display();
    }
}