// Level One

// Author: Anna Rakes

package application;

public class LevelOne extends Level {
	private final int EXTRA_LIFE_OCCURRENCES = 5;
	// no extend paddle powerups in this level, just extra lives.
	private final int EXTEND_PADDLE_OCCURRENCES = 0;
	
	
	public LevelOne (WindowDimensions window) {
		super(window);
		this.extraLifeOccurrences = EXTRA_LIFE_OCCURRENCES;
        this.extendPaddleOccurrences = EXTEND_PADDLE_OCCURRENCES;
	}
	
	/**
	 * method createSpecificBrickWall
	 * create an easy, simple, brick wall.
	 */
	@Override
	public void createSpecificBrickWall (TargetWall wall) {
		brickWall.buildEasyWall();
	}


	/**
	 * no obstacle in level one, so returns null.
	 */
	@Override
	public Obstacle createObstacle() {
		return null;
	}

}
