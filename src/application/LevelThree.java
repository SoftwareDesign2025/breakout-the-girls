// Level Three

// Anna Rakes

package application;

public class LevelThree extends Level {
	private final int EXTRA_LIFE_OCCURRENCES = 8;
	private final int EXTEND_PADDLE_OCCURRENCES = 8;
	
	public LevelThree (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
        this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	@Override
	public void createSpecificBrickWall (BrickWall wall) {
		brickWall.createHardBrickWall();
	}

	
	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(windowWidth, windowHeight, 200);
		return obstacle;
	}

}
