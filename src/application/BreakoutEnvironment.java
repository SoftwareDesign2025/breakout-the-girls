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
public class BreakoutEnvironment {
	private TargetWall brickWall;
	private Paddle paddle;
	private Ball ball;
	private Score score;
	private ArrayList<PowerUp> powerUps;
	private Obstacle obstacle;
	private Group root;
	private GameScreen ui;
	private Level level;
	private int windowWidth;
	private int windowHeight;
	private Collisions collisions;
	private int lives = 3;

	
	public BreakoutEnvironment(Level level, Group root, GameScreen ui, int windowWidth, int windowHeight, Score score) {
		this.root = root;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.level = level;
		this.score = new Score();
		this.powerUps = new ArrayList<>();
		this.ui = new GameScreen(root, windowWidth, windowHeight);
		this.collisions = new Collisions();
		this.score = score;
		
		initializeObjects();
	}
	
	private void initializeObjects () {
		paddle = new Paddle();
		ball = new Ball();
		brickWall = level.createBrickWall();
		obstacle = level.createObstacle();
		
		paddle.createPaddle(windowWidth, windowHeight);
		ball.createProjectile(windowWidth, windowHeight);
		
		if (obstacle != null) {
			obstacle.createObstacle(windowWidth, windowHeight, 160);
			root.getChildren().add(obstacle.getObstacle());
		}
		root.getChildren().add(paddle.getPaddle());
		root.getChildren().add(ball.getProjectile());
		for (Target brick : brickWall.getBrickWall()) {
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
        int lives = 3;
        level = nextLevel;
        initializeObjects();
        ui.clearText(); 
        
        return lives;
	}
	
	public void checkAllCollisions() {
		ball.outOfBoundsCollision(windowWidth, windowHeight);
		collisions.ballPaddleCollision(paddle,ball);
		collisions.ballTargetCollision(root, brickWall.getBrickWall(), ball, level, powerUps, score);
		collisions.ballObstacleCollision(obstacle, ball);
		collisions.paddlePowerUpCollision(this, powerUps, paddle, root, windowHeight);
	}
	
	public void launchProjectile() {
	    ball.launchProjectile();
	}

	public void moveProjectile(double elapsedTime) {
	    ball.move(elapsedTime);
	}

	public boolean isBallLost() {
	    return ball.checkIfRoundLost();
	}

	public void resetBallPosition() {
	    ball.resetBallPosition(windowWidth, windowHeight);
	}
	
	public boolean isWallEmpty() {
	    return brickWall.getBrickWall().isEmpty();
	}
	
	public int increaseLives() {
		lives += 1;
		return lives;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public TargetWall getWall() {
		return brickWall;
	}
}