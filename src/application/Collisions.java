// Author: Anna Rakes and Johnathan Meeks
// Handles all types of collisions

package application;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class Collisions implements CollisionHandler {
	
	/**
	 * method detectCollision
	 * @param shapeA
	 * @param shapeB
	 * determines if two shapes collide based on where they intersect.
	 * @return true if the two objects are colliding. 
	 */
	private boolean detectCollision(Shape shapeA, Shape shapeB) {
		Shape ballRectangleIntersection = Shape.intersect(shapeA, shapeB);
		return (ballRectangleIntersection.getBoundsInLocal().getWidth() != -1);
	}
	

	/**
	 * method ballPaddleCollision
	 * @param paddle
	 * @param ball
	 * checks to see if ball and paddle are colliding, if so, have the ball
	 * bounce off the paddle.
	 */
	public void ballPaddleCollision(Paddle paddle, Ball ball) {
		if (detectCollision(paddle.getController(), ball.getProjectile())) {
			ball.changeBallVelocity();
		}
	}
	
	
	/**
	 * method ballObstacleCollision
	 * @param obstacle
	 * @param ball
	 * If there is an obstacle object, check for a collision between the obstacle and the ball.
	 * If they collide, have the ball bounce off the obstacle. 
	 */
	public void ballObstacleCollision(Obstacle obstacle, Ball ball) {
		if (obstacle!= null) {
			if(detectCollision(obstacle.getObstacle(), ball.getProjectile())) {
			ball.changeBallVelocity();
			}
		}
	}
	
	/**
	 * method aircraftBugCollision
	 * @param aircraft
	 * @param targetWall
	 * check if there is a collision between the aircraft and every bug in the wall.
	 * @return true if the aircraft and a bug collide
	 */
	public boolean aircraftBugCollision(Aircraft aircraft, TargetWall targetWall) {
		for (int i = targetWall.getWall().size() - 1; i >= 0; i--) {
			Target target = targetWall.getWall().get(i);
			if (detectCollision(target.getTarget(), aircraft.getController())) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * made by Johnathan and Anna
	 * method handleTargetWallCollision
	 * @param targets
	 * @param shapeB
	 * @param score
	 * Checks for collisions between all the targets in the wall and a shape. If there's a 
	 * collision, destroy that target, receive the points associated with that target, and remove it
	 * from the target wall.
	 * @return  true if there was a collision between the shape and a target.
	 */
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
	
	/**
	 * method bulletBugCollision
	 * @param bullet
	 * @param targetWall
	 * @param score
	 * Checks if there is any collisions between the bullet and any bugs in the wall OR
	 * if there are any collisions between a bullet and any of the bugs no longer in the wall, 
	 * but falling.
	 * @return true if either of those collisions are occurring.
	 */
	public boolean bulletBugCollision(Bullet bullet, TargetWall targetWall, Score score) {
		return handleTargetWallCollision(targetWall.getWall(), bullet.getProjectile(), score) ||
		handleTargetWallCollision(targetWall.getFallingBugs(), bullet.getProjectile(), score);
	}
	
	
	/**
	 * made by Johnathan and Anna
	 * method ballTargetCollision
	 * @param root
	 * @param targets
	 * @param ball
	 * @param level
	 * @param powerUps
	 * @param score
	 * Detects collisions between the ball and all of the targets in the wall,
	 * collects powerUps, destroys brick, and gathers points if there was a collision. 
	 * 
	 */
	public void ballTargetCollision(Group root, ArrayList<Target> targets, Ball ball, Level level, ArrayList<PowerUp> powerUps, Score score) {
		for (int i = targets.size() - 1; i >= 0; i--) {
			Target target = targets.get(i);
			if (detectCollision(target.getTarget(), ball.getProjectile())) {
				ball.changeBallVelocity();
				spawnPowerUps(level, target, powerUps, root);
				target.destroyTarget();
				score.addPoints(target.getTargetPoint());
				targets.remove(i);
			}
		}
	}
	
	/**
	 * method spawnPowerUp
	 * @param level
	 * @param target
	 * @param powerUps
	 * @param root
	 * Attempts to spawn a power up if a collision is detected. If that brick
	 * contains a powerup, add the powerup to the scene so it can be dropped. 
	 */
	private void spawnPowerUps(Level level, Target target, ArrayList<PowerUp> powerUps, Group root) {
		PowerUp powerUp = level.determineSpawn(target);
		if (powerUp != null) {
			powerUps.add(powerUp);
			root.getChildren().add(powerUp.getVisualNode());
		}
	}


	/**
	 * method paddlePowerUpCollision
	 * @param environment
	 * @param powerUps
	 * @param paddle
	 * @param root
	 * @param windowHeight
	 * Iterates through falling power ups to check for collisions with the paddle
	 * If a power up is collected or falls off screen, it is removed from the game.
	 */
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
	
	/**
	 * Anna and Johnathan
	 * method initiatePowerUp
	 * @param environment
	 * @param powerUp
	 * @param paddle
	 * @param root
	 * @param windowHeight
	 * Makes a powerup fall. While falling, if the powerup collides with the paddle,
	 * call the powerup's effects, and remove the powerup from the scene since it was
	 * collected. If the powerup was not collected, make it inactive and remove from scene.
	 * @return true if powerup was activated or removed.
	 */
	private boolean initiatePowerUp(BreakoutEnvironment environment, PowerUp powerUp, Paddle paddle, Group root, int windowHeight) {
		powerUp.fall();
		if (detectCollision(powerUp.getVisualNode(), paddle.getController())) {
			powerUp.activatePowerUp(environment); 
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

