// Author: Anna Rakes

package application;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Target {

	// Constants
	protected final int MAX_TARGET_POINT_VALUE = 25;
	protected final int DISTANCE_DOWN = 15;
	
	// Target dimensions
	protected double targetHeight;
	protected double targetWidth;

	// Location of target on screen:
	protected double xCoordinate;
	protected double yCoordinate;
	protected int targetPointValue;
	protected Rectangle target;

	private Random random = new Random();

	// Assigns a random point value to the target between 1 and 25.
	public Target(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		targetPointValue = random.nextInt(MAX_TARGET_POINT_VALUE) + 1;
	}
	
	// Default constructor
	public Target() {}

	/**
	 * method createTarget
	 * @param brickHeight
	 * @param brickWidth
	 * Creates a rectangle based on given dimensions and outlines it black.
	 */
	public void createTarget(double brickHeight, double brickWidth) {
		targetHeight = brickHeight;
		targetWidth = brickWidth;
		target = new Rectangle(brickWidth, brickHeight);				
		target.setStroke(Color.BLACK);
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}
	
	/**
	 * @return target in abstracted Shape object. 
	 */
	public Shape getTarget() {
		return target;
	}

	/**
	 * method destoryTarget
	 * Destroys the target by making it transparent.
	 */
	public void destroyTarget () {
		target.setFill(Color.TRANSPARENT);
		target.setStroke(Color.TRANSPARENT);
	}
	
	/**
	 * method moveBugDown
	 * Change the location of the target to 15 spaces down on screen.
	 */
	public void moveBugDown() {
		yCoordinate += DISTANCE_DOWN;
		target.setY(yCoordinate);
	}


	// Gets the point value assigned to the target.
	public int getTargetPoint () {
		return targetPointValue;
	}


	// Returns the width of the target.
	public double getTargetWidth() {
		return targetWidth;
	}

	// Returns the height of the target.
	public double getTargetHeight() {
		return targetHeight;
	}

	// Get x coordinate of target.
	public double getX() { 
		return xCoordinate;
	}

	// Get y coordinate of target.
	public double getY() { 
		return yCoordinate; 
	}
	
	// Methods below used in subclasses.
	public void startFalling() {}
	
	public void updatePosition(double elapsedTime) {}
	
	public boolean bugOutOfBounds (int windowHeight) {
		return false;
	}
}
