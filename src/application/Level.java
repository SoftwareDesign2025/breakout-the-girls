// Abstract Level

// Anna Rakes


package application;

import java.util.ArrayList;

public abstract class Level {

	protected WindowDimensions window;
    protected int extraLifeOccurrences;
    protected int extendPaddleOccurrences;
    protected final int MAX_BRICK_POINT_VALUE = 25;
    protected TargetWall brickWall;
    

    public Level(WindowDimensions window) {
        this.window = window;
    }

    public TargetWall createBrickWall() {
	    this.brickWall = new TargetWall(window);
	    createSpecificBrickWall(this.brickWall);
	    return this.brickWall;
    }
    
    public abstract void createSpecificBrickWall(TargetWall wall);

    public ArrayList<PowerUp> generatePowerUps() {
		return new ArrayList<>();
	}
    
    public PowerUp determineSpawn(Target target) {
        int brickValue = target.getTargetPoint();

        ArrayList<Integer> extraLifeValues = generateMultiples(extraLifeOccurrences);
        ArrayList<Integer> longPaddleValues = generateMultiples(extendPaddleOccurrences);

        if (longPaddleValues.contains(brickValue)) {
            return new LongPaddle(
                target.getX() + target.getTargetWidth()/2,
                target.getY() + target.getTargetHeight()/2
            );
        } else if (extraLifeValues.contains(brickValue)) {
            return new ExtraLife(
                target.getX() + target.getTargetWidth()/2,
                target.getY() + target.getTargetHeight()/2
            );
        }

        return null;
    }

    private ArrayList<Integer> generateMultiples(int step) {
        ArrayList<Integer> values = new ArrayList<>();
        if (step <= 0) return values; 
        for (int i = step; i < MAX_BRICK_POINT_VALUE; i += step) {
            values.add(i);
        }
        return values;
    }
    
 
    public abstract Obstacle createObstacle();
}
