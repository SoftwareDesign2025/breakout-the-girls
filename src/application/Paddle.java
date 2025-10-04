// Anna Rakes
// Creates and handles an object representing the paddle.

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle {
	
	public static final Paint MOVER_COLOR = Color.DEEPPINK;
	public static final int PADDLE_HEIGHT = 10;
	public static final int PADDLE_WIDTH = 45;
	public static final int MOVER_SPEED = 5;


	private Rectangle paddle;
	private int windowWidth;
	private int windowHeight;
	private double rectangleHeight;
	private double rectangleWidth;
	private double xCoordinate;
	private double yCoordinate;

	
	/* Method createPaddleShape
	 * Create attributes for the paddle, such as size and color.
	 */
	public void createPaddle(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		paddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT);
		rectangleHeight = paddle.getHeight();
		rectangleWidth = paddle.getWidth();
		
		xCoordinate = (this.windowWidth - rectangleWidth) / 2;
		yCoordinate = this.windowHeight - rectangleHeight - 50;
		
		paddle.setX(xCoordinate);
		paddle.setY(yCoordinate);
		
		paddle.setFill(MOVER_COLOR);
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
	public void movePaddleHorizontally (boolean goRight) {
		if(goRight)
			paddle.setX(paddle.getX() - MOVER_SPEED);

		else {
			paddle.setX(paddle.getX() + MOVER_SPEED);
		}
	}
	
	/* Method handleKeyInput
	 * based on the key pressed, move the paddle left or right.
	 */
	public void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			movePaddleHorizontally(false);
		} 
		else if (code == KeyCode.LEFT) {
			movePaddleHorizontally(true);
		}
	}
	
	
	
	

}
