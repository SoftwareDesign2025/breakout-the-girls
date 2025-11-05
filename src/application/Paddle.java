// Author: Anna Rakes
// Creates and handles an object representing the paddle.
package application;


public class Paddle extends UserControl {

	// how much to increase the paddle size by when a powerup is hit.
	private final int PADDLE_SIZE_INCREASE = 10;
	
	
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
