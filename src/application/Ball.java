import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	
	private final double radius = 10;
    public final double SPEED = 350;
	
	private Circle ball;
    private Point2D ballVelocity;
    private Random random = new Random();
    private boolean gameLost = false;
    
	
	public void createBall(int windowWidth, int windowHeight) {
		ball = new Circle();		
		double xCoordinate = windowWidth/2;
		double yCoordinate = windowHeight*2.0/3.0;
		
		ball.setCenterX(xCoordinate);
		ball.setCenterY(yCoordinate);
		ball.setRadius(radius);
		ball.setFill(Color.BLACK);
		
		ballVelocity = new Point2D(0,-SPEED); // how many pixels over and down it moves by
	}
	
	/**
     * Move by taking one step based on its velocity.
     *
     * Note, elapsedTime is used to ensure consistent speed across different machines.
     */
    public void move (double elapsedTime) {
        ball.setCenterX(ball.getCenterX() + ballVelocity.getX() * elapsedTime);
        ball.setCenterY(ball.getCenterY() + ballVelocity.getY() * elapsedTime);
    }

    /**
     * Bounce off the walls represented by the edges of the screen.
     */
    public void outOfBoundsCollision (double screenWidth, double screenHeight) {
        if (ball.getCenterX() < 0 || ball.getCenterX() > screenWidth - ball.getBoundsInLocal().getWidth()) {
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        if (ball.getCenterY() < 0 ) {
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if (ball.getCenterY() > screenHeight - ball.getBoundsInLocal().getHeight()) {
        		gameLost = true;
        }
    }
    
	
	public Circle getBall() {
		return ball;
	}
	
	public boolean checkIfGameLost() {
		return gameLost;
	}
	
	
	
	// add method to change velocity of ball
	// collision for ball with brick, ball with wall, ball with paddle and powerup with paddle
	
	public void collisionWithBrickOrPaddle () {
		double newY = -ballVelocity.getY();
		double newX = ballVelocity.getX() + getRandomVelocityChange();
        ballVelocity = new Point2D(newX,newY);
	}
	
	
	public double getRandomVelocityChange() {
	    return (random.nextDouble() * 0.8) - 0.4; // -0.4 to +0.4
	}
	
	
	
	



}
