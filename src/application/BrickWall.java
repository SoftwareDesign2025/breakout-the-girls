// Anna Rakes
// Store collection of bricks and position them into a wall.
package application;

import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class BrickWall {
	
	// Fields
	private ArrayList<Brick> brickWall = new ArrayList<>();
    private Random random = new Random();
    
    // List of colors to pick from for the brick color:
	private Paint[] colorList = {Color.LIGHTCORAL, Color.LAVENDERBLUSH, Color.LIGHTSTEELBLUE, Color.MOCCASIN, 
			Color.LIGHTGOLDENRODYELLOW,Color.DARKSEAGREEN};
	private int windowWidth;
	private int windowHeight;
	private int numberOfColumns;
	private int numberOfRows;
	private Brick emptyBrick = new Brick();

	
	/* Constructor
	 * Determines number of columns of bricks by dividing the width of the window 
	 * by the width of an individual brick.
	 * Determine the number of rows of bricks to fill up the top third of the window.
	 * Divides the size of the top third of the window by the brick height.
	 */
	public BrickWall(int windowWidth, int windowHeight) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		numberOfColumns = this.windowWidth/emptyBrick.getBrickWidth();
		numberOfRows = (this.windowHeight/3) / emptyBrick.getBrickHeight();
	}

	
	/* Create a wall of bricks
	 * Fills out the rows and columns of bricks
	 * Determines the position of the brick by the brick size and number of rows and columns.
	 * Then creates a brick with the next position and random color.
	 */
	public void createBrickWall() {
		for (int i = 0; i<numberOfRows ; i++) {
			for (int j = 0; j<numberOfColumns; j++) {
				double xCoordinate = j*emptyBrick.getBrickWidth();
				double yCoordinate = i*emptyBrick.getBrickHeight();
				Brick brick = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
				brick.createBrick();
				brickWall.add(brick);
			}
		}
	}
	
	private Paint generateRandomBrickColor () {
		Paint brickColor = colorList[random.nextInt(6)];
		return brickColor;
	}
	
	public ArrayList<Brick> getBrickWall () {
		return brickWall;
	}

}
