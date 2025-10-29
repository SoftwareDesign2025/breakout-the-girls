// Collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collisions {
	
	
	
	private boolean genericBallRectangleCollision(Rectangle rect, Ball ball) {
		Shape ballRectangleIntersection = Shape.intersect(ball.getProjectile(), rect);
		if (ballRectangleIntersection.getBoundsInLocal().getWidth() != -1) {
			ball.changeBallVelocity();
			return true;
		}
		else {
			return false;
		}
	}

	public void ballPaddleCollision(Paddle paddle, Ball ball) {
		genericBallRectangleCollision(paddle.getController(), ball);
	}
	
	public void ballObstacleCollision(Obstacle obstacle, Ball ball) {
		if (obstacle!= null) {
			genericBallRectangleCollision(obstacle.getObstacle(), ball);
		}
	}

	public void ballTargetCollision(Group root, ArrayList<Target> targets, Ball ball, Level level, ArrayList<PowerUp> powerUps, Score score) {
		for (int i = targets.size() - 1; i >= 0; i--) {
			Target target = targets.get(i);
			if (genericBallRectangleCollision(target.getTarget(), ball)) {
				PowerUp powerUp = level.determineSpawn(target);
				if (powerUp != null) {
					powerUps.add(powerUp);
					root.getChildren().add(powerUp.getVisualNode());
				}
				target.destroyTarget();
				score.addPoints(target.getTargetPoint());
				targets.remove(i);
			}
		}
	}

	public void paddlePowerUpCollision(BreakoutEnvironment environment, ArrayList<PowerUp> powerUps, Paddle paddle, Group root, int windowHeight) {
		for (int i = powerUps.size() - 1; i >= 0; i--) {
			PowerUp powerUp = powerUps.get(i);
			if (powerUp.isActive()) {
				if (initiatePowerUp(environment, powerUp, paddle, root, windowHeight)) {
					powerUps.remove(i);
				}
			}
		}
	}	
	
	private boolean initiatePowerUp(BreakoutEnvironment environment, PowerUp powerUp, Paddle paddle, Group root, int windowHeight) {
		powerUp.fall();
		if (powerUp.getVisualNode().getBoundsInParent().intersects(paddle.getController().getBoundsInParent())) {
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

