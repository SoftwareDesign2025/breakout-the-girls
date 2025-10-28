// Anna Rakes i
// Create the ball object
package application;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Projectile {

    /**
     * Bounce off the walls represented by the edges of the screen.
     * If the ball goes past the bottom of the screen, the game is lost.
     */
    public void outOfBoundsCollision (double screenWidth, double screenHeight) {
        if (projectile.getCenterX() - RADIUS < 0 || projectile.getCenterX() - RADIUS > screenWidth - projectile.getBoundsInLocal().getWidth()) {
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        if (projectile.getCenterY() - RADIUS < 0 ) {
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if (projectile.getCenterY() - RADIUS > screenHeight - projectile.getBoundsInLocal().getHeight()) {
        		roundLost = true;
        }
    }
    
	
	/* If the ball collides with a brick or paddle, this method is called.
	 * It reverse the y to send the brick the opposite direction, but slightly alters the 
	 * x which changes the angle at which the ball bounces off. 
	 */
	public void changeBallVelocity () {
		double newY = -ballVelocity.getY();
		double newX = ballVelocity.getX() + getRandomVelocityChange();
		
		double currentSpeed = Math.sqrt(newX*newX + newY*newY);
	    double scale = SPEED / currentSpeed;

	    newX *= scale;
	    newY *= scale;
	    
        ballVelocity = new Point2D(newX,newY);
	}
	
	
	/* Determines a random value to change the course of the ball by.
	 * Between -100 and 100.
	 */
	private double getRandomVelocityChange() {
	    return (random.nextDouble() * 200) - 100; 
	}
	
	/* Changes the ball's velocity from 0 at the start of the game
	 * to the defined speed when launchBall() is called.
	 */
	
	public void resetBallPosition(int windowWidth, int windowHeight) {
	    projectile.setCenterX(windowWidth / 2.0);
	    projectile.setCenterY(windowHeight * 0.8);
	    ballVelocity = new Point2D(0, 0);
	    roundLost = false; // Reset this flag!
	}



	
	
	



}