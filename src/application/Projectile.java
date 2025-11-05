//Katherine Hoadley
//Projectile is the super class for ball and bullet.
package application;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile {
	
	protected final int RADIUS = 10;
	protected final int SPEED = 300;
	protected final double SCREEN_HEIGHT_RATIO = 0.8;
	
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

	
	/* Changes the projectile velocity from 0 at the start of the game
	 * to the defined speed when launchBall() is called.
	 */
	public void launchProjectile() {
	    // Always set a fixed upward velocity
	    ballVelocity = new Point2D(0, -SPEED);
	    roundLost = false; // Ensure ball can move
	}
	
	/**
     * Move the ball according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void move (double elapsedTime) {
        projectile.setCenterX(projectile.getCenterX() + ballVelocity.getX() * elapsedTime);
        projectile.setCenterY(projectile.getCenterY() + ballVelocity.getY() * elapsedTime);
    }
    
    /* Create the ball
     * Determine starting location of by centering it in the middle of the widths and 2/3 of the
     * way down the screen.
     * Set the size of the ball and the color to black.
     * Start the ball in a static position for the beginning of the game.
     */
	public void createProjectile(WindowDimensions window) {
		projectile = new Circle();		
		double xCoordinate = window.getWindowWidth()/2;
		double yCoordinate = window.getWindowHeight() * SCREEN_HEIGHT_RATIO;
		
		projectile.setCenterX(xCoordinate);
		projectile.setCenterY(yCoordinate);
		projectile.setRadius(RADIUS);
		projectile.setFill(Color.BLACK);
		
		ballVelocity = new Point2D(0,0); // how many pixels over and down it moves by
	}
	
	
	
	



}