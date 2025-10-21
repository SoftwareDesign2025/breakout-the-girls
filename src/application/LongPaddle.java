//Katherine Hoadley i
package application;

import javafx.scene.paint.Color;

/**
 * The LongPaddle class represents a specific type of PowerUp that grants
 * the player double the paddle length when activated.
 * 
 * This class extends the abstract PowerUp class and provides its own
 * implementation of the activatePowerUp() method, which increases
 * the player's paddle length by two times and deactivates the power-up after use.
 */

public class LongPaddle extends PowerUp{
	
	private int PADDLE_LENGTH_MULTIPLIER = 2;
	
	public LongPaddle(double x, double y) {
		super(x, y);
		onScreenPowerUp.setFill(Color.LIGHTPINK);
		
	}

	@Override
	public int activatePowerUp(int paddleLength) {
		int longPaddleLength = paddleLength * PADDLE_LENGTH_MULTIPLIER;
		setActive(false);
		return longPaddleLength;
	}

}
