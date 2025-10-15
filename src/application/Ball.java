// Anna Rakes
// Create the ball object
package application;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	
	private final double radius = 10;
    private final double SPEED = 150;
	
    // Fields:
	private Circle ball;
    private Point2D ballVelocity;
    private Random random = new Random();
    private boolean roundLost = false;
    
	
    /* Create the ball
     * Determine starting location of by centering it in the middle of the widths and 2/3 of the
     * way down the screen.
     * Set the size of the ball and the color to black.
     * Start the ball in a static position for the beginning of the game.
     */
	public void createBall(int windowWidth, int windowHeight) {
		ball = new Circle();		
		double xCoordinate = windowWidth/2;
		double yCoordinate = windowHeight * 0.8;
		
		ball.setCenterX(xCoordinate);
		ball.setCenterY(yCoordinate);
		ball.setRadius(radius);
		ball.setFill(Color.BLACK);
		
		ballVelocity = new Point2D(0,0); // how many pixels over and down it moves by
	}
	
	/**
     * Move the ball according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void move (double elapsedTime) {
        ball.setCenterX(ball.getCenterX() + ballVelocity.getX() * elapsedTime);
        ball.setCenterY(ball.getCenterY() + ballVelocity.getY() * elapsedTime);
    }

    /**
     * Bounce off the walls represented by the edges of the screen.
     * If the ball goes past the bottom of the screen, the game is lost.
     */
    public void outOfBoundsCollision (double screenWidth, double screenHeight) {
        if (ball.getCenterX() - radius < 0 || ball.getCenterX() - radius > screenWidth - ball.getBoundsInLocal().getWidth()) {
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        if (ball.getCenterY() - radius < 0 ) {
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if (ball.getCenterY() - radius > screenHeight - ball.getBoundsInLocal().getHeight()) {
        		roundLost = true;
        }
    }
    
	
    // Return the ball which is a circle
	public Circle getBall() {
		return ball;
	}
	
	// Return true if the game is lost.
	public boolean checkIfRoundLost() {
		return roundLost;
	}
	
	
	
	/* If the ball collides with a brick or paddle, this method is called.
	 * It reverse the y to send the brick the opposite direction, but slightly alters the 
	 * x which changes the angle at which the ball bounces off. 
	 */
	public void changeBallVelocity () {
		double newY = -ballVelocity.getY();
		double newX = ballVelocity.getX() + getRandomVelocityChange();
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
	    ball.setCenterX(windowWidth / 2.0);
	    ball.setCenterY(windowHeight * 0.8);
	    ballVelocity = new Point2D(0, 0);
	    roundLost = false; // Reset this flag!
	}

	public void launchBall() {
	    // Always set a fixed upward velocity
	    ballVelocity = new Point2D(0, -SPEED);
	    roundLost = false; // Ensure ball can move
	}
	
	
	
	



}
