package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
	private final int OBSTACLE_HEIGHT = 10;
	private final int OBSTACLE_WIDTH = 40;
	double xCoordinate;
	double yCoordinate;

	// Fields
	private Rectangle obstacle;
	
	    
    public Obstacle() {}

	
	
	// Creates the obstacle and sets the location of the obstacle on screen.
	public void createObstacle(int windowWidth, int windowHeight) {
		obstacle = new Rectangle(OBSTACLE_WIDTH, OBSTACLE_HEIGHT);				
		obstacle.setFill(Color.RED);
		
		obstacle.setX(windowWidth / 2.0 - OBSTACLE_WIDTH / 2.0);
		obstacle.setY(windowHeight * 2.0 / 3.0 - OBSTACLE_HEIGHT / 2.0);
	}
	
	// Return the obstacle object which is a Rectangle. 
	public Rectangle getObstacle() {
		return obstacle;
	}


	

}
