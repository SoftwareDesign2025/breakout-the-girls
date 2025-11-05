//Katherine Hoadley
// Creates and handles an object representing the paddle.
package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class UserControl {
	
	protected final int CONTROLLER_HEIGHT = 20;
	protected int controllerWidth = 70;
	protected final int CONTROLLER_SPEED = 10; 

	protected Rectangle controller;
	protected int windowWidth;
	protected double xCoordinate;
	protected double yCoordinate;
	protected boolean movingRight;
	protected boolean movingLeft;

	
	public UserControl() {
		controller = new Rectangle(controllerWidth, CONTROLLER_HEIGHT);

	}
	/* Method createPaddleShape
	 * Create attributes for the paddle, such as size and color.
	 */
	public void createController(WindowDimensions window) {
		this.windowWidth = window.getWindowWidth();
				
		xCoordinate = window.getWindowWidth()/2 - (controllerWidth/2.0);
		yCoordinate = window.getWindowHeight() * 0.9 - CONTROLLER_HEIGHT;
		
		controller.setX(xCoordinate);
		controller.setY(yCoordinate);
				
	}
	
	/* Method getPaddle
	 * get the paddle object
	 */
	public Shape getController() {
		return controller;
	}
	
	/* method movePaddleHorizontally
	 * Move the location of the paddle left or right across the screen
	 */
	private void moveControllerHorizontally (boolean goRight) {
		if(goRight)
			controller.setX(controller.getX() + CONTROLLER_SPEED);

		else {
			controller.setX(controller.getX() - CONTROLLER_SPEED);
		}
		keepControllerInBounds();
	}
	
	private void keepControllerInBounds() {
	    if (controller.getX() < 0) {
	        controller.setX(0);
	    }
	    if (controller.getX() + controller.getWidth() > windowWidth) {
	        controller.setX(windowWidth - controller.getWidth());
	    }
	}
	
	public void startMovingRight() {
	    movingRight = true;
	}

	public void stopMovingRight() {
	    movingRight = false;
	}

	public void startMovingLeft() {
	    movingLeft = true;
	}

	public void stopMovingLeft() {
	    movingLeft = false;
	}

	public void update() {
	    if (movingRight) moveControllerHorizontally(true);
	    if (movingLeft) moveControllerHorizontally(false);
	}
	
	public int getWidth() {
		return controllerWidth;
	}
	
	
	
	

}
