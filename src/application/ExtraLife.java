//Katherine Hoadley
package application;
import javafx.scene.paint.Color;

public class ExtraLife extends PowerUp{

	public ExtraLife(double x, double y) {
        super(x, y, Color.LIGHTGREEN); //light green visual to represent life
    }
	
	 @Override
	    public void activatePowerUp(Game game) {
	        // Add one life to the player
	        game.setLives(game.getLives() + 1);

	        // Deactivate so it stops moving and disappears
	        setActive(false);
	    }
}
