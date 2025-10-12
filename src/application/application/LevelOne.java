package application;

public class LevelOne extends Level {
	
	public LevelOne (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
	}
	
	@Override
	public void alterBrickWall () {
		super.brickWall.createBrickWall();
	}

}
