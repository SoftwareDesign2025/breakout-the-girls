// Level Two


package application;

public class LevelTwo extends Level {
	
	// extra life powerups only show up 1/6 of the time, and extra paddle powerups 
	// only show up 1/7 of the time. 
	private final int EXTRA_LIFE_OCCURRENCES = 6;
	private final int EXTEND_PADDLE_OCCURRENCES = 7;
	private final int OBSTACLE_WIDTH = 160;
	
	
	public LevelTwo (WindowDimensions window) {
		super(window);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
	    this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	/**
	 * method createSpecificBrickWall
	 * create an intermediate difficulty wall. 
	 */
	@Override
	public void createSpecificBrickWall (TargetWall wall) {
		brickWall.buildIntermediateWall();
	}


	// create an obstacle for the game with a size of 160 width.
	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(window, OBSTACLE_WIDTH);
		return obstacle;
	}
	
	@Override
	public void createBugWall(TargetWall wall) {
	    wall.buildIntermediateBugWall();
	}



}
