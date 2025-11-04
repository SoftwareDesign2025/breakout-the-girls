// Level Two


package application;

public class LevelTwo extends Level {
	
	private final int EXTRA_LIFE_OCCURRENCES = 6;
	private final int EXTEND_PADDLE_OCCURRENCES = 7;
	private final int OBSTACLE_WIDTH = 160;
	
	
	public LevelTwo (WindowDimensions window) {
		super(window);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
	    this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	@Override
	public void createSpecificBrickWall (TargetWall wall) {
		brickWall.createIntermediateTargetWall();
	}



	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(window, OBSTACLE_WIDTH);
		return obstacle;
	}



}
