package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Target {

	protected int targetHeight;
	protected int targetWidth;
	protected final int MAX_TARGET_POINT_VALUE = 25;

	// Location of target on screen:
	protected double xCoordinate;
	protected double yCoordinate;
	protected int targetPointValue;
	protected Rectangle target;

	private Random random = new Random();

	public Target(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		targetPointValue = random.nextInt(MAX_TARGET_POINT_VALUE) + 1;
	}
	
	public Target() {}

	public abstract void createTarget(int brickHeight, int brickWidth);
	
	public Rectangle getTarget() {
		return target;
	}

	// Destroys the brick by making it transparent.
	public void destroyTarget () {
		target.setFill(Color.TRANSPARENT);
		target.setStroke(Color.TRANSPARENT);
	}

	// Gets the point value assigned to the brick if it was destroyed and returns it.
	public int getTargetPoint () {
		return targetPointValue;
	}


	// Returns the width of the brick.
	public int getTargetWidth() {
		return targetWidth;
	}

	// Returns the height of the brick.
	public int getTargetHeight() {
		return targetHeight;
	}

	public double getX() { 
		return xCoordinate;
	}

	public double getY() { 
		return yCoordinate; 
	}
}
