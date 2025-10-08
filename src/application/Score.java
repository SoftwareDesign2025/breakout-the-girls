import java.util.HashMap;

public class Score {
	private int currentScore;
	private int highScore;
	// TBD if this remains
	// ....
	private HashMap<String, Integer> scores;
	
	
	public void resetPoints() {
		this.currentScore = 0;
		this.highScore = 0;
	}
	
	/*
	 * returns an int
	 * TODO: verify why int needs to be returned (what if score was just a singleton?)
	 */
	public void addPoints(int points) {
		this.currentScore += points;
	}
	
	public void addPoints() {
		this.currentScore++;
	}
	
	
	//
	/*
	 * questions: how complicated should this be? will it create a 
	 * 			  text file to store high scores, and just pull from that?
	 * 			  or maybe just 
	 */
	public void saveHighScore() {
		
	}
	
	public Score loadHighScore() {
		
	}
	//
}
