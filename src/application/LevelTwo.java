package application;

public class LevelTwo extends Level {
	
	public LevelTwo (int windowWidth, int windowHeight) {
		super(windowWidth, windowHeight);
	}
	
	@Override
	public void alterBrickWall () {
		super.brickWall.createIntermediateBrickWall();
	}
	

}
