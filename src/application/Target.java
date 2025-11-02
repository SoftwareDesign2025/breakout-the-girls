package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Target {

	protected double targetHeight;
	protected double targetWidth;
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

	public abstract void createTarget(double brickHeight, double brickWidth);
	
	public Shape getTarget() {
		return target;
	}

	// Destroys the brick by making it transparent.
	public void destroyTarget () {
		target.setFill(Color.TRANSPARENT);
		target.setStroke(Color.TRANSPARENT);
	}
	
	public void moveBugDown() {
		yCoordinate += 15;
		target.setY(yCoordinate);
	}


	// Gets the point value assigned to the brick if it was destroyed and returns it.
	public int getTargetPoint () {
		return targetPointValue;
	}


	// Returns the width of the brick.
	public double getTargetWidth() {
		return targetWidth;
	}

	// Returns the height of the brick.
	public double getTargetHeight() {
		return targetHeight;
	}

	public double getX() { 
		return xCoordinate;
	}

	public double getY() { 
		return yCoordinate; 
	}
	
	public void drop() {}
	
	public void move(double elapsedTime) {}
	
	public abstract boolean bugOutOfBounds (int windowHeight);
}
