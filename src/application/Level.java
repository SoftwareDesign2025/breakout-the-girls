package application;

import javafx.scene.Group;

public abstract class Level {
	
	protected BrickWall brickWall;
	protected Paddle paddle;
	protected Ball ball;
	protected int windowWidth;
	protected int windowHeight;

	
	public Level(int windowWidth, int windowHeight) {
		brickWall = new BrickWall(windowWidth, windowHeight);
		paddle = new Paddle();
		ball = new Ball();
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		paddle.createPaddle(windowWidth, windowHeight);
		ball.createBall(windowWidth, windowHeight);
	}
	
	public abstract void alterBrickWall ();

}
