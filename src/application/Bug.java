package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Bug extends Target {
	
	private Point2D bugVelocity;
	private final double SPEED = 200;

	
	public Bug (double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}
	
	public Bug() {}
	
	

	@Override
	public void createTarget(double bugHeight, double bugWidth) {
		super.createTarget(bugHeight, bugWidth);
		target.setFill(Color.BLUEVIOLET);
	}
	
	public void startFalling() {
	    bugVelocity = new Point2D(0, SPEED);
	}
	
	/**
     * Move the ball according to its velocity. 
     * Movement scaled by elapsedTime to ensure consistent speed.
     */
    public void updatePosition (double elapsedTime) {
        target.setX(target.getX() + bugVelocity.getX() * elapsedTime);
        target.setY(target.getY() + bugVelocity.getY() * elapsedTime);
    }
    
    public boolean bugOutOfBounds(int windowHeight) {
    		return (target.getY() > windowHeight);
    }



}
