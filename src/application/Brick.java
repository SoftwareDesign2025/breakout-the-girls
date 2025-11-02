// Anna Rakes i
// Create an individual brick object with its point value and determine if it was hit or not.
package application;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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
	public void createTarget(double brickHeight, double brickWidth) {
		targetHeight = brickHeight;
		targetWidth = brickWidth;
		target = new Rectangle(brickWidth, brickHeight);				
		target.setFill(brickColor);
		target.setStroke(Color.BLACK);
		
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}

	@Override
	public boolean bugOutOfBounds(int windowHeight) {
		return false;
	}
	
	
}
