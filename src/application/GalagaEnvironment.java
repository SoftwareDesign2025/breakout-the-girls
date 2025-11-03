package application;


import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class GalagaEnvironment extends Environment implements GameEnvironment {

	private TargetWall bugWall;
	private Aircraft aircraft;
	private Score score;
	private Group root;
	private GameScreen ui;
	private int windowWidth;
	private int windowHeight;
	private Collisions collisions;
	private int lives = 3;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private UserControl controller;



	public GalagaEnvironment(Group root, GameScreen ui, int windowWidth, int windowHeight, Score score) {
	    this.root = root;
	    this.windowHeight = windowHeight;
	    this.windowWidth = windowWidth;
	    this.ui = ui;
	    this.score = score;
	    this.collisions = new Collisions();

	    initializeObjects();
	}

	public void initializeObjects () {
		aircraft = new Aircraft();
		bugWall = new TargetWall(windowWidth, windowHeight);
		bugWall.createBugWall();

		aircraft.createController(windowWidth, windowHeight);

		root.getChildren().add(aircraft.getController());
		for (Target bug : bugWall.getWall()) {
			root.getChildren().add(bug.getTarget());
		}
	}

	public void resetEnvironment() {
		root.getChildren().clear();
		initializeObjects();
	}


	public void checkAllCollisions() {
	    for (Bullet bullet : bullets) {
	        collisions.bulletBugCollision(bullet, bugWall.getWall(), score);
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

	public Aircraft getAircraft() {
		return aircraft;
	}

	public TargetWall getWall() {
		return bugWall;
	}
	
	public int resetEnvironmentForNextLevel(Level leve) {
		return -1;
	}


	@Override
	public void resetBallPosition() {		
	}

	public UserControl getController() {
		return aircraft;
	}
	
	public void triggerBugDrop() {
	    bugWall.startBugDrop();
	}

	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
		ArrayList<Target> bugsOutOfBounds = bugWall.moveDroppedBug(elapsedTime);
	    return bugsOutOfBounds;
	}
	//Katherine Hoadley
    // Shoot a new bullet from the aircraft
	public void shootBullet() {
	    Bullet bullet = new Bullet();
	    bullet.createProjectile(windowWidth, windowHeight);

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
    //Moves the bullet then deletes it once it is off screen
    public void moveProjectiles(double elapsedTime) {
        for (Bullet bullet : bullets) {
            bullet.move(elapsedTime);
        }

        // Optionally remove bullets off screen
        for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i).getProjectile().getCenterY() < 0) {
                root.getChildren().remove(bullets.get(i).getProjectile());
                bullets.remove(i);
            }
        }    
    }
}
