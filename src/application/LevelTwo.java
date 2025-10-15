// Level Two

package application;

import java.util.ArrayList;

public class LevelTwo extends Level {
	
	private final int EXTRA_LIFE_OCCURRENCES = 5;
	private final int EXTEND_PADDLE_OCCURRENCES = 10;
    private final int MAX_BRICK_POINT_VALUE = 25;
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
	}
	
	@Override
	public BrickWall createBrickWall () {
	    BrickWall brickWall = new BrickWall(windowWidth, windowHeight);
		brickWall.createIntermediateBrickWall();;
		return brickWall;
	}

	@Override
	public ArrayList<PowerUp> generatePowerUps() {
		return new ArrayList<>();
	}

	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(windowWidth, windowHeight, 160);
		return obstacle;
	}

	@Override
	public PowerUp determineSpawn(Brick brick) {

	    int brickValue = brick.getBrickPoint();

	    // Extra Life: every multiple of EXTRA_LIFE_OCCURRENCES
	    ArrayList<Integer> extraLifeValues = new ArrayList<>();
	    for (int i = EXTRA_LIFE_OCCURRENCES; i < MAX_BRICK_POINT_VALUE; i += EXTRA_LIFE_OCCURRENCES) {
	        extraLifeValues.add(i);
	    }

	    // Long Paddle: every multiple of EXTEND_PADDLE_OCCURRENCES
	    ArrayList<Integer> longPaddleValues = new ArrayList<>();
	    for (int i = EXTEND_PADDLE_OCCURRENCES; i < MAX_BRICK_POINT_VALUE; i += EXTEND_PADDLE_OCCURRENCES) {
	        longPaddleValues.add(i);
	    }

	    // Decide which power-up to spawn
	    if (longPaddleValues.contains(brickValue)) {
	        return new LongPaddle(brick.getX() + brick.getBrickWidth()/2, brick.getY() + brick.getBrickHeight()/2);
	    } else if (extraLifeValues.contains(brickValue)) {
	        return new ExtraLife(brick.getX() + brick.getBrickWidth()/2, brick.getY() + brick.getBrickHeight()/2);
	    }

	    return null; // no power-up
	}

}
