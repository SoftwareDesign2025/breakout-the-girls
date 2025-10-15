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

	public ExtraLife(double x, double y) {
        super(x, y);
		onScreenPowerUp.setFill(Color.LIGHTGREEN);

    }
	
	 @Override
	 public int activatePowerUp(int lives) {
	    // Add one life to the player
	    lives++;

	    // Deactivate so it stops moving and disappears
	    setActive(false);
	    return lives;
	}
}
