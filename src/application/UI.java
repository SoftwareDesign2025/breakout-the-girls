package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI {

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
	private Rectangle backgroundOverlay;
	
	private Group root;
	private int windowWidth;
	private int windowHeight;
	
	public UI(Group root, int windowWidth, int windowHeight) {
		this.root = root;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;

		root.getChildren().add(titleText);
		root.getChildren().add(scoreText);
	}
	
	public void configureOverlay(Rectangle overlay) {
		if (overlay == null) {
			overlay = new Rectangle(0, 0, windowWidth, windowHeight);
			overlay.setFill(OVERLAY_COLOR);
	        root.getChildren().add(overlay);
	    } 
	    else {
	    	overlay.setWidth(windowWidth);
	    	overlay.setHeight(windowHeight);
	    	overlay.setFill(OVERLAY_COLOR);
	        if (!root.getChildren().contains(overlay)) {
	        	root.getChildren().add(overlay);
	        }
	    }
	}
	
	/* method configureEndGameDisplayText
	 * 
	 */
	public void configureGameDisplayText(Text text, double yPosition, Font font, String textValue) {
		configureOverlay(backgroundOverlay);
	    
		text.toFront();
		text.setText(textValue);
		text.setFont(font);
		text.setFill(TEXT_COLOR);
		text.setX((windowWidth - text.getLayoutBounds().getWidth()) / 2);
		text.setY(yPosition);
		
		root.getChildren().remove(text);
		root.getChildren().add(text);
	}
	
	public void endGameMessage(boolean win, Score score) { 
		if (backgroundOverlay == null) {
			backgroundOverlay = new Rectangle(0, 0, windowWidth, windowHeight);
			backgroundOverlay.setFill(OVERLAY_COLOR);
			root.getChildren().add(backgroundOverlay);
		} 
		else {
			backgroundOverlay.setFill(OVERLAY_COLOR);
			backgroundOverlay.toFront(); //
		}

		String titleString;
		if (win) {
			titleString = WIN_MESSAGE;
		} 
		else {
			titleString = LOSE_MESSAGE;
		}

		titleText.setText(titleString);
		String scoreTextValue = FINAL_SCORE_STRING + score.getCurrentScore();
		configureGameDisplayText(titleText, windowHeight / TITLE_Y_POSITION_FACTOR, new Font(TITLE_FONT_SIZE), titleString);
		configureGameDisplayText(scoreText, windowHeight / SCORE_Y_POSITION_FACTOR, new Font(SCORE_FONT_SIZE),scoreTextValue);

		titleText.toFront(); //
		scoreText.toFront();///
	}
	
	public void endRoundMessage(boolean win, Score score) {
		String message;
		if (win) {
			message = "YOU WON THE ROUND! \n SCORE: " + score.getCurrentScore();
		} 
		else {
			message = "YOU LOST THE ROUND";
		}
		configureGameDisplayText(titleText, 
				windowHeight / TITLE_Y_POSITION_FACTOR, new Font(TITLE_FONT_SIZE), message);
		titleText.toFront();
		scoreText.toFront();
	}
	
	public void resetText() {
		if (!root.getChildren().contains(titleText)) {
	        root.getChildren().add(titleText);
	    }
	    if (!root.getChildren().contains(scoreText)) {
	        root.getChildren().add(scoreText);
	    }
	}
	
	public void startText() {
		titleText.setText(""); 
		scoreText.setText("");
	}
	
	public void gameWonMessage(Score score) {
		titleText.setX(windowWidth/2.0);
		titleText.setY(windowHeight/2.0);
		titleText.setFont(new Font(30));
		titleText.setText("YOU WON ALL THE ROUNDS!: Final Score" + score.getCurrentScore());
	}


}
