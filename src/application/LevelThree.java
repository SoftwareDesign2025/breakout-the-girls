// Level Three

package application;

public class LevelThree extends Level {
	
	
	public LevelThree (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		this.extraLifeOccurrences = 8;
        this.extendPaddleOccurrences = 9;
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
