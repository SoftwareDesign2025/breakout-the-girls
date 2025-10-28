// Collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collisions {
	
	
	
	private boolean genericBallRectangleCollision(Rectangle rect, Ball ball) {
		Shape ballRectangleIntersection = Shape.intersect(ball.getBall(), rect);
		if (ballRectangleIntersection.getBoundsInLocal().getWidth() != -1) {
			ball.changeBallVelocity();
			return true;
		}
		else {
			return false;
		}
	}

	public void ballPaddleCollision(Paddle paddle, Ball ball) {
		genericBallRectangleCollision(paddle.getPaddle(), ball);
	}
	
	public void ballObstacleCollision(Obstacle obstacle, Ball ball) {
		if (obstacle!= null) {
			genericBallRectangleCollision(obstacle.getObstacle(), ball);
		}
	}

	public void ballBrickCollision(Group root, ArrayList<Brick> bricks, Ball ball, Level level, ArrayList<PowerUp> powerUps, Score score) {
		for (int i = bricks.size() - 1; i >= 0; i--) {
			Brick brick = bricks.get(i);
			if (genericBallRectangleCollision(brick.getBrick(), ball)) {
				PowerUp powerUp = level.determineSpawn(brick);
				if (powerUp != null) {
					powerUps.add(powerUp);
					root.getChildren().add(powerUp.getVisualNode());
				}
				brick.destroyBrick();
				score.addPoints(brick.getBrickPoint());
				bricks.remove(i);
			}
		}
	}

	public void paddlePowerUpCollision(Environment environment, ArrayList<PowerUp> powerUps, Paddle paddle, Group root, int windowHeight) {
		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			if (powerUp.isActive()) {
				if (initiatePowerUp(environment, powerUp, paddle, root, windowHeight)) {
					powerUps.remove(i);
				}
			}
		}
	}	
	
	private boolean initiatePowerUp(Environment environment, PowerUp powerUp, Paddle paddle, Group root, int windowHeight) {
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

