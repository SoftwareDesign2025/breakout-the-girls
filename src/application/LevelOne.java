package application;

public class LevelOne extends Environment {
	
	public LevelOne (int windowWidth, int windowHeight) {
		super();
	}
	
	@Override
	public void alterBrickWall () {
		super.brickWall.createBrickWall();
	}

}
