package application;


import javafx.scene.Group;

public class GalagaEnvironment extends Environment implements GameEnvironment {

	private TargetWall bugWall;
	private Aircraft aircraft;
	private Bullet bullet;
	private Score score;
	private Group root;
	private GameScreen ui;
	private int windowWidth;
	private int windowHeight;
	private Collisions collisions;
	private int lives = 3;


	public GalagaEnvironment(Group root, GameScreen ui, int windowWidth, int windowHeight, Score score) {
		this.root = root;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.score = new Score();
		this.ui = new GameScreen(root, windowWidth, windowHeight);
		this.collisions = new Collisions();
		this.score = score;

		initializeObjects();
	}

	public void initializeObjects () {
		aircraft = new Aircraft();
		bullet = new Bullet();
		bugWall.createTargetWall();

		aircraft.createController(windowWidth, windowHeight);
		bullet.createProjectile(windowWidth, windowHeight);

		root.getChildren().add(aircraft.getController());
		root.getChildren().add(bullet.getProjectile());
		for (Target bug : bugWall.getWall()) {
			root.getChildren().add(bug.getTarget());
		}
	}

	public void resetEnvironment() {
		root.getChildren().clear();
		initializeObjects();
	}



	public void checkAllCollisions() {
		collisions.aircraftBugCollision(aircraft, bugWall);
	}

	public void launchProjectile() {
		bullet.launchProjectile();
	}

	public void moveProjectile(double elapsedTime) {
		bullet.move(elapsedTime);
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
	public boolean isBallLost() {
		return false;
	}

	@Override
	public void resetBallPosition() {		
	}

	public UserControl getController() {
		return aircraft;
	}
}
