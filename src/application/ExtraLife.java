//Katherine Hoadley
package application;
import javafx.scene.paint.Color;

public abstract class ExtraLife extends PowerUp{

	public ExtraLife(double x, double y) {
        super(x, y, Color.LIGHTGREEN); //light green visual to represent life
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
