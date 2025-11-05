// Author: Anna Rakes
// Abstract class for all game environments

package application;

import java.util.ArrayList;

public abstract class Environment {
	
	public abstract void setUpGameObjects();
	public abstract void resetEnvironment();
	public abstract void launchProjectile();
	public abstract void moveProjectiles(double elapsedTime);
	public abstract boolean isWallEmpty();
	public abstract int increaseLives();
	public abstract boolean handleLifeLost();
	public abstract int getLives();
	public abstract void checkAllCollisions();
	public abstract UserControl getController();
	
	public TargetWall getWall() {
		return new TargetWall();
	}
	
	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
	    return new ArrayList<>();
	}
	
	public void triggerBugDrop() {}

	public void resetBallPosition() {}
	
	public int resetEnvironmentForNextLevel(Level level) {
		return -1;
	}
	
	
	
	
	

	
}
