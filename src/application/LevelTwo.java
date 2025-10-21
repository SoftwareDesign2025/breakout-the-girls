// Level Two

package application;

public class LevelTwo extends Level {
	
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		 this.extraLifeOccurrences = 6;
	     this.extendPaddleOccurrences = 7;
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
