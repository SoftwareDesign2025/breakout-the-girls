// Author: Anna Rakes
// Create an individual brick object
package application;


import javafx.scene.paint.Paint;

public class Brick extends Target {

	// Field
	private Paint brickColor;

	/* Constructor: Takes in parameters of brick location and color.
	 * Calls its super constructor and assigns the brickColor field to given color. 
	 */
	public Brick(Paint brickColor, double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
		this.brickColor = brickColor;
	}
	
	// Empty Constructor
	public Brick() {}
	
	
	/**
	 * method CreateTarget
	 * Creates the brick based on super method, but sets the color of the brick to the defined
	 * brick color. 
	 */
	@Override
	public void createTarget(double brickHeight, double brickWidth) {	
		super.createTarget(brickHeight, brickWidth);
		target.setFill(brickColor);
	}
	
}
