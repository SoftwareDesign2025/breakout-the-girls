// Anna Rakes
// Create the ball object for breakout
package application;


import javafx.geometry.Point2D;

public class Ball extends Projectile {
	
	private final int MAX_VELOCITY_RANGE = 100;
	private final int UPPER_LIMIT = 200;
	private final double SCREEN_HEIGHT_RATIO = 0.8;

	
	/**
	 * method: outOfBoundsCollision
	 * @param screenWidth
	 * @param screenHeight
	 * Handles if the ball goes beyond the screen. If the ball goes below the bottom edge, the
	 * round is lost. 
	 */
    public void outOfBoundsCollision (WindowDimensions window ) {
        if (projectile.getCenterX() - RADIUS < 0 || projectile.getCenterX() - RADIUS > window.getWindowWidth() - projectile.getBoundsInLocal().getWidth()) {
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        if (projectile.getCenterY() - RADIUS < 0 ) {
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if (projectile.getCenterY() - RADIUS > window.getWindowHeight() - projectile.getBoundsInLocal().getHeight()) {
        		roundLost = true;
        }
    }
    
    
    /**
	 * method: changeBallVelocity
	 * Reverses the ball's vertical direction and slightly tweaks its horizontal velocity.
	 * Adds a small random value to the X velocity. Updates the ball's velocity 
	 * with the new direction and scaled magnitude to keep consistent speed.
	 */
	public void changeBallVelocity () {
		double newY = -ballVelocity.getY();
		double newX = ballVelocity.getX() + getRandomVelocityChange();
		
		double currentSpeed = Math.sqrt(newX*newX + newY*newY);
	    double scale = SPEED / currentSpeed;

	    newX = newX*scale;
	    newY = newY*scale;
	    
        ballVelocity = new Point2D(newX,newY);
	}
	
	
	/**
	 * method: getRandomVelocityChange
	 * Generates a random value to change the course of the ball by between -100 and 100.
	 * @return the new random shifted velocity. 
	 */
	private double getRandomVelocityChange() {
	    return (random.nextDouble() * UPPER_LIMIT) - MAX_VELOCITY_RANGE; 
	}
	
	
	/* Changes the ball's velocity from 0 at the start of the game
	 * to the defined speed when launchBall() is called.
	 */
	
	public void resetBallPosition(WindowDimensions window) {
	    projectile.setCenterX(window.getWindowWidth() / 2.0);
	    projectile.setCenterY(window.getWindowHeight() * SCREEN_HEIGHT_RATIO);
	    ballVelocity = new Point2D(0, 0);
	    roundLost = false; // Reset this flag!
	}



	
	
	



}