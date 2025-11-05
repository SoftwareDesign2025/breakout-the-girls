//  Author: Anna Rakes
// Sets the scene for the Breakout game.
//
package application;

import java.util.ArrayList;

import javafx.scene.Group;

//
//import java.util.ArrayList;
//
//import javafx.scene.Group;
//
public class BreakoutEnvironment extends Environment implements GameEnvironment {
	
	// Constants
	private final int OBSTACLE_WIDTH = 160;
	private final int MAX_LIVES = 3;
	
	// Game Objects
	private TargetWall brickWall;
	private Paddle paddle;
	private Ball ball;
	private Score score;
	private ArrayList<PowerUp> powerUps;
	private Obstacle obstacle;
	
	// Game components
	private Group root;
	private GameScreen ui;
	private Level level;
	private WindowDimensions window;
	private Collisions collisions;
	private int lives = 3;

	
	public BreakoutEnvironment(Level level, Group root, GameScreen ui, Score score, WindowDimensions window) {
		this.root = root;
		this.window = window;
		this.level = level;
		this.powerUps = new ArrayList<>();
		this.ui = ui;
		this.collisions = new Collisions();
		this.score = score;
		
		setUpGameObjects();
		addGamePiecesToScene();
	}
	
	/**
	 * method setUpGameObjects
	 * Initializes game pieces and creates them. 
	 */
	public void setUpGameObjects () {
		paddle = new Paddle();
		ball = new Ball();
		brickWall = level.createBrickWall();
		obstacle = level.createObstacle();
		paddle.createController(window);
		ball.createProjectile(window);
	}
	
	/**
	 * method addGamePiecesToScene
	 * Once objects have been created, add all of them to the scene so they are
	 * visible when playing game. 
	 */
	private void addGamePiecesToScene() {
		if (obstacle != null) {
			obstacle.createObstacle(window, OBSTACLE_WIDTH);
			root.getChildren().add(obstacle.getObstacle());
		}
		root.getChildren().add(paddle.getController());
		root.getChildren().add(ball.getProjectile());
		for (Target brick : brickWall.getWall()) {
			root.getChildren().add(brick.getTarget());
		}
	}
	
	/**
	 * method resetEnvironment
	 * Resets the scene to its original state by first clearing everything from the scene
	 * and then recreating and re-adding game pieces to it.
	 */
	public void resetEnvironment() {
		root.getChildren().clear();
		setUpGameObjects();
		addGamePiecesToScene();
	}
	
	
	/**
	 * method resetEnvironmentForNextLevel
	 * @param Level
	 * First clears all objects from scene, resets the lives for the next level,
	 * and then transitions to the next level.
	 * Then re-adds game pieces to the scene and clears the UI text. 
	 * @return the number of lives for the next level.
	 */
	public int resetEnvironmentForNextLevel(Level nextLevel) {
        root.getChildren().clear();
        this.lives = MAX_LIVES;
        level = nextLevel;
        setUpGameObjects();
        addGamePiecesToScene();
        ui.clearText(); 
        
        return lives;
	}
	
	/**
	 * method checkAllCollisions
	 * Checks all the possible collision elements in the game and does the method's respective
	 * action to a collision.
	 * Collisions include: ball out of screen, the ball and paddle, the ball and the brick,
	 * the ball and the obstacle, and the paddle and a powerup.
	 */
	public void checkAllCollisions() {
		ball.outOfBoundsCollision(window);
		collisions.ballPaddleCollision(paddle,ball);
		collisions.ballTargetCollision(root, brickWall.getWall(), ball, level, powerUps, score);
		collisions.ballObstacleCollision(obstacle, ball);
		collisions.paddlePowerUpCollision(this, powerUps, paddle, root, window.getWindowHeight());
	}
	
	
	/**
	 * method launchProjectile
	 * Transitions the ball from a static state to moving upwards. 
	 */
	public void launchProjectile() {
	    ball.launchProjectile();
	}
	
	
	// johnathan -- updated because Game had separate lives variable which caused things to be out of sync
	/**
	 * method handleLifeLost
	 * If a life is lost, first determine if that was the last life and the round has been lost.
	 * If so, decrement the lives and return true. Otherwise, do nothing.
	 */
	public boolean handleLifeLost() {
	    if (ball.checkIfRoundLost()) {
	        lives--;  
	        return true;
	    }
	    return false;
	}

	
	/**
	 * method resetBallPosition
	 * reset the ball to its original, static, position.
	 */
	public void resetBallPosition() {
	    ball.resetBallPosition(window);
	}
	
	/**
	 * method isWallEmpty
	 * returns true if the wall is empty.
	 */
	public boolean isWallEmpty() {
	    return brickWall.getWall().isEmpty();
	}
	
	/**
	 * method increaseLives
	 * increments lives by one and returns update lives value. 
	 */
	public int increaseLives() {
		lives += 1;
		return lives;
	}
	
	
	/**
	 * method getController
	 * @return paddle object
	 */
	public UserControl getController() {
		return paddle;
	}
	
	/**
	 * method getWall
	 * @return the TargetWall object
	 */
	public TargetWall getWall() {
		return brickWall;
	}
	
	/**
	 * method moveProjectiles
	 * Continuously updates ball position as it is moving.
	 */
	public void moveProjectiles(double elapsedTime) {
	    ball.move(elapsedTime);
	}
	
	
	// johnathan -- updated because Game had separate lives variable which caused things to be out of sync
	/**
	 * method getLives
	 * return current number of lives
	 */
	public int getLives() {
	    return lives;
	}
}