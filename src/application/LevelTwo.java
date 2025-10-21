// Level Two

package application;

public class LevelTwo extends Level {
	
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		 this.extraLifeOccurrences = 8;
	     this.extendPaddleOccurrences = 8;
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
