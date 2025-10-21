// Abstract Level

package application;

import java.util.ArrayList;

public abstract class Level {

	protected int windowWidth;
    protected int windowHeight;
    protected int extraLifeOccurrences;
    protected int extendPaddleOccurrences;
    protected final int MAX_BRICK_POINT_VALUE = 25;
    protected BrickWall brickWall;
    

    public Level(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public BrickWall createBrickWall() {
	    this.brickWall = new BrickWall(windowWidth, windowHeight);
	    createSpecificBrickWall(this.brickWall);
	    return this.brickWall;
    }
    
    public abstract void createSpecificBrickWall(BrickWall wall);

    public ArrayList<PowerUp> generatePowerUps() {
		return new ArrayList<>();
	}
    
    public PowerUp determineSpawn(Brick brick) {
        int brickValue = brick.getBrickPoint();

        ArrayList<Integer> extraLifeValues = generateMultiples(extraLifeOccurrences);
        ArrayList<Integer> longPaddleValues = generateMultiples(extendPaddleOccurrences);

        if (longPaddleValues.contains(brickValue)) {
            return new LongPaddle(
                brick.getX() + brick.getBrickWidth()/2,
                brick.getY() + brick.getBrickHeight()/2
            );
        } else if (extraLifeValues.contains(brickValue)) {
            return new ExtraLife(
                brick.getX() + brick.getBrickWidth()/2,
                brick.getY() + brick.getBrickHeight()/2
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
