//Katherine Hoadley i
package application;

import javafx.scene.shape.Circle;

/*
 * Represents a falling PowerUp in the game.
 * 
 * PowerUps have a visual appearance (a Circle) and move downward each frame.
 * 
 * Collision and activation are managed externally by the Game class.
 */

public abstract class PowerUp {		//abstract because it needs a subclass to do anything, alone it is incomplete

	//fields
	protected double x;
	protected double y;
	protected boolean isActive;
	protected final int RADIUS = 10;
	protected final int FALL_SPEED = 2;
	protected Circle onScreenPowerUp;
	
	//constructor
	public PowerUp(double x, double y) {
		this.x = x;
		this.y = y;
		this.isActive = true;
		this.onScreenPowerUp = new Circle(x, y, RADIUS);
	}
	
	//method to add a powerUp into game
	public abstract void activatePowerUp(Environment environment);
	
	//this method controls the movement of the power-up.
	//It falls at a constant by changing it's y position.
	protected void fall() {
		if(isActive) {
			y+= FALL_SPEED;
			onScreenPowerUp.setCenterY(y);
		}
	}
	
	//getters
	public Circle getVisualNode() {
        return onScreenPowerUp;
    }

	public double getY() {
		return y; 
	}
	public boolean isActive() {
		return isActive; 
	}
	
	//setters
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

}
