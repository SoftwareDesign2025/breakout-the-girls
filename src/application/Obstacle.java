// Obstacle Class

package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
	private final int OBSTACLE_HEIGHT = 30;
	private final double SCREEN_HEIGHT_RATIO = 0.6;
	double xCoordinate;
	double yCoordinate;

	// Fields
	private Rectangle obstacle;
	
	    
    public Obstacle() {}

	
	
	// Creates the obstacle and sets the location of the obstacle on screen.
	public void createObstacle(WindowDimensions window, int obstacleWidth) {
		obstacle = new Rectangle(obstacleWidth, OBSTACLE_HEIGHT);				
		obstacle.setFill(Color.RED);
		
		obstacle.setX(window.getWindowWidth() / 2.0 - obstacleWidth / 2.0);
		obstacle.setY(window.getWindowHeight()*SCREEN_HEIGHT_RATIO - OBSTACLE_HEIGHT / 2.0);
	}
	
	// Return the obstacle object which is a Rectangle. 
	public Rectangle getObstacle() {
		return obstacle;
	}


	

}
