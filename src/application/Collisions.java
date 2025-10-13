package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collisions {

	private Environment level;

	public Collisions(Environment level) {
		this.level = level;
	}

	public void checkAllCollisions () {
		level.getBall().outOfBoundsCollision(level.getWindowWidth(), level.getWindowHeight());
		ballPaddleCollision();
		ballBrickCollision();
		paddlePowerUpCollision();
	}

	private void ballPaddleCollision() {
		Circle ball = level.getBall().getBall();
		Rectangle paddle = level.getPaddle().getPaddle();

		Shape ballPaddleIntersection = Shape.intersect(ball, paddle);
		if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
			level.getBall().changeBallVelocity();
		}
	}

	private void ballBrickCollision() {
		Circle ball = level.getBall().getBall();
		ArrayList<Brick> bricks = level.getBrickWall().getBrickWall();
		Group root = level.getRoot();

		for (int i = bricks.size() - 1; i >= 0; i--) {
			Brick brick = bricks.get(i);
			Shape intersection = Shape.intersect(ball, brick.getBrick());
			if (intersection.getBoundsInLocal().getWidth() != -1) {
				level.getBall().changeBallVelocity();

				PowerUp powerUp = brick.spawnExtraLife(level.getPowerUps().size());
				if (powerUp != null) {
					level.getPowerUps().add(powerUp);
					root.getChildren().add(powerUp.getVisualNode());
				}
				brick.destroyBrick();
				level.getScore().addPoints(brick.getBrickPoint());
				bricks.remove(i);
			}
		}
	}

	private void paddlePowerUpCollision() {
		ArrayList<PowerUp> powerUps = level.getPowerUps();
		Paddle paddle = level.getPaddle();
		Group root = level.getRoot();
		int windowHeight = level.getWindowHeight();

		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			if (powerUp.isActive()) {
				powerUp.fall();
				if (powerUp.getVisualNode().getBoundsInParent()
						.intersects(paddle.getPaddle().getBoundsInParent())) {

					powerUp.activatePowerUp(1); // could modify lives externally if needed
					root.getChildren().remove(powerUp.getVisualNode());
					powerUps.remove(i);
				} else if (powerUp.getY() > windowHeight) {
					powerUp.setActive(false);
					root.getChildren().remove(powerUp.getVisualNode());
					powerUps.remove(i);
				}
			}
		}
	}			

}

