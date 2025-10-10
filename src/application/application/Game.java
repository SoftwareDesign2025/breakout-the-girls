package application;
import java.awt.Color;
import java.util.ArrayList;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
	private final int windowHeight = 600;
	private final int windowWidth = 800;
	private Paddle paddle = new Paddle();
	private Ball ball = new Ball(); 
	//private ArrayList<Brick> brickList = new ArrayList<>(); 
	private BrickWall brickWall = new BrickWall(windowWidth, windowHeight);
	private Score score = new Score(); 
	private int lives = 3;
	private boolean isRunning = false; 
	// elapsedTime delta, should it be changed?
	private double elapsedTime = 1.0 / 60.0;
	private Group root;
	private ArrayList<PowerUp> powerUpList = new ArrayList<>();
	private Text text = new Text();

	
	
//	private Group getRoot() {
//		return this.root;
//	}
	
	private void handleBallLost() {
	    ball.outOfBoundsCollision(windowWidth, windowHeight);
	    if (ball.checkIfRoundLost()) {
	        lives--;
	        if (lives > 0) {
	            ball.resetBallPosition(windowWidth, windowHeight);
	            isRunning = false; // Wait for SPACE to relaunch
	        } else {
	            endGame(false);
	        }
	    }
	}
	    
//	    // Reset ball for next attempt
//	    this.root.getChildren().remove(this.ball.getBall());
//	    this.ball.createBall(this.windowWidth, this.windowHeight);
//	    this.root.getChildren().add(this.ball.getBall());
	
	public Game(Group root) {
	    this.root = root;
	    
	    paddle.createPaddle(windowWidth, windowHeight);
	    ball.createBall(windowWidth, windowHeight);
	    brickWall.createBrickWall();
	}
	
	public void startGame() {
		isRunning = true;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public BrickWall getBrickWall() {
		return brickWall;
	}
	
	
	public void step() {
		
		if (!isRunning) {
			return;
		}
		ball.move(elapsedTime);
		handleBallLost();
		checkCollisions();

		if (brickWall.getBrickWall().isEmpty()) {
			endGame(true);
		}

		for (int i = powerUpList.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUpList.get(i);

			if (powerUp.isActive()) {
				// Make the power-up fall
				powerUp.fall();

				// Check if paddle catches it
				if (powerUp.getVisualNode().getBoundsInParent()
						.intersects(paddle.getPaddle().getBoundsInParent())) {

					lives = powerUp.activatePowerUp(lives); // Increment lives
					root.getChildren().remove(powerUp.getVisualNode());
					powerUpList.remove(i);
				}
				// Remove if it falls off the bottom of the screen
				else if (powerUp.getY() > windowHeight) {
					powerUp.setActive(false);
					root.getChildren().remove(powerUp.getVisualNode());
					powerUpList.remove(i);
				}
			}
		

	}
	    
	    
//	    // make power ups fall 
//	    for (int i = powerUpList.size() - 1; i >= 0; i--) {
//	        PowerUp powerUp = powerUpList.get(i);
//	        if (powerUp.isActive()) {
//	            powerUp.fall();
//	            
//	            // Check if power-up caught by paddle
//	            if (powerUp.getVisualNode().getBoundsInParent()
//	                    .intersects(paddle.getPaddle().getBoundsInParent())) {
//	                lives = powerUp.activatePowerUp(lives);
//	                root.getChildren().remove(powerUp.getVisualNode());
//	                powerUpList.remove(i);
//	            }
//	            // Remove if fell off screen
//	            else if (powerUp.getY() > windowHeight) {
//	                powerUp.setActive(false);
//	                root.getChildren().remove(powerUp.getVisualNode());
//	                powerUpList.remove(i);
	    
	   
	}
	
	
	//public void checkBallCollisionWithBrick() {}
	//public void checkBallCollisionWithPaddle() {}
	//public void checkBallCollisionWithPowerUp() {}	
	public void checkCollisions() { 
		Circle ballShape = this.ball.getBall();
		Rectangle paddleShape = this.paddle.getPaddle();


		ArrayList<Brick> bricks = brickWall.getBrickWall();
		for (int i = bricks.size() - 1; i >= 0; i--) {
			Brick brick = bricks.get(i);
			Shape intersection = Shape.intersect(ballShape, brick.getBrick());
			if (intersection.getBoundsInLocal().getWidth() != -1) {
				ball.collisionWithBrickOrPaddle();
				
				PowerUp powerUp = brick.spawnPowerUp(lives);
			    if (powerUp != null) {
			        powerUpList.add(powerUp);
			        root.getChildren().add(powerUp.getVisualNode());
			    }
			    
				brick.destroyBrick();
				score.addPoints(brick.getBrickPoint());

				bricks.remove(i);
			}
		}


		Shape ballPaddleIntersection = Shape.intersect(ballShape, paddleShape);
		if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
			ball.collisionWithBrickOrPaddle();
		}

	}
	    
//	    for (int i = brickWall.getBrickWall().size() - 1; i >= 0; i--) {
//	    	Brick brick = brickWall.getBrickWall().get(i);
//	    	 //review
//		    if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
//		        brick.destroyBrick();
//		    }
//	    	
//	    	if (brick.getBrickPoint() > 0) {
//	    		//ask anna
//	    		//then call this
//	    		this.ball.collisionWithBrickOrPaddle();
//	    		score.addPoints(brick.getBrickPoint());
//	    		brick.spawnPowerUp(i);
//	    		brick.destroyBrick();
//	    		brickWall.getBrickWall().remove(i);
//	    	}
	    	
	
	public void resetBall() {
		this.ball = null;
	}
	
	public void resetPaddle() {
		this.paddle = null;
	}
	
	public void endGame(boolean win) {
		this.isRunning = false;
		
		if (win) {
			text.setX(windowWidth/2.0);
			text.setY(windowHeight/2.0);
			text.setFont(new Font(30));
			text.setText("YOU WIN!: Final Score" + score.getCurrentScore());
		}
		else {
			text.setX(windowWidth/2.0);
			text.setY(windowHeight/2.0);
			text.setFont(new Font(30));
			text.setText("GAME OVER... Final Score: " + score.getCurrentScore());
//	        System.out.println("High score: " + score.getHighScore());
		}
	}
	
	// is this func necessary considering Ball already checks if it is in bounds?
	public void checkBallOutOfBounds() {
		this.ball.outOfBoundsCollision(this.windowHeight, this.windowWidth);
		if (this.ball.checkIfRoundLost()) {
			endGame(false);
			ball.launchBall();
		}
	}
	
	public boolean getIsRunning() {
		return isRunning;
	}
	
	public Text getText() {
		return text;
	}
}
