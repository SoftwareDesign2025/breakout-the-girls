// Anna Rakes

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick {

	private final int BRICK_HEIGHT = 30;
	private final int BRICK_WIDTH = 100;


	private Rectangle brick;
	private Paint brickColor;
	private double xCoordinate;
	private double yCoordinate;
	private int brickPointValue;
	private boolean isDestroyed;
    private Random random = new Random();
    //private ExtraLife extraLifePowerUp = new ExtraLife();
    
    public Brick() {}

	
	public Brick(Paint brickColor, double xCoordinate, double yCoordinate) {
		this.brickColor = brickColor;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		brickPointValue = random.nextInt(25) + 1;
		isDestroyed = false;
		//this.game = game;
	}
	
	
	public void createBrick() {
		brick = new Rectangle(BRICK_WIDTH, BRICK_HEIGHT);				
		brick.setFill(brickColor);
		brick.setStroke(Color.BLACK);
		
		brick.setX(xCoordinate);
		brick.setY(yCoordinate);
	}
	
	public Rectangle getBrick() {
		return brick;
	}
	
	public void destroyBrick () {
//		Shape intersection = Shape.intersect(brick, ball);
//		if (intersection.getBoundsInLocal().getWidth() != -1) {
		brick.setFill(Color.TRANSPARENT);
		brick.setStroke(Color.TRANSPARENT);
		isDestroyed = true;
	}
	
	public int getBrickPoint () {
		if (isDestroyed) {
			return brickPointValue;
		}
		else {
			return 0;
		}
	}
	
	public boolean checkIfDestroyed () {
		return isDestroyed;
	}
	
	public int getBrickWidth() {
		return BRICK_WIDTH;
	}
	
	public int getBrickHeight() {
		return BRICK_HEIGHT;
	}
	
//	public void spawnPowerUp () {
//		if (brickPointValue == (random.nextInt(25)+1)) {
//			extraLifePowerUp.activatePowerUp(brickPointValue);
//		}
//	}

	
}
