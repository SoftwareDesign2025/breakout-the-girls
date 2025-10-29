//Katherine Hoadley i
package application;

import javafx.scene.paint.Color;


/**
 * The ExtraLife class represents a specific type of PowerUp that grants
 * the player an additional life when activated.
 * 
 * This class extends the abstract PowerUp class and provides its own
 * implementation of the activatePowerUp() method, which increases
 * the player's life count by one and deactivates the power-up after use.
 */

public class ExtraLife extends PowerUp{

	//This is the constructor for the ExtraLife class
	public ExtraLife(double x, double y) {
        super(x, y);
		onScreenPowerUp.setFill(Color.LIGHTGREEN);

    }
	
	//Activates the power-up by taking in the environment, and then
	//having the environment increase the lives. It then turns off
	//after it is applied.
	 @Override
	 public void activatePowerUp(Environment environment) {
	    environment.increaseLives();
	    setActive(false);
	}
}
