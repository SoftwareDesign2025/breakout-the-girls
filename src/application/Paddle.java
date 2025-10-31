// Anna Rakes

// Creates and handles an object representing the paddle.
package application;


public class Paddle extends UserControl {

	private final int PADDLE_SIZE_INCREASE = 10;
	
	public void increasePaddleWidth() {
		double currentWidth = controller.getWidth();
	    double newWidth = currentWidth + PADDLE_SIZE_INCREASE;

	    double currentX = controller.getX();
	    controller.setX(currentX - (newWidth - currentWidth)/2);

	    controller.setWidth(newWidth);
	}
	
	
}
