package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bug extends Target {
	
	private Point2D bugVelocity;
	private final double SPEED = 100;

	
	public Bug (double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}
	
	public Bug() {}
	
	

	@Override
	public void createTarget(double bugHeight, double bugWidth) {
		targetHeight = bugHeight;
		targetWidth = bugWidth;
		target = new Rectangle(bugWidth, bugHeight);	
		target.setFill(Color.BLUEVIOLET);
		target.setStroke(Color.BLACK);
		
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}
	
	public void drop() {
	    bugVelocity = new Point2D(0, SPEED);
	}
	
	/**
     * Move the ball according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void move (double elapsedTime) {
        target.setX(target.getX() + bugVelocity.getX() * elapsedTime);
        target.setY(target.getY() + bugVelocity.getY() * elapsedTime);
    }
    
    public boolean bugOutOfBounds(int windowHeight) {
    		return (target.getY() > windowHeight);
    }



}
