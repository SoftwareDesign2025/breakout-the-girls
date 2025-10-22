// Johnathan Meeks
package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Game {
    private final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8); // COLOR: BLACK / 80% OPACITY
    private final Color TEXT_COLOR = Color.WHITE;
    private final int TITLE_FONT_SIZE = 50;
    private final int SCORE_FONT_SIZE = 40;
    private final String WIN_MESSAGE = "YOU WIN!";
    private final String LOSE_MESSAGE = "GAME OVER!";
    private final String FINAL_SCORE_STRING = "Final score: ";
    private final double TITLE_Y_POSITION_FACTOR = 4.0;
    private final double SCORE_Y_POSITION_FACTOR = 2.0;
    private final int TOTAL_LEVELS = 3;
    
    private Environment environment;
    private Collisions checkCollision;
    // GAME
    private double elapsedTime = 1.0 / 60.0;
    private boolean isRunning = false;
    private boolean gameOver = false;
    private int lives = 3;
    private int roundsCompleted = 0;
    private int roundsWon = 0;
    // LEVELS 
    private Level levelOne;
    private Level levelTwo;
    private Level levelThree;
    // SCREENS
    private Text scoreText = new Text();
    private Text titleText = new Text();
    private Rectangle endGameBackground;
    private MainScreen mainScreen;

    
    private Level determineNextLevel() {
        if (roundsCompleted == 0) {
            return levelOne;
        }
        else if (roundsCompleted == 1) {
            return levelTwo;
        }
        else {
            return levelThree;
        }
    }

    private void handleBallLost() {
        if (environment.getBall().checkIfRoundLost()) {
            lives--;
            if (lives > 0) {
                environment.getBall().resetBallPosition(environment.getWindowWidth(), environment.getWindowHeight());
                isRunning = false;
            } 
            else {
                gameOver = true;
                endGame(false);
            }
        }
    }

    private void configureEndGameDisplayText(Text text, double yPosition, Font font) {
        text.setFont(font);
        text.setFill(TEXT_COLOR);
        text.setX(
            (environment.getWindowWidth() - text.getLayoutBounds().getWidth()) /
                2
        );
        text.setY(yPosition);

        if (!environment.getRoot().getChildren().contains(text)) {
            environment.getRoot().getChildren().add(text);
        }
    }
    
    //round-based logic (losing but continuing to next orund) is not 100% complete yet.
    private void endRound(boolean win) {
		isRunning = false;
		
		titleText.setX(environment.getWindowWidth()/2.0);
		titleText.setY(environment.getWindowHeight()/2.0);
		//constant-ify
		titleText.setFont(new Font(30));
		
		if (win) {
			//constant-ify
			titleText.setText("YOU WIN THE ROUND: Score" + environment.getScore().getCurrentScore());
			roundsWon++;
		}
		else {
			//constant-ify
			titleText.setText("YOU LOST THE ROUND!");
		}
		
		roundsCompleted++;
		
		if (roundsCompleted < TOTAL_LEVELS) {
			Level level = determineNextLevel();
			Group root = environment.getRoot();
			root.getChildren().clear();
			environment = new Environment(root, environment.getWindowWidth(), environment.getWindowHeight(), level);
			checkCollision = new Collisions(environment);
		} 
		else {
			endGame(roundsWon == TOTAL_LEVELS);
		}
    }

    private void endGame(boolean win) {
        isRunning = false;
   
        if (endGameBackground == null) {
            endGameBackground = new Rectangle(0, 0, environment.getWindowWidth(), environment.getWindowHeight());
            endGameBackground.setFill(OVERLAY_COLOR);
            environment.getRoot().getChildren().add(endGameBackground);
        } 
        else {
            endGameBackground.setFill(OVERLAY_COLOR);
        }
        
        String titleString;
        if (win) {
            titleString = WIN_MESSAGE;
        } 
        else {
            titleString = LOSE_MESSAGE;
        }

        titleText.setText(titleString);
        configureEndGameDisplayText(
            titleText,
            environment.getWindowHeight() / TITLE_Y_POSITION_FACTOR,
            new Font(TITLE_FONT_SIZE)
        );
        
        scoreText.setText(FINAL_SCORE_STRING + environment.getScore().getCurrentScore());
        configureEndGameDisplayText(
            scoreText,
            environment.getWindowHeight() / SCORE_Y_POSITION_FACTOR,
            new Font(SCORE_FONT_SIZE)
        );
    }

    
    public Game(Group root, int windowWidth, int windowHeight) {
        levelOne = new LevelOne(windowWidth, windowHeight);
        levelTwo = new LevelTwo(windowWidth, windowHeight);
        levelThree = new LevelThree(windowWidth, windowHeight);
        environment = new Environment(
            root,
            windowWidth,
            windowHeight,
            levelOne
        );
        checkCollision = new Collisions(environment);
        mainScreen = new MainScreen(root, windowWidth, windowHeight);

        mainScreen
            .getStartButton()
            .setOnAction(event -> {
                startGame();
            });
    }
    
    
    public void startRound() {
		environment.getBall().launchBall();
	}
    
    public void startGame() {
    	if (gameOver) {
    		return;
    	}
    	
        mainScreen.hide();
        isRunning = true;
        environment.resetEnvironment();
        environment.getBall().launchBall();
    }
    
    public void startAfterLifeLost() {
    	isRunning = true;
        environment.getBall().launchBall();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getLives() {
        return lives;
    }
    
    public void step() {
        if (!isRunning) {
            return;
        }
        
        environment.getBall().move(elapsedTime);
        
        /*
         * Check power up list size --> check collisions --> check size again after collisions
         * (because it deletes power ups during collisions)
         * -> if it's smaller increment lives
         * (this is the only way i've been able to get it to work, the paddlePowerUpCollisions() func doesn't 
         * connect to the instance variable so it never updates lives)
         */
        int powerUpCountBefore = environment.getPowerUps().size();
        checkCollision.checkAllCollisions();
        int powerUpCountAfter = environment.getPowerUps().size();
        if (powerUpCountAfter < powerUpCountBefore) {
            lives++;
        }
        
        handleBallLost();
        
        boolean hasRoundEnded = environment.getBrickWall().getBrickWall().isEmpty();
        if (hasRoundEnded) {
        	endRound(true);
        }
    }
}
	
	
	
	
	

	


