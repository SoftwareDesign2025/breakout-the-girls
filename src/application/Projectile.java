//Katherine Hoadley
//Projectile is the super class for ball and bullet.
package application;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile {
	
	protected final double radius = 10;
	protected final double SPEED = 300;
	
    // Fields:
	protected Circle projectile;
	protected Point2D ballVelocity;
	protected Random random = new Random();
	protected boolean roundLost = false;


	
    // Return the projectile which is a circle
	public Circle getProjectile() {
		return projectile;
	}
	
	
	/**
     * Move the projectile according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void move (double elapsedTime) {
    	projectile.setCenterX(projectile.getCenterX() + ballVelocity.getX() * elapsedTime);
    	projectile.setCenterY(projectile.getCenterY() + ballVelocity.getY() * elapsedTime);
    }

	
	/* Changes the projectile velocity from 0 at the start of the game
	 * to the defined speed when launchBall() is called.
	 */
	public void launchBall() {
	    // Always set a fixed upward velocity
	    ballVelocity = new Point2D(0, -SPEED);
	    roundLost = false; // Ensure ball can move
	}
	
	
	
	



}
