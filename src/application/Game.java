// Game class

// Johnathan Meeks
package application;

import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Game {
	
	// Constants
	private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8); // COLOR: BLACK / 80% OPACITY 
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final int TITLE_FONT_SIZE = 50;
	private static final int SCORE_FONT_SIZE = 40;
	private static final String WIN_MESSAGE = "YOU WIN!";
	private static final String LOSE_MESSAGE = "GAME OVER!";
	private static final String FINAL_SCORE_STRING = "Final score: ";
	private static final double TITLE_Y_POSITION_FACTOR = 4.0;
	private static final double SCORE_Y_POSITION_FACTOR = 2.0;
	
	// Fields
	private Environment environment;
	private Collisions checkCollision;
	private MainScreen mainScreen; 
	private boolean gameOver = false;
	private boolean isRunning = false;

	private double elapsedTime = 1.0 / 60.0;
	private int lives = 3;
	private int roundsCompleted = 0;
	private int roundsWon = 0;


	private Text scoreText = new Text();
	private Text titleText = new Text();
	private Rectangle endGameBackground;
	
	
	/* Game Constructor
	 * Sets up initial game environment and main screen
	 */
	public Game(Group root, int windowWidth, int windowHeight) {
		initializeEnvironment(root, windowWidth, windowHeight);
		root.getChildren().add(titleText);
		root.getChildren().add(scoreText);
		implementMainScreen(root, windowWidth, windowHeight);
	}
	
	/* method implementMainScreen
	 * create screen that allows the user to start the game
	 */
	private void implementMainScreen(Group root, int windowWidth, int windowHeight) {
		mainScreen = new MainScreen(root, windowWidth, windowHeight);
		mainScreen.getStartButton().setOnAction(event -> {
			startGame();
		});
	}
	
	/* method initializeEnvironment
	 *  Initializes the game environment based on the specified window dimensions and 
	 *  determines the appropriate game level.
	 */
	public void initializeEnvironment(Group root, int windowWidth, int windowHeight) {
		Level level = determineLevel(windowWidth, windowHeight);
		environment = new Environment(root, windowWidth, windowHeight, level, this);
		checkCollision = new Collisions(environment);
	}
	
	/* method advanceToNextLevel
	 * restarts the game in order to continue onto to next level.
	 * If they have completed all 3 levels, the game ends.
	 */
