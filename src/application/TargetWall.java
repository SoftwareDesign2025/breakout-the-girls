// Anna Rakes i
// Store collection of bricks and position them into a wall.
package application;

import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TargetWall {
	
	private final int GENERIC_BRICK_HEIGHT = 40;
	private final int GENERIC_BRICK_WIDTH = 100;
	private final int MAX_NUMBER_OF_ROWS = 6;
	private final int HARD_BRICK_HEIGHT = 30;
	private final int HARD_BRICK_WIDTH = 70;
	private final int WALL_HEIGHT_RATIO = 3;
	private final double TARGET_MULTIPLIER = 1.5;
	private final int BUG_HEIGHT = 20;
	private final int BUG_WIDTH = 50;
	private final int BUG_MAX_ROWS = 12;
	
	// Fields
	private ArrayList<Target> targetWall = new ArrayList<>();
    private Random random = new Random();
    
    // List of colors to pick from for the brick color:
	private Paint[] colorList = {Color.LIGHTCORAL, Color.LAVENDERBLUSH, Color.LIGHTSTEELBLUE, Color.MOCCASIN, 
			Color.LIGHTGOLDENRODYELLOW,Color.DARKSEAGREEN};
	private WindowDimensions window;
	private Target emptyBrick = new Brick();
	private ArrayList<Target> fallingBugs = new ArrayList<>();
	
	/* Constructor
	 * Determines number of columns of bricks by dividing the width of the window 
	 * by the width of an individual brick.
	 * Determine the number of rows of bricks to fill up the top third of the window.
	 * Divides the size of the top third of the window by the brick height.
	 */
	public TargetWall(WindowDimensions window) {
		this.window = window;
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
		target.createTarget(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH);  // 40,100
		targetWall.add(target);
	}
	
	public void createTargetWall() {
		emptyBrick.createTarget(GENERIC_BRICK_HEIGHT,GENERIC_BRICK_WIDTH); // 40,100
		int numberOfColumns = (int) (window.getWindowWidth()/emptyBrick.getTargetWidth());
		int numberOfRows = (int) ((window.getWindowHeight()/WALL_HEIGHT_RATIO)/emptyBrick.getTargetHeight());
		combine(numberOfColumns, numberOfRows, false, false);
	}
	
	public void createIntermediateTargetWall() {
		emptyBrick.createTarget(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH); // 40,100
		int numberOfColumns = (int) (window.getWindowWidth()/emptyBrick.getTargetWidth());
		int maxNumberOfRows = MAX_NUMBER_OF_ROWS;
		combine(numberOfColumns, maxNumberOfRows, false, true);
	}
	
	public void createHardTargetWall() {
		emptyBrick.createTarget(HARD_BRICK_HEIGHT, HARD_BRICK_WIDTH);
		int numberOfColumns = (int) (window.getWindowWidth()/emptyBrick.getTargetWidth());
		int numberOfRows = (int) (((window.getWindowHeight()/WALL_HEIGHT_RATIO) / emptyBrick.getTargetHeight())*TARGET_MULTIPLIER);
		combine(numberOfColumns, numberOfRows, true, false);
	}
	
	public void createBugWall() {
		    Bug emptyBug = new Bug();
		    emptyBug.createTarget(BUG_HEIGHT, BUG_WIDTH);
		    int bugsInRow;

		    double bugWidth = emptyBug.getTargetWidth();
		    double bugHeight = emptyBug.getTargetHeight();

		    int center = window.getWindowWidth() / 2;
		    int maxRows = BUG_MAX_ROWS;

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
	
	
	public void startBugDrop() {
		if (!targetWall.isEmpty()) {
	        int randomBug = random.nextInt(targetWall.size());
	        Target bug = targetWall.remove(randomBug);
	        bug.drop();
	        fallingBugs.add(bug);
	    }
	}
	
	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
		ArrayList<Target> bugsToRemove = new ArrayList<>();
	    for (Target bug : fallingBugs) {
	        bug.move(elapsedTime);
	        if (bug.bugOutOfBounds(window.getWindowHeight())) {
	            bugsToRemove.add(bug);
	        }
	    }
	    fallingBugs.removeAll(bugsToRemove);
	    return bugsToRemove;
	}

	

	
	private Paint generateRandomBrickColor () {
		Paint brickColor = colorList[random.nextInt(6)];
		return brickColor;
	}
	
	public ArrayList<Target> getWall () {
		return targetWall;
	}
	
	public ArrayList<Target> getFallingBugs() {
	    return fallingBugs;
	}
}
