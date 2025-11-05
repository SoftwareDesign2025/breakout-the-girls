// Author: Anna Rakes
// Create an individual Bug which is a Target

package application;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Bug extends Target {
	
	// Objects
	private Point2D bugVelocity;
	
	// Constants
	private final double SPEED = 100;

	// Create Bug object based on super.
	public Bug (double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}
	
	// Empty Constructor
	public Bug() {}
	
	
	/**
	 * method createTarget
	 * Creates the bug base on the super specifications, but colors it Blue violet. 
	 */
	@Override
	public void createTarget(double bugHeight, double bugWidth) {
		super.createTarget(bugHeight, bugWidth);
		Image image = new Image(getClass().getResource("bug.png").toExternalForm());
		target.setFill(new ImagePattern(image));
		target.setStroke(null);
	}
	
	/**
	 * method startFalling
	 * Starts the movement of the bug downward.
	 */
	public void startFalling() {
	    bugVelocity = new Point2D(0, SPEED);
	}
	
	/**
     * method updatePosition
     * Move the ball according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void updatePosition (double elapsedTime) {
        target.setX(target.getX() + bugVelocity.getX() * elapsedTime);
        target.setY(target.getY() + bugVelocity.getY() * elapsedTime);
    }
    
    /**
     * method bugOutOfBounds
     * checks to see if the bug fell off the bottom of the screen
     * @return true if the bug is out of bounds.
     */
    public boolean bugOutOfBounds(int windowHeight) {
    		return (target.getY() > windowHeight);
    }

}
