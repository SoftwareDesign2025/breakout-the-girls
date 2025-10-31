// Game class

// Johnathan Meeks
package application;

import java.util.HashMap;

import javafx.scene.Group;


public class Game {
	private final double ELAPSED_TIME = 1.0 / 60.0;
	
	private boolean gameOver = false;
	private boolean isRunning = false;
	private boolean isWaitingForNextRound = false;
	private double roundOverDelayTime = 0.0;
	private int lives = 3;
	private int roundsCompleted = 0;
	private int roundsWon = 0;
	private static final double ROUND_END_DISPLAY_SECONDS = 5.0;

	private MainScreen mainScreen; 
	private Score score;
	private ScoreUI scoreUi;
	private int windowWidth;
	private int windowHeight;
	private Level level;
	private GameScreen ui;
	private Environment environment;
	
	
	/* Game Constructor
	 * Sets up initial game environment and main screen
	 */
	public Game(Group root, int windowWidth, int windowHeight, String gameType) {
		this.score = new Score();
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.level = determineLevel(windowWidth, windowHeight);
		this.ui = new GameScreen(root, windowWidth, windowHeight);
		this.scoreUi = new ScoreUI(root, windowWidth, windowHeight);
		if (gameType.equals("Breakout")) {
			this.environment = new BreakoutEnvironment(level, root, ui, windowWidth, windowHeight, score);
		} else if (gameType.equals("Galaga")) {
			this.environment = new GalagaEnvironment(root, ui, windowWidth, windowHeight, score);
		}
		
		implementMainScreen(root, windowWidth, windowHeight);
	}
	
	
	
	public void increaseLives() {
		environment.increaseLives();
	}
	
	
	/* method handleBallLost
	 * determines if the ball going out of bounds mean the player lost that level
	 * or if they get to continue.
	 */
	private void handleBallLost() {
		if (environment.isBallLost()) {
	        lives--;
	        
	        if (lives > 0) {
	            // Reset the ball for the next round
	            environment.resetBallPosition();
	            isRunning = false; // pause until space is pressed again
	        } 
	        else {
	        		gameOver = true;
	            endRound(false);
	        }
	    }
	}
	
	private void waitForNextRound() { 
		isWaitingForNextRound = false;
		resetEnvironmentForNextLevel();
		startRound();
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
	
	
	/* method endGame
	 * creates what is shown once the entire game is over depending on if
	 * the player completed all 3 levels successfully.
	 */
	private void endGame(boolean win) {
		isRunning = false;
		isWaitingForNextRound = false;
		ui.endGameMessage(win, score);
		scoreUi.hide();
	}



	/* method resetEnvironmentForNextLevel
	 * clears the root so the environment from the previous level is gone and the
	 * respective level is shown instead.
	 * Resets lives to 3 for the given level.
	 */
	private void resetEnvironmentForNextLevel() {
		level = determineLevel(windowWidth, windowHeight);
		lives = environment.resetEnvironmentForNextLevel(level);
	}

	/* method startGame
	 * Starts the game the very first time by clearing the main screen, setting up
	 * a new environment, and launching the ball.
	 */
	public void startGame() {
		if (!gameOver && roundsCompleted < 3) {
			mainScreen.hide();
			ui.clearText();
			environment.resetEnvironment();
			environment.launchProjectile();
			scoreUi.show();
			scoreUi.updateScore(score.getCurrentScore());
			isRunning = true;
		}
	}
	
	/* method startRound
	 * start each individual round by launching the ball
	 */
	public void startRound() {
		ui.clearText();
		environment.resetEnvironment();
		environment.launchProjectile();
		scoreUi.show();
		scoreUi.updateScore(score.getCurrentScore());
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
	        return;
	    } 
	    
	    isRunning = false;
	    scoreUi.hide();

	    if (win) { roundsWon ++; }
	    roundsCompleted ++;

	    ui.endRoundMessage(win, score);

	    if (roundsCompleted < 3) {
	        if (win) {
	            roundOverDelayTime = ROUND_END_DISPLAY_SECONDS;
	            isWaitingForNextRound = true;
	        }
	    } 
	    else {
	        if (roundsWon == 3) {
	            checkIfGameOver();
	        }
	        endGame(roundsWon == 3);
	    }
	}
	
	/* method startAfterLifeLost
	 * if the ball is lost during a level, reset the ball so the player
	 * can continue to play the game.
	 */
	public void startAfterLifeLost() {
		environment.launchProjectile();
		scoreUi.show();
		scoreUi.updateScore(score.getCurrentScore());
		isRunning = true;
	}
	
	
	/* method checkIfGameOver
	 * creates the text object that will be displayed once the game is over.
	 */
	public void checkIfGameOver() {
		if (roundsWon == 3) {
			ui.gameWonMessage(score);
		}
	}
	
	public void step() {
		// 5-second countdown in between round ==> next round
		if (isWaitingForNextRound) {
			roundOverDelayTime -= ELAPSED_TIME; 
			if (roundOverDelayTime <= 0) {
				waitForNextRound(); 
			}
			return; 
		}
		if (!isRunning) {
			return;
		}
		environment.moveProjectile(ELAPSED_TIME);
		environment.checkAllCollisions();
		scoreUi.updateScore(score.getCurrentScore());
		handleBallLost();
		
		boolean isRoundEnded = environment.isWallEmpty();
		if (isRoundEnded) {
			endRound(true);
		}
	}
	
	
	//Katherine Hoadley
	//Easter egg that lets you skip to level
	//2 or 3 to make testing easy
	public void skipToLevel(int levelNumber) {
		this.roundsCompleted = levelNumber - 1; // so determineLevel gets correct key
	    this.roundsWon = levelNumber - 1;

	    resetEnvironmentForNextLevel();
	    startRound();
	}
	
	public boolean getIsRunning() {
		return isRunning;
	}
	
	public int getLives() {
		return lives;
	}
	
	public UserControl getController() {
		return environment.getController();
	}
	
	public TargetWall getBrickWall() {
		return environment.getWall();
	}
	
}
	


