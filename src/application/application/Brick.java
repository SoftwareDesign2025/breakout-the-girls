// Anna Rakes
// Create an individual brick object with its point value and determine if it was hit or not.
package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick {

	private final int BRICK_HEIGHT = 30;
	private final int BRICK_WIDTH = 100;
	private final int POWERUP_OCCURRENCES = 5;
	private final int MAX_BRICK_POINT_VALUE = 25;

	// Fields
	private Rectangle brick;
	private Paint brickColor;
	
	// Location of brick on screen:
	private double xCoordinate;
	private double yCoordinate;
	private int brickPointValue;
	
	// Whether the brick has been hit by the ball or not:
	private boolean isDestroyed;
	
    private Random random = new Random();
    
    public Brick() {}

	/* Constructor: Takes in parameters of brick location and color.
	 * Also assigns it a random point value between 1 and 25.
	 */
	public Brick(Paint brickColor, double xCoordinate, double yCoordinate) {
		this.brickColor = brickColor;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		brickPointValue = random.nextInt(MAX_BRICK_POINT_VALUE) + 1;
		isDestroyed = false;
	}
	
	
	// Creates the brick, adds a black border, and sets the location of the brick on screen.
	public void createBrick() {
		brick = new Rectangle(BRICK_WIDTH, BRICK_HEIGHT);				
		brick.setFill(brickColor);
		brick.setStroke(Color.BLACK);
		
		brick.setX(xCoordinate);
		brick.setY(yCoordinate);
	}
	
	// Return the brick object which is a Rectangle. 
	public Rectangle getBrick() {
		return brick;
	}
	
	/* Destroys the brick by making it transparent
	 * and now sets isDestroyed to true.
	 */
	public void destroyBrick () {
//		Shape intersection = Shape.intersect(brick, ball);
//		if (intersection.getBoundsInLocal().getWidth() != -1) {
		brick.setFill(Color.TRANSPARENT);
		brick.setStroke(Color.TRANSPARENT);
		isDestroyed = true;
	}
	
	// Gets the point value assigned to the brick if it was destroyed and returns it.
	public int getBrickPoint () {
		if (isDestroyed) {
			return brickPointValue;
		}
		else {
			return 0;
		}
	}
	
	// Checks to see if the brick was destroyed, if so, it returns true.
	public boolean checkIfDestroyed () {
		return isDestroyed;
	}
	
	// Returns the width of the brick.
	public int getBrickWidth() {
		return BRICK_WIDTH;
	}
	
	// Returns the height of the brick.
	public int getBrickHeight() {
		return BRICK_HEIGHT;
	}
	
	 //Determine what brick point values will spawn the power up and activate the power up.
	public PowerUp spawnPowerUp (int numberOfLives) {
		ArrayList<Integer> powerUpValues = new ArrayList<>();
	    for (int i = POWERUP_OCCURRENCES; i < MAX_BRICK_POINT_VALUE; i += POWERUP_OCCURRENCES) {
	        powerUpValues.add(i);
	    }

	    if (powerUpValues.contains(brickPointValue)) {
	        return new ExtraLife(xCoordinate + BRICK_WIDTH / 2, yCoordinate + BRICK_HEIGHT / 2);
	    }

	    return null; // no power-up for this brick
	}

	
}
