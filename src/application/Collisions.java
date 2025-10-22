// Collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collisions {

	private Environment environment;

	public Collisions(Environment level) {
		this.environment = level;
	}

	public void checkAllCollisions () {
		environment.getBall().outOfBoundsCollision(environment.getWindowWidth(), environment.getWindowHeight());
		ballPaddleCollision();
		ballBrickCollision();
		ballObstacleCollision();
		paddlePowerUpCollision();
	}

	private void ballPaddleCollision() {
		Circle ball = environment.getBall().getBall();
		Rectangle paddle = environment.getPaddle().getPaddle();

		Shape ballPaddleIntersection = Shape.intersect(ball, paddle);
		if (ballPaddleIntersection.getBoundsInLocal().getWidth() != -1) {
			environment.getBall().changeBallVelocity();
		}
	}
	
	private void ballObstacleCollision() {
		if (environment.getObstacle()!= null) {
			Circle ball = environment.getBall().getBall();
			Rectangle obstacle = environment.getObstacle().getObstacle();
			Shape ballObstacleIntersection = Shape.intersect(ball, obstacle);
			if (ballObstacleIntersection.getBoundsInLocal().getWidth() != -1) {
				environment.getBall().changeBallVelocity();
			}
		}
	}

	private void ballBrickCollision() {
		Circle ball = environment.getBall().getBall();
		ArrayList<Brick> bricks = environment.getBrickWall().getBrickWall();
		Group root = environment.getRoot();

		for (int i = bricks.size() - 1; i >= 0; i--) {
			Brick brick = bricks.get(i);
			Shape intersection = Shape.intersect(ball, brick.getBrick());
			if (intersection.getBoundsInLocal().getWidth() != -1) {
				environment.getBall().changeBallVelocity();

				PowerUp powerUp = environment.getLevel().determineSpawn(brick);
				if (powerUp != null) {
					environment.getPowerUps().add(powerUp);
					root.getChildren().add(powerUp.getVisualNode());
				}
				brick.destroyBrick();
				environment.getScore().addPoints(brick.getBrickPoint());
				bricks.remove(i);
			}
		}
	}

	private void paddlePowerUpCollision() {
		ArrayList<PowerUp> powerUps = environment.getPowerUps();
		Paddle paddle = environment.getPaddle();
		Group root = environment.getRoot();
		int windowHeight = environment.getWindowHeight();

		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			if (powerUp.isActive()) {
				powerUp.fall();
				if (powerUp.getVisualNode().getBoundsInParent()
						.intersects(paddle.getPaddle().getBoundsInParent())) {

					powerUp.activatePowerUp(environment); // could modify lives externally if needed
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

