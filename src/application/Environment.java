package application;

public abstract class Environment {
	
	public abstract int increaseLives();
	
	public abstract boolean isBallLost();

	public abstract void resetBallPosition();
	
	public abstract int resetEnvironmentForNextLevel(Level level);
	
	public abstract void resetEnvironment();
	
	public abstract void launchProjectile();
	
	public abstract void moveProjectile(double elapsedTime);
	
	public abstract void checkAllCollisions();
	
	public abstract boolean isWallEmpty();
	
	public abstract UserControl getController();
	
	public abstract TargetWall getWall();
}
