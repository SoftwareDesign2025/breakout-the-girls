package application;
import java.util.ArrayList;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Game {
	private int windowHeight = 800;
	private int windowWidth = 600;
	private Paddle paddle = new Paddle();
	private Ball ball = new Ball(); 
	private ArrayList<Brick> brickList = new ArrayList<>(); 
	private ArrayList<PowerUp> powerUpList = new ArrayList<>();
	private BrickWall brickWall = new BrickWall(800, 600);
	private Score score = new Score(); 
	private int lives = 3;
	private boolean isRunning = false; 
	// elapsedTime delta, should it be changed?
	private double elapsedTime = 1.0 / 60.0;
	private Group root;
	
	
	private Group getRoot() {
		return this.root;
	}
	
	private void handleBallLost() {
	    lives--;
	    if (lives <= 0) {
	        endGame(false);
	        return;
	    }
	    
	    // Reset ball for next attempt
	    this.root.getChildren().remove(this.ball.getBall());
	    this.ball.createBall(this.windowWidth, this.windowHeight);
	    this.root.getChildren().add(this.ball.getBall());
	}

	
	public Game(Group root) {
		this.root = root;
	}
	
	public void step() {
	    if (!isRunning) {
	        return;
	    }
	    
	    ball.move(elapsedTime);
	    ball.outOfBoundsCollision(windowWidth, windowHeight);
	    
	    if (ball.checkIfRoundLost()) {
	        handleBallLost();
	        if (lives <= 0) {
	            return;
	        }
	    }
	    
	    // make power ups fall 
	    for (int i = powerUpList.size() - 1; i >= 0; i--) {
	        PowerUp powerUp = powerUpList.get(i);
	        if (powerUp.isActive()) {
	            powerUp.fall();
	            
	            // Check if power-up caught by paddle
	            if (powerUp.getVisualNode().getBoundsInParent()
	                    .intersects(paddle.getPaddle().getBoundsInParent())) {
	                lives = powerUp.activatePowerUp(lives);
	                root.getChildren().remove(powerUp.getVisualNode());
	                powerUpList.remove(i);
	            }
	            // Remove if fell off screen
	            else if (powerUp.getY() > windowHeight) {
	                powerUp.setActive(false);
	                root.getChildren().remove(powerUp.getVisualNode());
	                powerUpList.remove(i);
	            }
	        }
	    }
	    
	    checkCollisions();
	    if (brickList.isEmpty()) {
	        endGame(true);
	    }
	}
	
	
	//public void checkBallCollisionWithBrick() {}
	//public void checkBallCollisionWithPaddle() {}
	//public void checkBallCollisionWithPowerUp() {}	
	public void checkCollisions() { 
		Circle ballShape = this.ball.getBall();
	    Rectangle paddleShape = this.paddle.getPaddle();
	    Shape ballPaddleIntersection = Shape.intersect(ballShape, paddleShape);
	    Group root = this.getRoot();

	   
	    
	    for (int i = brickList.size() - 1; i >= 0; i--) {
	    	Brick brick = brickList.get(i);
	    	 //review
		    if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
		        brick.destroyBrick();
		    }
	    	
	    	if (brick.getBrickPoint() > 0) {
	    		//ask anna
	    		ballShape.setCenterY(paddleShape.getY() - ballShape.getRadius());
	    		//then call this
	    		this.ball.collisionWithBrickOrPaddle();
	    		score.addPoints(brick.getBrickPoint());
	    		brick.spawnPowerUp(i);
	    		brick.destroyBrick();
	    		brickList.remove(i);
	    	}
	    	
	    }	
	}
	
	public void resetBall() {
		this.ball = null;
	}
	
	public void resetPaddle() {
		this.paddle = null;
	}
	
	public void endGame(boolean win) {
		this.isRunning = false;
		
		if (win) {
			System.out.println("YOU WIN!");
	        System.out.println("Final score: " + score.getCurrentScore());
		}
		else {
			System.out.println("GAME OVER...");
	        System.out.println("Final score: " + score.getCurrentScore());
	        System.out.println("High score: " + score.getHighScore());
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
}
