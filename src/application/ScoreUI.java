package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ScoreUI {
    private static final int FONT_SIZE = 24;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final int MARGIN = 15; 
    
    private Group root;
    private Text scoreText;
    private int windowWidth;
    private int windowHeight;
    
    
    public ScoreUI(Group root, int windowWidth, int windowHeight) {
        this.root = root;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        initializeScoreText();
        updateScore(0);
    }
    
    
    private void initializeScoreText() {
        scoreText = new Text();
        scoreText.setFont(new Font(FONT_SIZE));
        scoreText.setFill(TEXT_COLOR);
        updatePosition();
    }
    
    private void updatePosition() {
        scoreText.setY(windowHeight - MARGIN);
    }
    
    
    public void updateScore(int score) {
        scoreText.setText("Score: " + score);
        scoreText.setX(windowWidth - scoreText.getLayoutBounds().getWidth() - MARGIN);
    }
    
    public void show() {
        if (!root.getChildren().contains(scoreText)) {
            root.getChildren().add(scoreText);
        }
        scoreText.toFront();
    }
    
    public void hide() {
        root.getChildren().remove(scoreText);
    }
}