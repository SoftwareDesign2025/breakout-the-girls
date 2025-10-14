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
	private Text text = new Text();
	

	private void handleBallLost() {
		if (environment.getBall().checkIfRoundLost()) {
	        lives--;
	        if (lives > 0) {
	            // Reset the ball for the next round
	            environment.getBall().resetBallPosition(environment.getWindowWidth(), environment.getWindowHeight());
	            isRunning = false; // pause until SPACE is pressed again
	        } else {
	            endGame(false);
	        }
	    }
	}
	
	private void endGame(boolean win) {
		isRunning = false;
		text.setX(environment.windowWidth/2.0);
		text.setY(environment.windowHeight/2.0);
		text.setFont(new Font(30));

		if (win) {
			text.setText("YOU WIN!: Final Score" + environment.getScore().getCurrentScore());
		}
		else {
			text.setText("GAME OVER... Final Score: " + environment.getScore().getCurrentScore());
		}
	}
	
	
	public Game(Group root, int windowWidth, int windowHeight) {
		environment = new Environment(root, windowWidth, windowHeight);
		checkCollision = new Collisions(environment);
		root.getChildren().add(text);
	}	
	
	
	public void startGame() {
		isRunning = true;
		environment.getBall().launchBall();
	}

	public void step() {
		if (!isRunning) {
			return;
		}
		environment.getBall().move(elapsedTime);
		checkCollision.checkAllCollisions();
		handleBallLost();
		
		if (environment.getBrickWall().getBrickWall().isEmpty()) {
			endGame(true);
		}
	}

	public boolean getIsRunning() {
		return isRunning;
	}
	
	public Environment getLevel() {
		return environment;
	}
}