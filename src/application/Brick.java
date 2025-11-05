// Anna Rakes i
// Create an individual brick object with its point value and determine if it was hit or not.
package application;


import javafx.scene.paint.Paint;

public class Brick extends Target {


	// Fields
	private Paint brickColor;
	
	

	/* Constructor: Takes in parameters of brick location and color.
	 * Also assigns it a random point value between 1 and 25.
	 */
	public Brick(Paint brickColor, double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
		this.brickColor = brickColor;
	}
	
	public Brick() {}
	
	
	// Creates the brick, adds a black border, and sets the location of the brick on screen.
	@Override
	public void createTarget(double brickHeight, double brickWidth) {	
		super.createTarget(brickHeight, brickWidth);
		target.setFill(brickColor);
	}
	
}
