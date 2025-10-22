// Anna Rakes i
// Creates and handles an object representing the paddle.
package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle {
	
	private final Paint PADDLE_COLOR = Color.DEEPPINK;
	private final int PADDLE_HEIGHT = 20;
	private int paddleWidth = 70;
	private final int PADDLE_SPEED = 10; 
	private final int PADDLE_SIZE_INCREASE = 10;


	private Rectangle paddle;
	private int windowWidth;
	private double xCoordinate;
	private double yCoordinate;
	private boolean movingRight;
	private boolean movingLeft;

	
	/* Method createPaddleShape
	 * Create attributes for the paddle, such as size and color.
	 */
	public void createPaddle(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		
		paddle = new Rectangle(paddleWidth, PADDLE_HEIGHT);
		
		xCoordinate = windowWidth/2 - (paddleWidth/2.0);
		yCoordinate = windowHeight * 0.9 - PADDLE_HEIGHT;
		
		paddle.setX(xCoordinate);
		paddle.setY(yCoordinate);
		
		paddle.setFill(PADDLE_COLOR);
		
	}
	
	/* Method getPaddle
	 * get the paddle object
	 */
	public Rectangle getPaddle() {
		return paddle;
	}
	
	/* method movePaddleHorizontally
	 * Move the location of the paddle left or right across the screen
	 */
	private void movePaddleHorizontally (boolean goRight) {
		if(goRight)
			paddle.setX(paddle.getX() + PADDLE_SPEED);

		else {
			paddle.setX(paddle.getX() - PADDLE_SPEED);
		}
		keepPaddleInBounds();
	}
	
	/* Method handleKeyInput
	 * based on the key pressed, move the paddle left or right.
	 */
//	public void handleKeyInput (KeyCode code) {
//		if (code == KeyCode.RIGHT) {
//			movePaddleHorizontally(true);
//		} 
//		else if (code == KeyCode.LEFT) {
//			movePaddleHorizontally(false);
//		}
//	}
	
	private void keepPaddleInBounds() {
	    if (paddle.getX() < 0) {
	        paddle.setX(0);
	    }
	    if (paddle.getX() + paddle.getWidth() > windowWidth) {
	        paddle.setX(windowWidth - paddle.getWidth());
	    }
	}
	
	public void increasePaddleWidth() {
		double currentWidth = paddle.getWidth();
	    double newWidth = currentWidth + PADDLE_SIZE_INCREASE;

	    double currentX = paddle.getX();
	    paddle.setX(currentX - (newWidth - currentWidth)/2);

	    paddle.setWidth(newWidth);
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
	    if (movingRight) movePaddleHorizontally(true);
	    if (movingLeft) movePaddleHorizontally(false);
	}
	
	
	
	

}
