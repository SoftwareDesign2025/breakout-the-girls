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
	private Target emptyBrick = new Brick();

	
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
		double xCoordinate = row * emptyBrick.getTargetWidth();
        double yCoordinate = column * emptyBrick.getTargetHeight();
		Brick target = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
		target.createTarget(40, 100);  // 40,100
		targetWall.add(target);
	}
	
	public void createTargetWall() {
		emptyBrick.createTarget(40,100); // 40,100
		int numberOfColumns = (int) (this.windowWidth/emptyBrick.getTargetWidth());
		int numberOfRows = (int) ((this.windowHeight/3)/emptyBrick.getTargetHeight());
		combine(numberOfColumns, numberOfRows, false, false);
	}
	
	public void createIntermediateTargetWall() {
		emptyBrick.createTarget(40, 100); // 40,100
		int numberOfColumns = (int) (this.windowWidth/emptyBrick.getTargetWidth());
		int maxNumberOfRows = 6;
		combine(numberOfColumns, maxNumberOfRows, false, true);
	}
	
	public void createHardTargetWall() {
		emptyBrick.createTarget(30, 70);
		int numberOfColumns = (int) (this.windowWidth/emptyBrick.getTargetWidth());
		int numberOfRows = (int) (((this.windowHeight/3) / emptyBrick.getTargetHeight())*1.5);
		combine(numberOfColumns, numberOfRows, true, false);
	}
	
	public void createBugWall() {
		    Bug emptyBug = new Bug();
		    emptyBug.createTarget(20, 50);
		    int bugsInRow;

		    double bugWidth = emptyBug.getTargetWidth();
		    double bugHeight = emptyBug.getTargetHeight();

		    int center = windowWidth / 2;
		    int maxRows = 12;

		    for (int row = 0; row < maxRows; row++) {
		    	if (row <= maxRows / 2) {
		    	    bugsInRow = row * 2 + 1;
		    	} else {
		    	    bugsInRow = (maxRows - row - 1) * 2 + 1;
		    	}
		        double startX = center - (bugsInRow / 2.0) * bugWidth;
		        double y = row * bugHeight;

		        for (int i = 0; i < bugsInRow; i++) {
		            double x = startX + i * bugWidth;
		            Bug bug = new Bug(x,y);
		            bug.createTarget(bugHeight, bugWidth);
		            targetWall.add(bug);
		    }
		}
	}
	
	public Target moveBug() {
		int randomBug = random.nextInt(targetWall.size());
		Target bugToRemove = targetWall.get(randomBug);
		bugToRemove.moveBugDown();
		return bugToRemove;
	}
	

	

	
	private Paint generateRandomBrickColor () {
		Paint brickColor = colorList[random.nextInt(6)];
		return brickColor;
	}
	
	public ArrayList<Target> getWall () {
		return targetWall;
	}

}
