// Collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class Collisions {
	
	
	
	private boolean detectCollision(Shape shapeA, Shape shapeB) {
		Shape ballRectangleIntersection = Shape.intersect(shapeA, shapeB);
		return (ballRectangleIntersection.getBoundsInLocal().getWidth() != -1);
	}
	

	public void ballPaddleCollision(Paddle paddle, Ball ball) {
		if (detectCollision(paddle.getController(), ball.getProjectile())) {
			ball.changeBallVelocity();
		}
	}
	
	public void ballObstacleCollision(Obstacle obstacle, Ball ball) {
		if (obstacle!= null) {
			if(detectCollision(obstacle.getObstacle(), ball.getProjectile())) {
			ball.changeBallVelocity();
			}
		}
	}
	
	public boolean aircraftBugCollision(Aircraft aircraft, TargetWall targetWall) {
		for (int i = targetWall.getWall().size() - 1; i >= 0; i--) {
			Target target = targetWall.getWall().get(i);
			if (detectCollision(target.getTarget(), aircraft.getController())) {
				return true;
			}
		}
		return false;
	}

	private boolean handleTargetWallCollision(ArrayList<Target> targets, Shape shapeB, Score score) {
		 for (int i = targets.size() - 1; i >= 0; i--) {
		        Target target = targets.get(i);
		        if (detectCollision(target.getTarget(), shapeB)) {
		            target.destroyTarget();
		            score.addPoints(target.getTargetPoint());
		            targets.remove(i);
		            return true;
		        }
		    }
		    return false;
	}
	
	public boolean bulletBugCollision(Bullet bullet, TargetWall targetWall, Score score) {
		return handleTargetWallCollision(targetWall.getWall(), bullet.getProjectile(), score) ||
		handleTargetWallCollision(targetWall.getFallingBugs(), bullet.getProjectile(), score);
	}
	
	public void ballTargetCollision(Group root, ArrayList<Target> targets, Ball ball, Level level, ArrayList<PowerUp> powerUps, Score score) {
		for (int i = targets.size() - 1; i >= 0; i--) {
			Target target = targets.get(i);
			if (detectCollision(target.getTarget(), ball.getProjectile())) {
				ball.changeBallVelocity();
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
		if (detectCollision(powerUp.getVisualNode(), paddle.getController())) {
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

