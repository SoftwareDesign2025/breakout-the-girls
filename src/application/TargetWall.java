// Author: Anna Rakes
// Store collection of targets and position them into a wall.
package application;

import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TargetWall {
	
	// Constants
	
	//Level 1
	private final int GENERIC_BRICK_HEIGHT = 40;
	private final int GENERIC_BRICK_WIDTH = 100;
	private final int MAX_NUMBER_OF_ROWS = 6;
	
	// Level 3
	private final int HARD_BRICK_HEIGHT = 30;
	private final int HARD_BRICK_WIDTH = 70;
	
	private final int WALL_HEIGHT_RATIO = 3;
	private final double TARGET_MULTIPLIER = 1.5;
	
	// Galaga
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
	
	
	/**
	 * method placeBricks
	 * @param row
	 * @param column
	 * Calculates the brick’s location based on its row and column in the layout.
	 * Creates a new Brick with a randomized color. Adds the brick to the target wall.
	 */
	private void placeBricks(int row, int column) {
		double xCoordinate = row * emptyBrick.getTargetWidth();
        double yCoordinate = column * emptyBrick.getTargetHeight();
		Brick target = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
		target.createTarget(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH);  // 40,100
		targetWall.add(target);
	}
	
	/**
	 * method populateGrid
	 * @param outerLoop
	 * @param innerLoop
	 * @param createHardWall
	 * @param randomHeights
	 * Populates the target wall with bricks based on grid dimensions.
	 * Optionally randomizes number of bricks in a column if randomheights is true.
	 * Optionally skips over every column if createHardWall is true.
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
	
	/**
	 * method initializeLayout
	 * @param brickHeight
	 * @param brickWidth
	 * @param rowCount
	 * @param createHardWall
	 * @param randomHeights
	 * Sets up initial brick wall for level.
	 * Calculates how many columns fit across the game window.
	 * Populates the grid with bricks using the specified row count and layout options.
	 */
	private void initializeLayout(int brickHeight, int brickWidth, int rowCount, boolean createHardWall, boolean randomHeights) {
	    emptyBrick.createTarget(brickHeight, brickWidth);
	    int numberOfColumns = (int) (window.getWindowWidth() / emptyBrick.getTargetWidth());
	    populateGrid(numberOfColumns, rowCount, createHardWall, randomHeights);
	}
	
	
	/**
	 * method buildEasyWall
	 * Constructs a simple brick wall layout for an easy level 1.
	 * Initializes the wall with uniform brick dimensions and no added difficulty modifiers.
	 */
	public void buildEasyWall() {
	    int rows = (int) ((window.getWindowHeight() / WALL_HEIGHT_RATIO) / GENERIC_BRICK_HEIGHT);
	    initializeLayout(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH, rows, false, false);
	}

	/**
	 * method buildIntermediateWall
	 * Constructs a moderately challenging brick wall layout for level 2.
	 * Enables randomized column heights.
	 */
	public void buildIntermediateWall() {
	    initializeLayout(GENERIC_BRICK_HEIGHT, GENERIC_BRICK_WIDTH, MAX_NUMBER_OF_ROWS, false, true);
	}

	/**
	 * method buildHardWall
	 * Constructs a challenging brick wall layout for level 3.
	 * Calculates the number of rows based on proportion of window height, brick size. 
	 * Smaller bricks and alternating columns used in this level
	 */
	public void buildHardWall() {
	    int rows = (int) (((window.getWindowHeight() / WALL_HEIGHT_RATIO) / HARD_BRICK_HEIGHT) * TARGET_MULTIPLIER);
	    initializeLayout(HARD_BRICK_HEIGHT, HARD_BRICK_WIDTH, rows, true, false);
	}
	
	
	/**
	 * method createBugWall
	 * Creates a empty bug to determine dimensions.
	 * Calculates the number of bugs per row based on the row index and total rows.
	 * Centers each row of bugs and places them on screen.
	 */
	public void createBugWallWithRows(int maxRows) {
	    Bug emptyBug = new Bug();
	    emptyBug.createTarget(BUG_HEIGHT, BUG_WIDTH);
	    double bugWidth = emptyBug.getTargetWidth();
	    double bugHeight = emptyBug.getTargetHeight();
	    int center = window.getWindowWidth() / 2;

	    for (int row = 0; row < maxRows; row++) {
	        int bugsInRow = calculateRowSize(row, maxRows);
	        placeRowOfBugs(row, bugsInRow, bugWidth, bugHeight, center);
	    }
	}
	
	public void buildEasyBugWall() {
	    createBugWallWithRows(6); 
	}

	public void buildIntermediateBugWall() {
	    createBugWallWithRows(9); 
	}

	public void buildHardBugWall() {
	    createBugWallWithRows(12);
	}
	
	/**
	 * method calculateRowSize
	 * @param row
	 * @param maxRows
	 *  Calculates the number of bugs in a row to create a diamond shape.
	 * @return number of bugs in a given row.
	 */
	private int calculateRowSize(int row, int maxRows) {
		if (row <= maxRows / 2) {
	        return row * 2 + 1;
	    } else {
	        return (maxRows - row - 1) * 2 + 1;
	    }
	}
	
	/**
	 * method placeRowOfBugs
	 * @param row
	 * @param bugsInRow
	 * @param bugWidth
	 * @param bugHeight
	 * @param center
	 * For each bug in a row, creates the bug and adds it to the target wall.
	 * Centers each row of bugs in the screen.
	 */
	private void placeRowOfBugs (int row, int bugsInRow, double bugWidth, double bugHeight, int center) {
		double centerPosition = center - (bugsInRow / 2.0) * bugWidth;
        double y = row * bugHeight;

        for (int i = 0; i < bugsInRow; i++) {
            double x = centerPosition + i * bugWidth;
            Bug bug = new Bug(x,y);
            bug.createTarget(bugHeight, bugWidth);
            targetWall.add(bug);
        }
	}
	
	/**
	 * method initiateBugDrop
	 * If no bugs are current falling and there are still bugs in the wall,
	 * selects a random bug from the wall and drops it. 
	 * Falling bug is added to list of bugs currently dropping on screen.
	 */
	public void initiateBugDrop() {
		if (!targetWall.isEmpty() && fallingBugs.isEmpty()) {
	        int randomBug = random.nextInt(targetWall.size());
	        Target bug = targetWall.remove(randomBug);
	        bug.startFalling();
	        fallingBugs.add(bug);
	    }
	}
	
	/**
	 * method updateFallingBugs
	 * @param elapsedTime
	 * Updates current position of all falling bugs and checks if any of the falling bugs
	 * go out of bounds. 
	 * If bug goes out of bounds, remove it from the falling bug list and return it.
	 * @return bugs that falls out of bounds
	 */
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

	

	// Picks a random color from the color list and returns the color.
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
