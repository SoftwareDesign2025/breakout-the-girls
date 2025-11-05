// Author: Anna Rakes
// Creates and handles an object representing the paddle.
package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Paddle extends UserControl {

	// how much to increase the paddle size by when a powerup is hit.
	private final int PADDLE_SIZE_INCREASE = 10;
	protected final Paint CONTROLLER_COLOR = Color.DEEPPINK;
	
	/**
     * Constructor - create the paddle and set its color
     */
    public Paddle() {
    	super();
        controller.setFill(CONTROLLER_COLOR); 
        controller.setStroke(null);          
    }
	
	/**
	 * method increasePaddleWidth
	 * Expands the width of the paddle and recenters it. 
	 */
	public void increasePaddleWidth() {
		double currentWidth = controller.getWidth();
	    double newWidth = currentWidth + PADDLE_SIZE_INCREASE;

	    double currentX = controller.getX();
	    controller.setX(currentX - (newWidth - currentWidth)/2);

	    controller.setWidth(newWidth);
	}
	
}
