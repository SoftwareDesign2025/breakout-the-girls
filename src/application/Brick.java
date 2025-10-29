// Anna Rakes i
// Create an individual brick object with its point value and determine if it was hit or not.
package application;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Target {

//	private int brickHeight;
//	private int brickWidth;
//	private final int MAX_BRICK_POINT_VALUE = 25;

	// Fields
//	private Rectangle brick;
	private Paint brickColor;
	
	// Location of brick on screen:
//	private double xCoordinate;
//	private double yCoordinate;
//	private int brickPointValue;
	
	// Whether the brick has been hit by the ball or not:
//	private boolean isDestroyed;
	 

	/* Constructor: Takes in parameters of brick location and color.
	 * Also assigns it a random point value between 1 and 25.
	 */
	public Brick(Paint brickColor, double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
		this.brickColor = brickColor;
	}
	
	public Brick() {}
	
	
	// Creates the brick, adds a black border, and sets the location of the brick on screen.
	public void createTarget(int brickHeight, int brickWidth) {
		targetHeight = brickHeight;
		targetWidth = brickWidth;
		target = new Rectangle(brickWidth, brickHeight);				
		target.setFill(brickColor);
		target.setStroke(Color.BLACK);
		
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}
	
	
	/* Destroys the brick by making it transparent
	 * and now sets isDestroyed to true.
	 */
//	public void destroyBrick () {
//		brick.setFill(Color.TRANSPARENT);
//		brick.setStroke(Color.TRANSPARENT);
////		isDestroyed = true;
//	}
	
//	// Gets the point value assigned to the brick if it was destroyed and returns it.
//	public int getBrickPoint () {
////		if (isDestroyed) {
////			return brickPointValue;
////		}
////		else {
////			return 0;
////		}
//		return brickPointValue;
//	}
//	
////	// Checks to see if the brick was destroyed, if so, it returns true.
////	private boolean checkIfDestroyed () {
////		return isDestroyed;
////	}
//	
//	// Returns the width of the brick.
//	public int getBrickWidth() {
//		return brickWidth;
//	}
//	
//	// Returns the height of the brick.
//	public int getBrickHeight() {
//		return brickHeight;
//	}
//	
//	public double getX() { 
//		return xCoordinate;
//	}
//	
//    public double getY() { 
//    		return yCoordinate; 
//    	}


	
}
