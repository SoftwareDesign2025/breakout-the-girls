package application;

import java.util.ArrayList;

import javafx.scene.Group;

public class Environment {
	
	protected BrickWall brickWall;
	protected Paddle paddle;
	protected Ball ball;
	protected Score score = new Score(); 
	protected ArrayList<PowerUp> powerUps = new ArrayList<>();
	protected int windowWidth;
	protected int windowHeight;
	protected Group root;
	
	
	public Environment(Group root, int windowWidth, int windowHeight) {
		this.root = root;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		
		brickWall = new BrickWall(windowWidth, windowHeight);
		paddle = new Paddle();
		ball = new Ball();
		score = new Score();
		powerUps = new ArrayList<>();
		
		initializeObjects();
	}
	
	private void initializeObjects () {
		paddle.createPaddle(windowWidth, windowHeight);
		ball.createBall(windowWidth, windowHeight);
		brickWall.createBrickWall();
		
		root.getChildren().add(paddle.getPaddle());
		root.getChildren().add(ball.getBall());
		for (Brick brick : brickWall.getBrickWall()) {
			root.getChildren().add(brick.getBrick());
		}
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

}
