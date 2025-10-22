// Level Two

package application;

public class LevelTwo extends Level {
	
	private final int EXTRA_LIFE_OCCURRENCES = 6;
	private final int EXTEND_PADDLE_OCCURRENCES = 7;
	
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
	    this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	@Override
	public void createSpecificBrickWall (BrickWall wall) {
		brickWall.createIntermediateBrickWall();
	}



	@Override
	public Obstacle createObstacle() {
		Obstacle obstacle = new Obstacle();
		obstacle.createObstacle(windowWidth, windowHeight, 160);
		return obstacle;
	}



}
