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
	
	private boolean genericBallRectangleCollision(Rectangle rect) {
		Circle ball = environment.getBall().getBall();
		Shape ballRectangleIntersection = Shape.intersect(ball, rect);
		if (ballRectangleIntersection.getBoundsInLocal().getWidth() != -1) {
			environment.getBall().changeBallVelocity();
			return true;
		}
		else return false;
	}

	private void ballPaddleCollision() {
		Rectangle paddle = environment.getPaddle().getPaddle();
		genericBallRectangleCollision(paddle);
	}
	
	private void ballObstacleCollision() {
		if (environment.getObstacle()!= null) {
			Rectangle obstacle = environment.getObstacle().getObstacle();
			genericBallRectangleCollision(obstacle);
		}
	}

	private void ballBrickCollision() {
		ArrayList<Brick> bricks = environment.getBrickWall().getBrickWall();
		Group root = environment.getRoot();

		for (int i = bricks.size() - 1; i >= 0; i--) {
			Brick brick = bricks.get(i);
			if (genericBallRectangleCollision(brick.getBrick())) {
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
		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			if (powerUp.isActive()) {
				if (initiatePowerUp(powerUp)) {
					powerUps.remove(i);
				}
			}
		}
	}	
	
	private boolean initiatePowerUp(PowerUp powerUp ) {
		Paddle paddle = environment.getPaddle();
		Group root = environment.getRoot();
		int windowHeight = environment.getWindowHeight();
		powerUp.fall();
		if (powerUp.getVisualNode().getBoundsInParent().intersects(paddle.getPaddle().getBoundsInParent())) {
			powerUp.activatePowerUp(environment); // could modify lives externally if needed
			root.getChildren().remove(powerUp.getVisualNode());
			return true;
		} else if (powerUp.getY() > windowHeight) {
			powerUp.setActive(false);
			root.getChildren().remove(powerUp.getVisualNode());
			return true;
		} else {
			return false;
		}
	}
	

}

