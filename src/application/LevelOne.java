// Level One

package application;

public class LevelOne extends Level {
	
	
	public LevelOne (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		this.extraLifeOccurrences = 5;
        this.extendPaddleOccurrences = 0;
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
