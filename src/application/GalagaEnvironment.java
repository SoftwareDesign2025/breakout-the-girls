package application;


import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class GalagaEnvironment extends Environment implements GameEnvironment {

	private TargetWall bugWall;
	private Aircraft aircraft;
	private Score score;
	private Group root;
	private WindowDimensions window;
	private Collisions collisions;
	private int lives = 3;
	private ArrayList<Bullet> bullets = new ArrayList<>();



	public GalagaEnvironment(Group root, GameScreen ui, Score score, WindowDimensions window) {
	    this.root = root;
	    this.window = window;
	    this.score = score;
	    this.collisions = new Collisions();

	    setUpGameObjects();
	}

	public void setUpGameObjects () {
		aircraft = new Aircraft();
		bugWall = new TargetWall(window);
		bugWall.createBugWall();

		aircraft.createController(window);

		root.getChildren().add(aircraft.getController());
		for (Target bug : bugWall.getWall()) {
			root.getChildren().add(bug.getTarget());
		}
	}

	public void resetEnvironment() {
		root.getChildren().clear();
		setUpGameObjects();
	}


	public void checkAllCollisions() {
		for (int i = bullets.size() - 1; i >= 0; i--) {
	        Bullet bullet = bullets.get(i);
	        if (collisions.bulletBugCollision(bullet, bugWall, score)) {
	            root.getChildren().remove(bullet.getProjectile());
	            bullets.remove(i);

	            bugWall.initiateBugDrop();
	        }
	    }
	}
	
	public boolean handleLifeLost() {
		return collisions.aircraftBugCollision(aircraft, bugWall);
	}

	public void launchProjectile() {
	    shootBullet();
	}

	public void moveProjectile(double elapsedTime) {
	    moveProjectiles(elapsedTime);
	}
	


	public boolean isWallEmpty() {
		return bugWall.getWall().isEmpty();
	}

	public int increaseLives() {
		lives += 1;
		return lives;
	}


	
	public int resetEnvironmentForNextLevel(Level leve) {
		return -1;
	}

	
	public void triggerBugDrop() {
	    bugWall.initiateBugDrop();
	}

	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
		ArrayList<Target> bugsOutOfBounds = bugWall.updateFallingBugs(elapsedTime);
	    return bugsOutOfBounds;
	}
	
	public UserControl getController() {
		return aircraft;
	}
	//Katherine Hoadley
    // Shoot a new bullet from the aircraft
	public void shootBullet() {
	    Bullet bullet = new Bullet();
	    bullet.createProjectile(window);

	    double shipX = ((Rectangle) aircraft.getController()).getX();
	    double shipY = ((Rectangle) aircraft.getController()).getY();
	    double shipWidth = ((Rectangle) aircraft.getController()).getWidth();

	    bullet.getProjectile().setCenterX(shipX + shipWidth / 2);
	    bullet.getProjectile().setCenterY(shipY);

	    bullet.launchProjectile();
	    bullets.add(bullet);
	    root.getChildren().add(bullet.getProjectile());
	}
    
    //Katherine Hoadley
    public void moveProjectiles(double elapsedTime) {
        for (Bullet bullet : bullets) {
            bullet.move(elapsedTime);
        }
        removeBullet();

    }
    //Katherine Hoadley
    public void removeBullet() {
    	for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i).getProjectile().getCenterY() < 0) {
                root.getChildren().remove(bullets.get(i).getProjectile());
                bullets.remove(i);
            }
        }    
    }
}
