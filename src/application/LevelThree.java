package application;

public class LevelThree extends Environment {
	
	protected Obstacle obstacle;
	
	public LevelThree (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
		obstacle = new Obstacle();
	}
	
	@Override
	public void alterBrickWall () {
		super.brickWall.createHardBrickWall();
	}
	
	public void addObstacle () {
		obstacle.createObstacle(super.windowWidth, super.windowHeight);
	}

}
