// Level Three

// Anna Rakes

package application;

public class LevelThree extends Level {
	private final int EXTRA_LIFE_OCCURRENCES = 8;
	private final int EXTEND_PADDLE_OCCURRENCES = 8;
	private final int OBSTACLE_WIDTH = 200;
	
	public LevelThree (WindowDimensions window) {
		super(window);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
        this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	@Override
	public void createSpecificBrickWall (TargetWall wall) {
		brickWall.createHardTargetWall();
	}

	
	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(window, OBSTACLE_WIDTH);
		return obstacle;
	}

}
