// Author: Anna Rakes
// Abstract Level

package application;

import java.util.ArrayList;


public abstract class Level {
	
	// Constants
    protected final int MAX_BRICK_POINT_VALUE = 25;

    // Objects
	protected WindowDimensions window;
    protected TargetWall brickWall;
	
	// How many extra life powerups there are in the level.
    protected int extraLifeOccurrences;
    
    // How many extend paddle powerups there are in the level.
    protected int extendPaddleOccurrences;
        

    public Level(WindowDimensions window) {
        this.window = window;
    }

    /**
     * Initializes and creates a brick wall respective to its level. 
     * @return constructed target wall.
     */
    public TargetWall createBrickWall() {
	    this.brickWall = new TargetWall(window);
	    createSpecificBrickWall(this.brickWall);
	    return this.brickWall;
    }
    
    public abstract void createSpecificBrickWall(TargetWall wall);

    /* method generatePowerUps
     * returns empty arraylist. 
     */
    public ArrayList<PowerUp> generatePowerUps() {
		return new ArrayList<>();
	}
    
    
    /**
     * method determineSpawn
     * @param target
     * Determines whether a destroyed target should spawn a powerup based on its point value.
     * If the target's point value matches a predefined list of power up locations (point values),
     * then the powerup is spawned. 
     * @return a new powerup at center of target. 
     */
    public PowerUp determineSpawn(Target target) {
        int brickValue = target.getTargetPoint();
        double x = getCenterX(target);
        double y = getCenterY(target);

        ArrayList<Integer> extraLifeValues = generateMultiples(extraLifeOccurrences);
        ArrayList<Integer> longPaddleValues = generateMultiples(extendPaddleOccurrences);

        if (longPaddleValues.contains(brickValue)) {
            return new LongPaddle(x, y);
        } else if (extraLifeValues.contains(brickValue)) {
            return new ExtraLife(x, y);
        }
        return null;
    }
    
    /**
     * method getCenterX
     * @param target
     * @return middle of target width
     */
    private double getCenterX(Target target) {
        return target.getX() + target.getTargetWidth() / 2;
    }

    /**
     * method getCenterY
     * @param target
     * @return middle of target height
     */
    private double getCenterY(Target target) {
        return target.getY() + target.getTargetHeight() / 2;
    }

    /**
     * method generateMultiples
     * @param step
     * Determines which of all the brick point values will hold a powerup. 
     * For example, if brick point values range from 0 to 25, and extralife occurrences
     * is 5. then bricks with a point value of 5,10,15,20, will have a powerup.
     * @return list of where the powerups occur (ex: 5,10,15,20)
     */
    private ArrayList<Integer> generateMultiples(int step) {
        ArrayList<Integer> values = new ArrayList<>();
        if (step <= 0) return values; 
        for (int i = step; i < MAX_BRICK_POINT_VALUE; i += step) {
            values.add(i);
        }
        return values;
    }
    
    public abstract void createBugWall(TargetWall wall) ;
    
    public abstract Obstacle createObstacle();
}
