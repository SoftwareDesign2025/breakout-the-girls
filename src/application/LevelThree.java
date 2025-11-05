// Level Three

// Anna Rakes

package application;

public class LevelThree extends Level {
	// both extra lives and extend paddle occurrences only show up 1/8 of the time. 
	private final int EXTRA_LIFE_OCCURRENCES = 8;
	private final int EXTEND_PADDLE_OCCURRENCES = 8;
	private final int OBSTACLE_WIDTH = 200;
	
	public LevelThree (WindowDimensions window) {
		super(window);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
        this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	
	/**
	 * method createSpecificBrickWall
	 * creates the hardest, intricate, brick wall.
	 */
	@Override
	public void createSpecificBrickWall (TargetWall wall) {
		brickWall.buildHardWall();
	}

	
	// create an obstacle with size 200 for the width, the largest obstacle yet. 
	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(window, OBSTACLE_WIDTH);
		return obstacle;
	}

}
