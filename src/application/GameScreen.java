// Johnatha

package application;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameScreen extends Screen {

	private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8); // COLOR: BLACK / 80% OPACITY 
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final int TITLE_FONT_SIZE = 50;
	private static final int SCORE_FONT_SIZE = 40;
	private static final String WIN_MESSAGE = "YOU WIN!";
	private static final String LOSE_MESSAGE = "GAME OVER!";
	private static final String FINAL_SCORE_STRING = "Final score: ";
	private static final double TITLE_Y_POSITION_FACTOR = 4.0;
	private static final double SCORE_Y_POSITION_FACTOR = 2.0;
	
	private Text scoreText = new Text();
	private Text titleText = new Text();

	
	public GameScreen(Group root, int windowWidth, int windowHeight) {
		super(root, windowWidth, windowHeight);

		root.getChildren().add(titleText);
		root.getChildren().add(scoreText);
	}
	
	
	private String checkWinState(boolean win, String onWin, String onLost) {
		if (win) {
			return onWin;
		}
		else {
			return onLost;
		}
	}
	
	
	//function inside can be called directly instead but will do that later
	public void startText() {
		initializeText(List.of(titleText, scoreText));
	}
	
	//function inside can be called directly instead but will do that later
	public void resetText() {
		addUIElements(List.of(titleText, scoreText));  // Just use addUIElements!
	}
	
	public void endGameMessage(boolean win, Score score) { 
		configureOverlay(OVERLAY_COLOR);
		showOverlay();
		
		String scoreTextValue = FINAL_SCORE_STRING + score.getCurrentScore();
		String titleString = checkWinState(win, WIN_MESSAGE, LOSE_MESSAGE);

		configureText(titleText, titleString, getCenteredX(titleText), windowHeight / TITLE_Y_POSITION_FACTOR, 
				new Font(TITLE_FONT_SIZE), TEXT_COLOR);
		configureText(scoreText, scoreTextValue, getCenteredX(scoreText), windowHeight / SCORE_Y_POSITION_FACTOR, 
				new Font(SCORE_FONT_SIZE), TEXT_COLOR);
	}
	
	public void endRoundMessage(boolean win, Score score) {
		configureOverlay(OVERLAY_COLOR);
		
		String roundWon = "YOU WON THE ROUND! \n SCORE: " + score.getCurrentScore();
		String roundLost = "YOU LOST THE ROUND";
		String message = checkWinState(win, roundWon, roundLost);
		
		configureText(titleText, message, getCenteredX(titleText), windowHeight / TITLE_Y_POSITION_FACTOR, 
				new Font(TITLE_FONT_SIZE), TEXT_COLOR);
		
		titleText.toFront();
		scoreText.toFront();
	}
	
	public void gameWonMessage(Score score) {
		titleText.setX(windowWidth/2.0);
		titleText.setY(windowHeight/2.0);
		titleText.setFont(new Font(30)); // magic values above and herefix
		titleText.setText("YOU WON ALL THE ROUNDS!: Final Score" + score.getCurrentScore());
	}


	@Override
	public void hide() {
		addUIElements(List.of(backgroundOverlay, titleText, scoreText));
	}


	@Override
	public void display() {
		removeUIElements(List.of(backgroundOverlay, titleText, scoreText));
	}

	@Override
	public void clearText() {
		titleText.setText("");
	    scoreText.setText("");
	}
}
