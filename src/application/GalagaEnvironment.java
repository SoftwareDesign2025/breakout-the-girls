// Author: Anna Rakes
// Sets the scene for the Galaga game.

package application;


import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class GalagaEnvironment extends Environment{

	// Game objects
	private TargetWall bugWall;
	private Aircraft aircraft;
	private ArrayList<Bullet> bullets = new ArrayList<>();

	// Game Components
	private Score score;
	private Group root;
	private WindowDimensions window;
	private CollisionHandler collisions;
	private int lives = 3;
	private Level level;
	private GameScreen ui;


	public GalagaEnvironment(Level level, Group root, GameScreen ui, Score score, WindowDimensions window) {
	    this.root = root;
	    this.level = level;
	    this.window = window;
	    this.score = score;
	    this.collisions = new Collisions();
	    this.ui = ui;
	    

	    setUpGameObjects();
	    addGamePiecesToScene();
	}
	
	
	/**
	 * method setUpGameObjects
	 * Initializes game pieces and creates them. 
	 */
	public void setUpGameObjects () {
		aircraft = new Aircraft();
		bugWall = new TargetWall(window);
//		bugWall.createBugWall();
		level.createBugWall(bugWall);
		aircraft.createController(window);
	}
	
	
	/**
	 * method addGamePiecesToScene
	 * Once objects have been created, add all of them to the scene so they are
	 * visible when playing game. 
	 */
	private void addGamePiecesToScene() {
	    Rectangle background = new Rectangle(window.getWindowWidth(), window.getWindowHeight());
	    background.setFill(javafx.scene.paint.Color.BLACK);
	    root.getChildren().add(background);
	    
		root.getChildren().add(aircraft.getController());
		for (Target bug : bugWall.getWall()) {
			root.getChildren().add(bug.getTarget());
		}
	}
	

	/**
	 * method resetEnvironment
	 * Resets the scene to its original state by first clearing everything from the scene
	 * and then recreating and re-adding game pieces to it.
	 */
	public void resetEnvironment() {
		root.getChildren().clear();
		setUpGameObjects();
		addGamePiecesToScene();
	}
	
	public int resetEnvironmentForNextLevel(Level nextLevel) {
	    root.getChildren().clear();
	    this.lives = 3; 
	    this.level = nextLevel;
	    setUpGameObjects();
	    addGamePiecesToScene();
	    ui.clearText();
	    return lives;
	}


	/**
	 * method checkAllCollisions
	 * Processes all bullet collisions and determines if the bullet hits a target
	 * If so, removes the bullet and triggers the next bug to drop (one bug always
	 * dropping). 
	 */
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
	
	
	// johnathan -- updated because Game had separate lives variable which caused things to be out of sync
	/**
	 * method handleLifeLost
	 * Detects if there was a collision with the aircraft and a bug. If so,
	 * decrement the lives
	 * @return true if a life is lost (collision occurred).
	 */
	public boolean handleLifeLost() {
	    if (collisions.aircraftBugCollision(aircraft, bugWall)) {
	        lives--;
	        return true;
	    }
	    return false;
	}

	
	/**
	 * method launchProjectile
	 * Transitions the ball from a static state to moving upwards. 
	 */
	public void launchProjectile() {
	    shootBullet();
	}


	/**
	 * method isWallEmpty
	 * returns true if the wall is empty.
	 */
	public boolean isWallEmpty() {
		return bugWall.getWall().isEmpty();
	}

	
	/**
	 * method increaseLives
	 * increments lives by one and returns update lives value. 
	 */
	public int increaseLives() {
		lives += 1;
		return lives;
	}
	
	
	/**
	 * method getLives
	 * return current number of lives
	 */
	public int getLives() {
	    return lives;
	}

	
	/** method triggerBugDrop
	 * send bug falling down. 
	 */
	public void triggerBugDrop() {
	    bugWall.initiateBugDrop();
	}

	
	// johnathan -- updated because Game had separate lives variable which caused things to be out of sync
	// so addded if statement to decrement lives 
	/**
	 * method moveDroppedBug
	 * Adds bugs that have fallen off the screen to a collection of missed bugs.
	 * The number of bugs that fall of the screen corresponds to the number of lives lost.
	 */
	public ArrayList<Target> moveDroppedBug(double elapsedTime) {
	    ArrayList<Target> bugsOutOfBounds = bugWall.updateFallingBugs(elapsedTime);
	    if (!bugsOutOfBounds.isEmpty()) {
	        lives -= bugsOutOfBounds.size(); 
	    }
	    return bugsOutOfBounds;
	}
	
	/**
	 * method getController
	 * @return Aircraft object.
	 */
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
	// Keeps all bullets that have been shot moving.
	// Removes bullets that have moved off screen.
    public void moveProjectiles(double elapsedTime) {
        for (Bullet bullet : bullets) {
            bullet.move(elapsedTime);
        }
        removeBullet();
    }
    
    
    //Katherine Hoadley
    // Removes bullets from list of all active bullets if it has moved past the top of
    // the screen. 
    public void removeBullet() {
    	for (int i = bullets.size() - 1; i >= 0; i--) {
            if (bullets.get(i).getProjectile().getCenterY() < 0) {
                root.getChildren().remove(bullets.get(i).getProjectile());
                bullets.remove(i);
            }
        }    
    }
}
