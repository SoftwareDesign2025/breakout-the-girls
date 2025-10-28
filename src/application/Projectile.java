//Katherine Hoadley
//Projectile is the super class for ball and bullet.
package application;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Projectile {
	
	protected final double RADIUS = 10;
	protected final double SPEED = 300;
	
    // Fields:
	protected Circle projectile;
	protected Point2D ballVelocity;
	protected Random random = new Random();
	protected boolean roundLost = false;

	// Return true if the game is lost.
	public boolean checkIfRoundLost() {
		return roundLost;
	}
	
    // Return the projectile which is a circle
	public Circle getProjectile() {
		return projectile;
	}
	
	
	// abstract move to be implemented
    public abstract void move (double elapsedTime);

	
	/* Changes the projectile velocity from 0 at the start of the game
	 * to the defined speed when launchBall() is called.
	 */
	public void launchProjectile() {
	    // Always set a fixed upward velocity
	    ballVelocity = new Point2D(0, -SPEED);
	    roundLost = false; // Ensure ball can move
	}
	
	
	
	



}
