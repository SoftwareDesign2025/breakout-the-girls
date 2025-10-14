package application;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
	private Environment environment;
	private Collisions checkCollision;
	private double elapsedTime = 1.0 / 60.0;
	private boolean isRunning = false;
	private int lives = 3;
	private int roundsCompleted = 0;
	private int roundsWon = 0;
	private Text text = new Text();
		
	public Game(Group root, int windowWidth, int windowHeight) {
		Level level = new LevelOne(windowWidth, windowHeight);
		environment = new Environment(root, windowWidth, windowHeight, level);
		checkCollision = new Collisions(environment);
		root.getChildren().add(text);
	}
	
	public void startGame() {
		if (roundsCompleted < 3) {
			isRunning = true;
			environment.resetEnvironment();
			environment.getBall().launchBall();
		}
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
		roundsCompleted += 1;
		
		text.setX(environment.getWindowWidth()/2.0);
		text.setY(environment.getWindowHeight()/2.0);
		text.setFont(new Font(30));
		

		if (win) {
			text.setText("YOU WIN THE ROUND: Score" + environment.getScore().getCurrentScore());
		} else {
			text.setText("YOU LOST THE ROUND!");
		}
		
		if (roundsCompleted == 3) {
			endGame(roundsWon == 3);
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
	
	private void endGame(boolean win) {
		isRunning = false;
		text.setX(environment.getWindowWidth()/2.0);
		text.setY(environment.getWindowHeight()/2.0);
		text.setFont(new Font(30));

		if (win) {
			text.setText("YOU WIN!: Final Score" + environment.getScore().getCurrentScore());
		}
		else {
			text.setText("GAME OVER... Final Score: " + environment.getScore().getCurrentScore());
		}
	}
	
	public void checkIfGameOver () {
		if (roundsWon == 3) {
			text.setX(environment.getWindowWidth()/2.0);
			text.setY(environment.getWindowHeight()/2.0);
			text.setFont(new Font(30));
			text.setText("YOU WON ALL THE ROUNDS!: Final Score" + environment.getScore().getCurrentScore());
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
}
