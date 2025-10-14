package application;

import java.util.ArrayList;

public class LevelOne extends Level {
	
	private final int EXTRA_LIFE_OCCURRENCES = 5;
    private final int MAX_BRICK_POINT_VALUE = 25;
	
	
	public LevelOne (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
	}
	
	@Override
	public BrickWall createBrickWall () {
		BrickWall brickWall = new BrickWall(windowWidth, windowHeight);
		brickWall.createBrickWall();
		return brickWall;
	}

	@Override
	public ArrayList<PowerUp> generatePowerUps() {
		return new ArrayList<>();
	}

	@Override
	public Obstacle createObstacle() {
		return null;
	}

	@Override
	public PowerUp determineSpawn(Brick brick) {

	    ArrayList<Integer> powerUpValues = new ArrayList<>();
	    for (int i = EXTRA_LIFE_OCCURRENCES; i < MAX_BRICK_POINT_VALUE; i += EXTRA_LIFE_OCCURRENCES) {
	        powerUpValues.add(i);
	    }

	    if (powerUpValues.contains(brick.getBrickPoint())) {
	        return new ExtraLife(brick.getX()+brick.getBrickWidth()/2, brick.getY()+brick.getBrickHeight()/2);
	    }

	    return null; // no power-up
	}

}
