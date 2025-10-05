import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	
	private final double radius = 10;
    public final int BALL_MIN_SPEED = -400;
    public final int BALL_MAX_SPEED = 400;
	
	private Circle ball;
    private Point2D ballVelocity;
    private Random random = new Random();
    private boolean gameLost;
    
	
	public void createBall(int windowWidth, int windowHeight) {
		ball = new Circle();		
		double xCoordinate = windowWidth/2;
		double yCoordinate = windowHeight*2.0/3.0;
		
		ball.setCenterX(xCoordinate);
		ball.setCenterY(yCoordinate);
		ball.setRadius(radius);
		ball.setFill(Color.BLACK);
		
		ballVelocity = new Point2D(getRandomInRange(BALL_MIN_SPEED, BALL_MAX_SPEED),
                getRandomInRange(BALL_MIN_SPEED, BALL_MAX_SPEED));
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
    public boolean keepBallWithinBounds (double screenWidth, double screenHeight) {
        // collide all bouncers against the walls
        if (ball.getCenterX() < 0 || ball.getCenterX() > screenWidth - ball.getBoundsInLocal().getWidth()) {
            ballVelocity = new Point2D(-ballVelocity.getX(), ballVelocity.getY());
        }
        if (ball.getCenterY() < 0 ) {
            ballVelocity = new Point2D(ballVelocity.getX(), -ballVelocity.getY());
        }
        if (ball.getCenterY() > screenHeight - ball.getBoundsInLocal().getHeight()) {
        		return true;
        }
        return false;
    }
    
    // Returns an "interesting", non-zero random value in the range (min, max)
    private int getRandomInRange (int min, int max) {
        return min + random.nextInt(max - min) + 1;
    }
	
	public Circle getBall() {
		return ball;
	}
	
	public boolean checkIfGameLost() {
		if (gameLost) {
			return true;
		} else {
			return false;
		}
	}
	
	



}