//	public void advanceToNextLevel() {
//	    if (roundsCompleted < 3) {
//	        roundsCompleted++;
//	        resetEnvironmentForNextLevel();
//	        startRound();
//	    } else {
//	        endGame(true);
//	    }
//	}
	
	/* method resetEnvironmentForNextLevel
	 * clears the root so the environment from the previous level is gone and the
	 * respective level is shown instead.
	 * Resets lives to 3 for the given level.
	 */
	public void resetEnvironmentForNextLevel() {
        Group root = environment.getRoot();
        root.getChildren().clear();
        initializeEnvironment(root, environment.getWindowWidth(), environment.getWindowHeight());
        lives = 3;
	}

	
	/* method determineLevel
	 * uses polymorphism to select the correct level based on the number of rounds
	 * the player has completed.
	 */
	private Level determineLevel(int windowWidth, int windowHeight) {
		HashMap<Integer,Level> levels = new HashMap<>();
		levels.put(1, new LevelOne(windowWidth, windowHeight));
		levels.put(2, new LevelTwo(windowWidth,windowHeight));
		levels.put(3, new LevelThree(windowWidth,windowHeight));
		
		return levels.get(roundsCompleted+1);
	}
	

	/* method startGame
	 * Starts the game the very first time by clearing the main screen, setting up
	 * a new environment, and launching the ball.
	 */
	public void startGame() {
		if (!gameOver && roundsCompleted < 3) {
			mainScreen.hide();
			environment.resetEnvironment();
			environment.getBall().launchBall();
			isRunning = true;
		}
	}
	
	/* method endGame
	 * creates what is shown once the entire game is over depending on if
	 * the player completed all 3 levels successfully.
	 */
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
	
	
	/* method startRound
	 * start each individual round by launching the ball
	 */
	public void startRound() {
		environment.resetEnvironment();
		environment.getBall().launchBall();
		isRunning = true;
	}
	
	/* method endRound
	 * end each individual round.
	 * determines the correct text to be displayed depending on whether or not the player
	 * won the individual round or not. 
	 * If this was the last level, the game ends. 
	 */
	public void endRound(boolean win) {
		if (gameOver) {
			endGame(false);
		} else {
			isRunning = false;

			if (win) {
				roundsWon += 1;
			}
			roundsCompleted += 1;

			String message;
			if (win) {
				message = "YOU WON THE ROUND! SCORE: " + environment.getScore().getCurrentScore();
			} else {
				message = "YOU LOST THE ROUND";
			}
			createRoundText(message);

			if (roundsCompleted < 3) {
				resetEnvironmentForNextLevel();
			} else {
				if (roundsWon == 3) {
					checkIfGameOver();
				}
				endGame(roundsWon == 3);
			}
		}
	}
	
	/* method createRoundText
	 * create the text object used at the end of each round
	 */
	private void createRoundText(String text) {
		titleText.setText(text);
		titleText.setFont(new Font(30));
		titleText.setX(environment.getWindowWidth()/2.0);
		titleText.setY(environment.getWindowHeight()/2.0);
		
	}
	
	/* method startAfterLifeLost
	 * if the ball is lost during a level, reset the ball so the player
	 * can continue to play the game.
	 */
	public void startAfterLifeLost() {
		environment.getBall().launchBall();
		isRunning = true;
	}
	
	
	/* method checkIfGameOver
	 * creates the text object that will be displayed once the game is over.
	 */
	public void checkIfGameOver () {
		if (roundsWon == 3) {
			titleText.setX(environment.getWindowWidth()/2.0);
			titleText.setY(environment.getWindowHeight()/2.0);
			titleText.setFont(new Font(30));
			titleText.setText("YOU WON ALL THE ROUNDS!: Final Score" + environment.getScore().getCurrentScore());
		}
	}
	
	/* method handleBallLost
	 * determines if the ball going out of bounds mean the player lost that level
	 * or if they get to continue.
	 */
	private void handleBallLost() {
		if (environment.getBall().checkIfRoundLost()) {
	        lives--;
	        if (lives > 0) {
	            // Reset the ball for the next round
	            environment.getBall().resetBallPosition(environment.getWindowWidth(), environment.getWindowHeight());
	            isRunning = false; // pause until space is pressed again
	        } else {
	        		gameOver = true;
	            endRound(false);
	        }
	    }
	}
	
	
	/* method configureEndGameDisplayText
	 * 
	 */
	private void configureEndGameDisplayText(Text text, double yPosition, Font font) {
		text.setFont(font);
		text.setFill(TEXT_COLOR);
		text.setX((environment.getWindowWidth() - text.getLayoutBounds().getWidth()) / 2);
		text.setY(yPosition);
		
		environment.getRoot().getChildren().remove(text);
		environment.getRoot().getChildren().add(text);
	}
	
	
	
	
	public void step() {
		if (!isRunning) {
			return;
		}
		environment.getBall().move(elapsedTime);
		checkCollision.checkAllCollisions();
		handleBallLost();
		
		if (environment.getBrickWall().getBrickWall().isEmpty()) {
			endRound(true);
		}
	}
	
	//Katherine Hoadley
	//Easter egg that lets you skip to level
	//2 or 3 to make testing easy
	public void skipToLevel(int levelNumber) {
		this.roundsCompleted = levelNumber -1;
		this.roundsWon = levelNumber -1;
		
		resetEnvironmentForNextLevel();
		
		startRound();
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
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	

}
	
	
	
	
	

	


