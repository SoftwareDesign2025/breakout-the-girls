// Author: Anna Rakes

package application;

import java.util.ArrayList;

import javafx.scene.Group;

public interface CollisionHandler {
	
	void ballPaddleCollision(Paddle paddle, Ball ball);
    void ballObstacleCollision(Obstacle obstacle, Ball ball);
    boolean aircraftBugCollision(Aircraft aircraft, TargetWall targetWall);
    boolean bulletBugCollision(Bullet bullet, TargetWall targetWall, Score score);
    void ballTargetCollision(Group root, ArrayList<Target> targets, Ball ball, Level level, ArrayList<PowerUp> powerUps, Score score);
    void paddlePowerUpCollision(BreakoutEnvironment environment, ArrayList<PowerUp> powerUps, Paddle paddle, Group root, int windowHeight);

}
