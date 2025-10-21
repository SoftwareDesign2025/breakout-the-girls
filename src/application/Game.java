// Game class
// Johnathan Meeks
package application;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Game {
	private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8); // COLOR: BLACK / 80% OPACITY 
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final int TITLE_FONT_SIZE = 50;
	private static final int SCORE_FONT_SIZE = 40;
	private static final String WIN_MESSAGE = "YOU WIN!";
	private static final String LOSE_MESSAGE = "GAME OVER!";
	private static final String FINAL_SCORE_STRING = "Final score: ";
	private static final double TITLE_Y_POSITION_FACTOR = 4.0;
	private static final double SCORE_Y_POSITION_FACTOR = 2.0;
	
	private Environment environment;
	private Collisions checkCollision;
	private double elapsedTime = 1.0 / 60.0;
	private boolean isRunning = false;
	private int lives = 3;
	private Text scoreText = new Text();
	private Text titleText = new Text();
	private Rectangle endGameBackground;
		private int roundsCompleted = 0;
	private int roundsWon = 0;
	
	
	//levels
	private Level levelOne;
	private Level leveTwo;
	private Level levelThree;
	//screens
	private MainScreen mainScreen; 
	
	
	private Level determineLevel(int windowWidth, int windowHeight) {
		if (roundsCompleted == 0) {
			return new LevelOne(windowWidth, windowHeight);
		} else if (roundsCompleted == 1) {
			return new LevelTwo(windowWidth, windowHeight);
		} else {
			return new LevelThree(windowWidth, windowHeight);
		}
	}
	
	
	private void handleBallLost() {
		if (environment.getBall().checkIfRoundLost()) {
	        lives--;
	        if (lives > 0) {
	            // Reset the ball for the next round
	            environment.getBall().resetBallPosition(environment.getWindowWidth(), environment.getWindowHeight());
	            isRunning = false; // pause until space is pressed again
	        } else {
	            endRound(false);
	        }
	    }
	}
	
	
	private void configureEndGameDisplayText(Text text, double yPosition, Font font) {
		text.setFont(font);
		text.setFill(TEXT_COLOR);
		text.setX((environment.getWindowWidth() - text.getLayoutBounds().getWidth()) / 2);
		text.setY(yPosition);
		
		if (!environment.getRoot().getChildren().contains(text)) {
			environment.getRoot().getChildren().add(text);
		}
	}
	
	
	private void endGame(boolean win) {
		isRunning = false;

		if (endGameBackground == null) {
			endGameBackground = new Rectangle(0, 0, environment.getWindowWidth(), environment.getWindowHeight());
			endGameBackground.setFill(OVERLAY_COLOR);
			environment.getRoot().getChildren().add(endGameBackground);
		} 
		else {
			endGameBackground.setFill(OVERLAY_COLOR);
		}

		String titleString;
		if (win) {
		    titleString = WIN_MESSAGE;
		} 
		else {
		    titleString = LOSE_MESSAGE;
		}
		
		titleText.setText(titleString);
		configureEndGameDisplayText(titleText, environment.getWindowHeight() / TITLE_Y_POSITION_FACTOR, new Font(TITLE_FONT_SIZE));
		scoreText.setText(FINAL_SCORE_STRING + environment.getScore().getCurrentScore());
		configureEndGameDisplayText(scoreText, environment.getWindowHeight() / SCORE_Y_POSITION_FACTOR, new Font(SCORE_FONT_SIZE));
	}
	
	
	
	public Game(Group root, int windowWidth, int windowHeight) {
		Level level = determineLevel(windowWidth, windowHeight);
		environment = new Environment(root, windowWidth, windowHeight, level);
		checkCollision = new Collisions(environment);
		root.getChildren().add(titleText);
		root.getChildren().add(scoreText);
		
		mainScreen = new MainScreen(root, windowWidth, windowHeight);
		mainScreen.getStartButton().setOnAction(event -> {
			startGame();
		});

	}
	
	
	public void startGame() {
		if (roundsCompleted < 3) {
			mainScreen.hide();
			isRunning = true;
			environment.resetEnvironment();
			environment.getBall().launchBall();
		}
	}
	
	
	public void step() {
		if (!isRunning) {
			return;
		}
		environment.getBall().move(elapsedTime);
		checkCollision.checkAllCollisions();
		handleBallLost();
		
		if (environment.getBrickWall().getBrickWall().isEmpty()) {
			roundsWon += 1;
			endRound(true);
		}
	}
	
	
	public boolean getIsRunning() {
		return isRunning;
	}
	
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void startAfterLifeLost() {
		isRunning = true;
		environment.getBall().launchBall();
	}
	
	public void startRound() {
		environment.getBall().launchBall();
	}
	
	public void endRound(boolean win) {
		isRunning = false;
		
		if (win) {
			roundsWon += 1;
		}
		roundsCompleted += 1;
		
		titleText.setX(environment.getWindowWidth()/2.0);
		titleText.setY(environment.getWindowHeight()/2.0);
		titleText.setFont(new Font(30));
		

		if (win) {
			titleText.setText("YOU WIN THE ROUND: Score" + environment.getScore().getCurrentScore());
		} else {
			titleText.setText("YOU LOST THE ROUND!");
		}
		
		if (roundsCompleted < 3) {
			Level nextLevel = determineLevel(environment.getWindowWidth(), environment.getWindowHeight());
			Group root = environment.getRoot();
			root.getChildren().clear();
			environment = new Environment(root, environment.getWindowWidth(), environment.getWindowHeight(), nextLevel);
			checkCollision = new Collisions(environment);
		} else {
			endGame(roundsWon == 3);
		}
	}
	
	public void checkIfGameOver () {
		if (roundsWon == 3) {
			titleText.setX(environment.getWindowWidth()/2.0);
			titleText.setY(environment.getWindowHeight()/2.0);
			titleText.setFont(new Font(30));
			titleText.setText("YOU WON ALL THE ROUNDS!: Final Score" + environment.getScore().getCurrentScore());
		}
	}

}
	
	
	
	
	

	


