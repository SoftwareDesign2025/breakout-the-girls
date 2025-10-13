package application;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
//	private final int windowHeight = 600;
//	private final int windowWidth = 800;
//	private Paddle paddle = new Paddle();
//	private Ball ball = new Ball(); 
//	private BrickWall brickWall = new BrickWall(windowWidth, windowHeight);
//	private Score score = new Score(); 
//	private int lives = 3;
//	private boolean isRunning = false; 
//	// elapsedTime delta, should it be changed?
//	private double elapsedTime = 1.0 / 60.0;
//	private Group root;
//	private ArrayList<PowerUp> powerUpList = new ArrayList<>();
//	private Text text = new Text();
	private Environment environment;
	private Collisions checkCollision;
	private double elapsedTime = 1.0 / 60.0;
	private boolean isRunning = false;
	private int lives = 3;
	private Text text = new Text();
	
	

	

	
	
//	private void handleBallLost() {
//	    ball.outOfBoundsCollision(windowWidth, windowHeight);
//	    if (ball.checkIfRoundLost()) {
//	        lives--;
//	        if (lives > 0) {
//	            ball.resetBallPosition(windowWidth, windowHeight);
//	            isRunning = false; // Wait for SPACE to relaunch
//	        } else {
//	            endGame(false);
//	        }
//	    }
//	}
//	    
//	
	public Game(Group root, int windowWidth, int windowHeight) {
		environment = new Environment(root, windowWidth, windowHeight);
		checkCollision = new Collisions(environment);
		root.getChildren().add(text);
	}
//	public Game(Group root) {
//	    this.root = root;
//	    
//	    paddle.createPaddle(windowWidth, windowHeight);
//	    ball.createBall(windowWidth, windowHeight);
//	    brickWall.createBrickWall();
//	}
//	
	public void startGame() {
		isRunning = true;
		environment.getBall().launchBall();
	}
//	
//	public Paddle getPaddle() {
//		return paddle;
//	}
//	
//	public Ball getBall() {
//		return ball;
//	}
//	
//	public BrickWall getBrickWall() {
//		return brickWall;
//	}
//	
//	public ArrayList<PowerUp> getPowerUp() {
//		return powerUpList;
//	}
//	
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
	
	
//	public void step() {
//		
//		if (!isRunning) {
//			return;
//		}
//		ball.move(elapsedTime);
//		handleBallLost();
//		checkCollisions();
//
//		if (brickWall.getBrickWall().isEmpty()) {
//			endGame(true);
//		}
//
//		for (int i = powerUpList.size() - 1; i >= 0; i--) {
//			PowerUp powerUp = powerUpList.get(i);
//
//			if (powerUp.isActive()) {
//				// Make the power-up fall
//				powerUp.fall();
//
//				// Check if paddle catches it
//				if (powerUp.getVisualNode().getBoundsInParent()
//						.intersects(paddle.getPaddle().getBoundsInParent())) {
//
//					lives = powerUp.activatePowerUp(lives); // Increment lives
//					root.getChildren().remove(powerUp.getVisualNode());
//					powerUpList.remove(i);
//				}
//				// Remove if it falls off the bottom of the screen
//				else if (powerUp.getY() > windowHeight) {
//					powerUp.setActive(false);
//					root.getChildren().remove(powerUp.getVisualNode());
//					powerUpList.remove(i);
//				}
//			}
//
//
//		}
//	    
//	    
//	   
//	}
//	
//	
//	//public void checkBallCollisionWithBrick() {}
//	//public void checkBallCollisionWithPaddle() {}
//	//public void checkBallCollisionWithPowerUp() {}	
//	public void checkCollisions() { 
//		Circle ballShape = this.ball.getBall();
//		Rectangle paddleShape = this.paddle.getPaddle();
//
//
//		ArrayList<Brick> bricks = brickWall.getBrickWall();
//		for (int i = bricks.size() - 1; i >= 0; i--) {
//			Brick brick = bricks.get(i);
//			Shape intersection = Shape.intersect(ballShape, brick.getBrick());
//			if (intersection.getBoundsInLocal().getWidth() != -1) {
//				ball.changeBallVelocity();
//				
//				PowerUp powerUp = brick.spawnExtraLife(lives);
//			    if (powerUp != null) {
//			        powerUpList.add(powerUp);
//			        root.getChildren().add(powerUp.getVisualNode());
//			    }
//			    
//				brick.destroyBrick();
//				score.addPoints(brick.getBrickPoint());
//
//				bricks.remove(i);
//			}
//		}
//		Shape ballPaddleIntersection = Shape.intersect(ballShape, paddleShape);
//		if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
//			ball.changeBallVelocity();
//		}
//
//	}
//	
//	public PowerUp checkPowerUpSpawn() {
//		for (Brick brick : brickWall.getBrickWall()) {
//	        PowerUp p = brick.spawnExtraLife(lives);
//	        if (p != null) {
//	            powerUpList.add(p);
//	            return p;
//	        }
//	    }
//	    return null;
//	}
//	    	
//	
//	public void resetBall() {
//		this.ball = null;
//	}
//	
//	public void resetPaddle() {
//		this.paddle = null;
//	}
//	
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
//	
////	// is this func necessary considering Ball already checks if it is in bounds?
//	public void checkBallOutOfBounds() {
//		this.ball.outOfBoundsCollision(this.windowHeight, this.windowWidth);
//		if (this.ball.checkIfRoundLost()) {
//			endGame(false);
//			ball.launchBall();
//		}
//	}
//	
	public boolean getIsRunning() {
		return isRunning;
	}
	
	public Environment getLevel() {
		return environment;
	}
//	
//	public Text getText() {
//		return text;
//	}
}
