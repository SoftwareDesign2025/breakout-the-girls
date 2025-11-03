// Collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class Collisions {
	
	
	
	private boolean genericBallRectangleCollision(Shape shape, Ball ball) {
		Shape ballRectangleIntersection = Shape.intersect(ball.getProjectile(), shape);
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
	
	public boolean aircraftBugCollision(Aircraft aircraft, TargetWall targetWall) {
		for (int i = targetWall.getWall().size() - 1; i >= 0; i--) {
			Target target = targetWall.getWall().get(i);
			Shape aircraftBugIntersection = Shape.intersect(aircraft.getController(), target.getTarget());
			if (aircraftBugIntersection.getBoundsInLocal().getWidth() != -1) {
				return true;
			}
		}
		return false;
	}
	
	public void bulletBugCollision(Bullet bullet, TargetWall targetWall, Score score) {
	    // Collide with stationary bugs
	    ArrayList<Target> wallTargets = targetWall.getWall();
	    for (int i = wallTargets.size() - 1; i >= 0; i--) {
	        Target target = wallTargets.get(i);
	        Shape intersection = Shape.intersect(bullet.getProjectile(), target.getTarget());
	        if (intersection.getBoundsInLocal().getWidth() != -1) {
	            target.destroyTarget();
	            score.addPoints(target.getTargetPoint());
	            wallTargets.remove(i);
	            break; // bullet stops after hitting one target
	        }
	    }

	    // Collide with falling bugs
	    ArrayList<Target> fallingBugs = targetWall.getFallingBugs();
	    for (int i = fallingBugs.size() - 1; i >= 0; i--) {
	        Target bug = fallingBugs.get(i);
	        Shape intersection = Shape.intersect(bullet.getProjectile(), bug.getTarget());
	        if (intersection.getBoundsInLocal().getWidth() != -1) {
	            bug.destroyTarget();
	            score.addPoints(bug.getTargetPoint());
	            fallingBugs.remove(i);
	            break; // bullet stops after hitting one target
	        }
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

