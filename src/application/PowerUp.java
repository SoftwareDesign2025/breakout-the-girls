//Katherine Hoadley
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
	protected int radius = 10;
	protected int fallSpeed = 2;
	protected Circle onScreenPowerUp;
	
	//constructor
	public PowerUp(double x, double y) {
		this.x = x;
		this.y = y;
		this.isActive = true;
		this.onScreenPowerUp = new Circle(x, y, radius);
	}
	
	//method to add a powerUp into game
	public abstract int activatePowerUp(int lives);
	
	//movement
	public void fall() {
		if(isActive) {
			y+= fallSpeed;
			onScreenPowerUp.setCenterY(y);
		}
	}
	
	//getters
	public Circle getVisualNode() {
        return onScreenPowerUp;
    }
	public double getX() { 
		return x;
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
