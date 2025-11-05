package application;

import java.util.ArrayList;

public abstract class Environment {
	
	public abstract int increaseLives();
	
	public abstract boolean handleLifeLost();

	public void resetBallPosition() {}
	
	public abstract int resetEnvironmentForNextLevel(Level level);
	
	public abstract void resetEnvironment();
	
	public abstract void launchProjectile();
	
	public abstract void moveProjectile(double elapsedTime);
	
	public abstract void checkAllCollisions();
	
	public void moveProjectiles(double elapsedTime) {}
	
	public abstract int getLives();
	
	public abstract boolean isWallEmpty();
	
	public UserControl getController() {
		return new UserControl();
	}

	
	public TargetWall getWall() {
		return new TargetWall();
	}
	
	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
	    return new ArrayList<>();
	}
	
	public void triggerBugDrop() {}
}
