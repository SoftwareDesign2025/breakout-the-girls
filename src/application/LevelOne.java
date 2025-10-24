// Level One

// Anna Rakes

package application;

public class LevelOne extends Level {
	private final int EXTRA_LIFE_OCCURRENCES = 5;
	private final int EXTEND_PADDLE_OCCURRENCES = 0;
	
	
	public LevelOne (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
        this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	@Override
	public void createSpecificBrickWall (BrickWall wall) {
		brickWall.createBrickWall();
	}


	@Override
	public Obstacle createObstacle() {
		return null;
	}

}
