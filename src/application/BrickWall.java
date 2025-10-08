// Anna Rakes
// Store collection of bricks and position them into a wall.

import java.util.ArrayList;
import java.util.Random;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class BrickWall {
	
	private ArrayList<Brick> brickWall = new ArrayList<>();
    private Random random = new Random();
	private Paint[] colorList = {Color.LIGHTCORAL, Color.LAVENDERBLUSH, Color.LIGHTSTEELBLUE, Color.MOCCASIN, 
			Color.LIGHTGOLDENRODYELLOW,Color.DARKSEAGREEN};
	private int windowWidth;
	private int windowHeight;
	private int numberOfColumns;
	private int numberOfRows;
	private Brick emptyBrick = new Brick();

	
	public BrickWall(int windowWidth, int windowHeight) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		numberOfColumns = this.windowWidth/ emptyBrick.getBrickWidth();
		numberOfRows = (this.windowHeight/3) / emptyBrick.getBrickHeight();
	}

	
	public void createBrickWall() {
		for (int i = 0; i<numberOfRows ; i++) {
			for (int j = 0; j<numberOfColumns; j++) {
				double xCoordinate = j*emptyBrick.getBrickWidth();
				double yCoordinate = i*emptyBrick.getBrickHeight();
				Brick brick = new Brick(generateRandomBrickColor(), xCoordinate, yCoordinate);
				brick.createBrick();
				brickWall.add(brick);
			}
		}
	}
	
	private Paint generateRandomBrickColor () {
		Paint brickColor = colorList[random.nextInt(6)];
		return brickColor;
	}
	
	public ArrayList<Brick> getBrickWall () {
		return brickWall;
	}

}
