// Game class

// Johnathan Meeks
package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;


public class Game {
	private final double ELAPSED_TIME = 1.0 / 60.0;
	private final int MAX_ROUNDS = 3;
	private final int MAX_LIVES = 3;
	
	private boolean gameOver = false;
	private boolean isRunning = false;
	private boolean isWaitingForNextRound = false;
	private double roundOverDelayTime = 0.0;
	private int lives = MAX_LIVES;
	private int roundsCompleted = 0;
	private int roundsWon = 0;
	private static final double ROUND_END_DISPLAY_SECONDS = 5.0;
	private double bugDropTimer = 0.0;
	private static final double DROP_BUG_INTERVAL = 5; 

	private MainScreen mainScreen; 
	private String gameTitle;
	private Score score;
	private ScoreUI scoreUi;
	private WindowDimensions window;
	private Level level;
	private GameScreen ui;
	private Environment environment;
	
	
	/* Game Constructor
	 * Sets up initial game environment and main screen
	 */
	public Game(Group root, WindowDimensions window, String gameType) {
		this.gameTitle = gameType;
		this.score = new Score();
		this.window = window;
		this.ui = new GameScreen(root, window);

		
		this.level = determineLevel();
		this.scoreUi = new ScoreUI(root, window);
		
		HashMap<String, Environment> gameEnvironments = new HashMap<>();
	    gameEnvironments.put("Breakout", new BreakoutEnvironment(level, root, ui, score, window));
	    gameEnvironments.put("Galaga", new GalagaEnvironment(root, ui, score, window));

	    this.environment = gameEnvironments.get(gameType);
		
		implementMainScreen(root);
	}
	
	
	
	public void increaseLives() {
		environment.increaseLives();
	}
	
	
	/* method handleBallLost
	 * determines if the ball going out of bounds mean the player lost that level
	 * or if they get to continue.
	 */
	private void handleBallLost() {
		if (environment.handleLifeLost()) {
	        lives--;
	        
	        if (lives > 0) {
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
	private void implementMainScreen(Group root) {
		mainScreen = new MainScreen(root, window, gameTitle);
		mainScreen.getStartButton().setOnAction(event -> {
			startGame();
		});
	}
	
	/* method determineLevel
	 * uses polymorphism to select the correct level based on the number of rounds
	 * the player has completed.
	 */
	private Level determineLevel() {
		HashMap<Integer,Level> levels = new HashMap<>();
		levels.put(1, new LevelOne(window));
		levels.put(2, new LevelTwo(window));
		levels.put(3, new LevelThree(window));

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
		level = determineLevel();
		lives = environment.resetEnvironmentForNextLevel(level);
	}

	/* method startGame
	 * Starts the game the very first time by clearing the main screen, setting up
	 * a new environment, and launching the ball.
	 */
	public void startGame() {
		if (!gameOver && roundsCompleted < MAX_ROUNDS) {
			mainScreen.hide();
			ui.clearText();
			environment.resetEnvironment();
			environment.launchProjectile();
			scoreUi.show();
			scoreUi.updateScore(score.getCurrentScore());
			scoreUi.updateLives(lives);
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
		scoreUi.updateLives(lives);
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

	    if (roundsCompleted < MAX_ROUNDS) {
	        if (win) {
	            roundOverDelayTime = ROUND_END_DISPLAY_SECONDS;
	            isWaitingForNextRound = true;
	        }
	    } 
	    else {
	        if (roundsWon == MAX_ROUNDS) {
	            checkIfGameOver();
	        }
	        endGame(roundsWon == MAX_ROUNDS);
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
		if (roundsWon == MAX_ROUNDS) {
			ui.gameWonMessage(score);
		}
	}
	
	public void step() {
		bugDropTimer += ELAPSED_TIME;
		if (bugDropTimer >= DROP_BUG_INTERVAL) {
		    bugDropTimer = 0.0;
		    environment.triggerBugDrop(); 
		}
		
		ArrayList<Target> bugsOutOfBounds = environment.moveDroppedBug(ELAPSED_TIME);
		if (!bugsOutOfBounds.isEmpty()) {
		    lives -= bugsOutOfBounds.size();
		    
		    if (lives <= 0) {
		        gameOver = true;
		        endRound(false);
		        return;
		    }
		}
		
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
		environment.moveProjectiles(ELAPSED_TIME);
		environment.checkAllCollisions();
		scoreUi.updateScore(score.getCurrentScore());
		scoreUi.updateLives(lives);
		handleBallLost();
		
		if(environment.isWallEmpty()) {
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
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public UserControl getController() {
		return environment.getController();
	}
	
	public TargetWall getBrickWall() {
		return environment.getWall();
	}
	
}
	


