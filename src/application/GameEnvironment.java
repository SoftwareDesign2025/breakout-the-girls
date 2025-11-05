package application;

public interface GameEnvironment {

	public void setUpGameObjects ();
	
	public void resetEnvironment();
	
	
	public void launchProjectile();
	
	public void moveProjectile(double elapsedTime);
	
	public boolean isWallEmpty();
	
	public int increaseLives();	
}
