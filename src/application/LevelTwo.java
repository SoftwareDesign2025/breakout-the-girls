package application;

public class LevelTwo extends Environment {
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
	}
	
	@Override
	public void alterBrickWall () {
		super.brickWall.createIntermediateBrickWall();
	}
	

}
