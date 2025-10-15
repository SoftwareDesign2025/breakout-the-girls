// Environment Class

package application;

import java.util.ArrayList;

import javafx.scene.Group;

public class Environment {
	
	private BrickWall brickWall;
	private Paddle paddle;
	private Ball ball;
	private Score score;
	private ArrayList<PowerUp> powerUps;
	private int windowWidth;
	private int windowHeight;
	private Group root;
	private Obstacle obstacle;
	private Level level;
	
	
	public Environment(Group root, int windowWidth, int windowHeight, Level level) {
		this.root = root;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.level = level;
		
		paddle = new Paddle();
		ball = new Ball();
		score = new Score();
		powerUps = new ArrayList<>();
		brickWall = level.createBrickWall();
		obstacle = level.createObstacle();

		initializeObjects();
	}
	
	private void initializeObjects () {
		paddle.createPaddle(windowWidth, windowHeight);
		ball.createBall(windowWidth, windowHeight);
		
		if (obstacle != null) {
			obstacle.createObstacle(windowWidth, windowHeight, 160);
			root.getChildren().add(obstacle.getObstacle());
		}
		root.getChildren().add(paddle.getPaddle());
		root.getChildren().add(ball.getBall());
		for (Brick brick : brickWall.getBrickWall()) {
			root.getChildren().add(brick.getBrick());
		}
	}
	
	public void resetEnvironment() {
		root.getChildren().clear();
		initializeObjects();
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
	
	public Score getScore () {
		return score;
	}
	
	public Obstacle getObstacle() {
		return obstacle;
	}
	
	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}
	
	public int getWindowWidth() {
		return windowWidth;
	}
	
	public int getWindowHeight() {
		return windowHeight;
	}
	
	public Group getRoot() {
		return root;
	}
	
	public Level getLevel() {
		return level;
	}

}
