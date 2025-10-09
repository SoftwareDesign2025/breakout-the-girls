import java.util.HashMap;

public class Score {
	private int currentScore;
	private int highScore;
	private int WINNING_SCORE = 100;
	private int SCORE_DEFAULT_INCREMENT = 1;
	// TBD if this remains
	private HashMap<String, Integer> scores;
	
	
	public Score() {
		this.currentScore = 0;
		this.highScore = 0;
	}
	
	
	public void resetPoints() {
		this.currentScore = 0;
		this.highScore = 0;
	}
	
	public void addPoints(int points) {
		this.currentScore += points;
	}
	
	public void addPoints() {
		this.currentScore += SCORE_DEFAULT_INCREMENT;
	}
	
	public int getCurrentScore() {
		return this.currentScore;
	}
	
	public int getHighScore() {
		return this.highScore;
	}
	
	public int getWinningScore() {
		return this.WINNING_SCORE;
	}
	
	public void saveHighScore() {
		// take username: 
	}
	
	
}
