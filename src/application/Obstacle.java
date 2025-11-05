// Author: Anna Rakes
// Obstacle Class

package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
	
	// Constants
	private final int OBSTACLE_HEIGHT = 30;
	private final double SCREEN_HEIGHT_RATIO = 0.6;
	
	// Obstacle location
	double xCoordinate;
	double yCoordinate;

	// Fields
	private Rectangle obstacle;
	
	// Default constructor   
    public Obstacle() {}
	
	/**
	 * method createObstacle
	 * @param window
	 * @param obstacleWidth
	 * Initializes the obstacle based on a given width and predefined height.
	 * Colors the obstacle red and sets the location of the obstacle to the 
	 * middle of the screen and 60% of the way down.
	 */
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
