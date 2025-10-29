// Anna Rakes i
// Store collection of bricks and position them into a wall.
package application;

import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TargetWall {
	
	// Fields
	private ArrayList<Target> targetWall = new ArrayList<>();
    private Random random = new Random();
    
    // List of colors to pick from for the brick color:
	private Paint[] colorList = {Color.LIGHTCORAL, Color.LAVENDERBLUSH, Color.LIGHTSTEELBLUE, Color.MOCCASIN, 
			Color.LIGHTGOLDENRODYELLOW,Color.DARKSEAGREEN};
	private int windowWidth;
	private int windowHeight;
	private Brick emptyTarget = new Brick();

	
	/* Constructor
	 * Determines number of columns of bricks by dividing the width of the window 
	 * by the width of an individual brick.
	 * Determine the number of rows of bricks to fill up the top third of the window.
	 * Divides the size of the top third of the window by the brick height.
	 */
	public TargetWall(int windowWidth, int windowHeight) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
	}

	
	/* Create a wall of bricks
	 * Fills out the rows and columns of bricks
	 * Determines the position of the brick by the brick size and number of rows and columns.
	 * Then creates a brick with the next position and random color.
	 */

	
	public void combine(int outerLoop, int innerLoop, boolean createHardWall, boolean randomHeights) {
		for (int j = 0; j<outerLoop; j++) {
			int rows = innerLoop;
			if (randomHeights) {
				rows = random.nextInt(innerLoop) + 2;
			}
			for (int i = 0; i<rows; i++) {
				if (createHardWall) {
					if (j % 2 == 0) {
						buildWall(j,i);
					}
				} else {
					buildWall(j,i);
				}
			}
		}
	}
	
	private void buildWall(int row, int column) {
		double xCoordinate = row * emptyTarget.getTargetWidth();
        double yCoordinate = column * emptyTarget.getTargetHeight();
		Brick target = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
		target.createTarget(40, 100);  // 40,100
		targetWall.add(target);
	}
	
	public void createTargetWall() {
		emptyTarget.createTarget(40,100); // 40,100
		int numberOfColumns = this.windowWidth/emptyTarget.getTargetWidth();
		int numberOfRows = (this.windowHeight/3)/emptyTarget.getTargetHeight();
		combine(numberOfColumns, numberOfRows, false, false);
	}
	
	public void createIntermediateTargetWall() {
		emptyTarget.createTarget(40, 100); // 40,100
		int numberOfColumns = this.windowWidth/emptyTarget.getTargetWidth();
		int maxNumberOfRows = 6;
		combine(numberOfColumns, maxNumberOfRows, false, true);
	}
	
	public void createHardTargetWall() {
		emptyTarget.createTarget(30, 70);
		int numberOfColumns = this.windowWidth/emptyTarget.getTargetWidth();
		int numberOfRows = (int) (((this.windowHeight/3) / emptyTarget.getTargetHeight())*1.5);
		combine(numberOfColumns, numberOfRows, true, false);
	}
	
	


	
	private Paint generateRandomBrickColor () {
		Paint brickColor = colorList[random.nextInt(6)];
		return brickColor;
	}
	
	public ArrayList<Target> getBrickWall () {
		return targetWall;
	}

}
