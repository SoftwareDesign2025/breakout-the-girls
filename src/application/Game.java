import java.util.ArrayList;

import com.sun.javafx.geom.Shape;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Game {
	private int windowHeight = 800;
	private int windowWidth = 600;
	private Paddle paddle = new Paddle();
	private Ball ball = new Ball(); 
	private ArrayList<Brick> brickList = new ArrayList<>(); 
	private ArrayList<PowerUp> powerUpList = new ArrayList<>();
	private BrickWall brickWall = new BrickWall(800, 600);
	private Score score = new Score(); 
	private int lives = 0;
	private boolean isRunning = false; 
	// elapsedTime delta, should it be changed?
	private double elapsedTime = 1.0 / 60.0;
	private Group root;
	
	
	private Group getRoot() {
		return this.root;
	}
	
	public Game(Group root) {
		this.root = root;
	}
	
	public void step() {
		/**
		 * TODO: ref JavaFX lab had step function on AnimationController.java
		 * should be remarkably similar if not same implementation except for different object names
		 * 
		 * questions for team:
		 * 	=> do we need a central run function that initializes any objects, i.e. balls
		 * => when is the game over? 
		 * => can we move windowWidth and windowHeight setting logic in Game to centralize control of it to and have it set in 1 file?
		 * => when ball hits paddle, it bounces off paddle? (see below in collisions func)
		 * 
		 * for sure do:
		 * 
		 * => if isRunning is false --> return out of the func
		 * => check endGame() to see if game has been won
		 * => move balls 
		 * => move paddles 
		 * 
		 * else:
		 * => manage BrickWalls
		 * => track all PowerUps currently being used (ArrayList)
		 * =>
		 */
	}
	
	public void checkCollisions() { 
		/**
		 * Ball 	=> checkCollision()
		 * Brick	=> checkCollision()
		 * 			=> call in step() func
		 */
		Circle ballShape = this.ball.getBall();
	    Rectangle paddleShape = this.paddle.getPaddle();
	    Shape ballPaddleIntersection = Shape.intersect(ballShape, paddleShape);
	    Group root = this.getRoot();
	    
	    if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
	        // Ball hit paddle and then do what?
	    }
	    
	    for (int i = brickList.size() - 1; i >= 0; i--) {
	    	Brick brick = brickList.get(i);
	    	brick.checkCollision(ballShape);
	    	
	    	if (brick.getBrickPoint() > 0) {
	    		// needs logic for bouncing off whatever it hits which currently doesnt exist hre
	    		// bare minimum it needs to bounce off the paddle
	    		score.addPoints(brick.getBrickPoint());
	    		root.getChildren().remove(brick.getBrick());
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
		boolean outOfBounds = this.ball.keepBallWithinBounds(this.windowHeight, this.windowWidth);
		
		if (outOfBounds) {
			// logic logic logic
		}
	}
}
