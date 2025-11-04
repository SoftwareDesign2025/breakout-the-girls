// Environment Class
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
	private final int OBSTACLE_WIDTH = 160;
	private final int MAX_LIVES = 3;
	
	private TargetWall brickWall;
	private Paddle paddle;
	private Ball ball;
	private Score score;
	private ArrayList<PowerUp> powerUps;
	private Obstacle obstacle;
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
		
		initializeObjects();
	}
	
	public void initializeObjects () {
		paddle = new Paddle();
		ball = new Ball();
		brickWall = level.createBrickWall();
		obstacle = level.createObstacle();
		
		paddle.createController(window);
		ball.createProjectile(window);
		
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
	
	public void resetEnvironment() {
		root.getChildren().clear();
		initializeObjects();
	}
	
	
	public int resetEnvironmentForNextLevel(Level nextLevel) {
        root.getChildren().clear();
        //ui.resetText();        
        int lives = MAX_LIVES;
        level = nextLevel;
        initializeObjects();
        ui.clearText(); 
        
        return lives;
	}
	
	public void checkAllCollisions() {
		ball.outOfBoundsCollision(window);
		collisions.ballPaddleCollision(paddle,ball);
		collisions.ballTargetCollision(root, brickWall.getWall(), ball, level, powerUps, score);
		collisions.ballObstacleCollision(obstacle, ball);
		collisions.paddlePowerUpCollision(this, powerUps, paddle, root, window.getWindowHeight());
	}
	
	public void launchProjectile() {
	    ball.launchProjectile();
	}

	public void moveProjectile(double elapsedTime) {
	    ball.move(elapsedTime);
	}

	public boolean handleLifeLost() {
	    return ball.checkIfRoundLost();
	}

	public void resetBallPosition() {
	    ball.resetBallPosition(window);
	}
	
	public boolean isWallEmpty() {
	    return brickWall.getWall().isEmpty();
	}
	
	public int increaseLives() {
		lives += 1;
		return lives;
	}
	
	public UserControl getController() {
		return paddle;
	}
	
	public TargetWall getWall() {
		return brickWall;
	}
	
	public void triggerBugDrop() {};
}