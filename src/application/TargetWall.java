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
	
	public TargetWall() {}
	
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

	
	private void populateGrid(int outerLoop, int innerLoop, boolean createHardWall, boolean randomHeights) {
		for (int j = 0; j<outerLoop; j++) {
			int rows = innerLoop;
			if (randomHeights) {
				rows = random.nextInt(innerLoop) + 2;
			}
			for (int i = 0; i<rows; i++) {
				if (createHardWall) {
					if (j % 2 == 0) {
						placeBricks(j,i);
					}
				} else {
					placeBricks(j,i);
				}
			}
		}
	}
	
	private void initializeLayout(int brickHeight, int brickWidth, int rowCount, boolean createHardWall, boolean randomHeights) {
	    emptyBrick.createTarget(brickHeight, brickWidth);
	    int numberOfColumns = (int) (window.getWindowWidth() / emptyBrick.getTargetWidth());
	    populateGrid(numberOfColumns, rowCount, createHardWall, randomHeights);
	}
	public void buildEasyWall() {
	    int rows = (int) ((window.getWindowHeight() / WALL_HEIGHT_RATIO) / GENERIC_BRICK_HEIGHT);
	    initializeLayout(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH, rows, false, false);
	}

	public void buildIntermediateWall() {
	    initializeLayout(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH, MAX_NUMBER_OF_ROWS, false, true);
	}

	public void buildHardWall() {
	    int rows = (int) (((window.getWindowHeight() / WALL_HEIGHT_RATIO) / HARD_BRICK_HEIGHT) * TARGET_MULTIPLIER);
	    initializeLayout(HARD_BRICK_HEIGHT, HARD_BRICK_WIDTH, rows, true, false);
	}
	
	private void placeBricks(int row, int column) {
		double xCoordinate = row * emptyBrick.getTargetWidth();
        double yCoordinate = column * emptyBrick.getTargetHeight();
		Brick target = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
		target.createTarget(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH);  // 40,100
		targetWall.add(target);
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
		    		bugsInRow = calculateRowSize(row, maxRows);
		        placeRowOfBugs(row, bugsInRow, bugWidth, bugHeight, center);
		    }
		}
	
	private int calculateRowSize(int row, int maxRows) {
		if (row <= maxRows / 2) {
	        return row * 2 + 1;
	    } else {
	        return (maxRows - row - 1) * 2 + 1;
	    }
	}
	
	private void placeRowOfBugs (int row, int bugsInRow, double bugWidth, double bugHeight, int center) {
		double startX = center - (bugsInRow / 2.0) * bugWidth;
        double y = row * bugHeight;

        for (int i = 0; i < bugsInRow; i++) {
            double x = startX + i * bugWidth;
            Bug bug = new Bug(x,y);
            bug.createTarget(bugHeight, bugWidth);
            targetWall.add(bug);
        }
	}
	
	
	public void initiateBugDrop() {
		if (!targetWall.isEmpty()) {
	        int randomBug = random.nextInt(targetWall.size());
	        Target bug = targetWall.remove(randomBug);
	        bug.startFalling();
	        fallingBugs.add(bug);
	    }
	}
	
	public ArrayList<Target> updateFallingBugs(double elapsedTime) {
		ArrayList<Target> bugsToRemove = new ArrayList<>();
	    for (Target bug : fallingBugs) {
	        bug.updatePosition(elapsedTime);
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
