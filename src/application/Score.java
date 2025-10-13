

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Score {
	private int SCORE_DEFAULT_INCREMENT = 1;
    private static final String GAME_DATA_FOLDER_NAME = "BreakoutGameData";
    private static final String HIGH_SCORES_FILE_NAME = "HighScores.txt";
    private final Path HIGH_SCORES_PATH;
    private final String USERNAME = "Player1";
    
    private int currentScore;
    private int savedHighScore = 0;
    
	
	public Score() {
		this.currentScore = 0;
	    
	    String userHome = System.getProperty("user.home");
	    this.HIGH_SCORES_PATH = Paths.get(userHome, "Documents", GAME_DATA_FOLDER_NAME, HIGH_SCORES_FILE_NAME);
	    
	    try {
	        Files.createDirectories(this.HIGH_SCORES_PATH.getParent());
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    this.loadSavedHighScore();
	}
	
	
	public void askPlayerName() {
		// --> create a text box
		// --> ask the player their name 
	}
	
	public void resetPoints() {
		this.currentScore = 0;
	}
	
	public void addPoints(int points) {
		this.currentScore += points;
	}
	
	public void addPoints() {
		this.currentScore += SCORE_DEFAULT_INCREMENT;
	}
	
	// rename to getCurrentHighScore across codebase
	public int getHighScore() {
		return this.currentScore;
	}
	
	public void loadSavedHighScore() {
		try {
	        if (Files.exists(this.HIGH_SCORES_PATH)) {
	            String content = Files.readString(this.HIGH_SCORES_PATH).trim();
	            this.savedHighScore = Integer.parseInt(content);
	        }
	    } 
		catch (IOException | NumberFormatException e) {
	        System.err.println(e.getMessage());
	        this.savedHighScore = 0;
	    }	
	}
	
	public void saveHighScore() {
	    try {
	        Files.writeString(this.HIGH_SCORES_PATH, String.valueOf(this.currentScore));
	    } 
	    catch (IOException e) {
	        System.err.println(e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	
}
