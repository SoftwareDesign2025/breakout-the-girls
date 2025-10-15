// Abstract Level

package application;

import java.util.ArrayList;

public abstract class Level {

	protected int windowWidth;
    protected int windowHeight;
    protected BrickWall brickWall;

    public Level(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public abstract BrickWall createBrickWall();

    public abstract ArrayList<PowerUp> generatePowerUps();
    
    public abstract PowerUp determineSpawn(Brick brick);


    public abstract Obstacle createObstacle();
}
