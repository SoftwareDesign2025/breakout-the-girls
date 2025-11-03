package application;

import java.util.ArrayList;

public abstract class Environment {
	
	public abstract int increaseLives();
	
	public abstract boolean handleLifeLost();

	public abstract void resetBallPosition();
	
	public abstract int resetEnvironmentForNextLevel(Level level);
	
	public abstract void resetEnvironment();
	
	public abstract void launchProjectile();
	
	public abstract void moveProjectile(double elapsedTime);
	
	public abstract void checkAllCollisions();
	
	public abstract boolean isWallEmpty();
	
	public abstract UserControl getController();
	
	public abstract TargetWall getWall();
	
	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
	    return new ArrayList<>();
	}
	
	public abstract void triggerBugDrop();
}
