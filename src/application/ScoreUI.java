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
    private Text livesText; 
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
        
        livesText = new Text();  // Add this
        livesText.setFont(new Font(FONT_SIZE));
        livesText.setFill(TEXT_COLOR);
        
        updatePosition();
    }
    
    private void updatePosition() {
        scoreText.setY(windowHeight - MARGIN);
        livesText.setY(windowHeight - MARGIN - 30);
    }
    
    
    public void updateScore(int score) {
        scoreText.setText("Score: " + score);
        scoreText.setX(windowWidth - scoreText.getLayoutBounds().getWidth() - MARGIN);
    }
    
    public void updateLives(int lives) {
        livesText.setText("Lives: " + lives);
        livesText.setX(windowWidth - livesText.getLayoutBounds().getWidth() - MARGIN);
    }
    
    public void show() {
        if (!root.getChildren().contains(scoreText)) {
            root.getChildren().add(scoreText);
        }
        if (!root.getChildren().contains(livesText)) {
            root.getChildren().add(livesText);
        }
        scoreText.toFront();
        livesText.toFront();
    }

    public void hide() {
        root.getChildren().remove(scoreText);
        root.getChildren().remove(livesText);
    }
}