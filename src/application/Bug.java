package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bug extends Target {
	
	public Bug (double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}
	
	public Bug() {}
	
	

	@Override
	public void createTarget(double bugHeight, double bugWidth) {
		targetHeight = bugHeight;
		targetWidth = bugWidth;
		target = new Rectangle(bugWidth, bugHeight);	
		target.setFill(Color.BLUEVIOLET);
		target.setStroke(Color.BLACK);
		
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}
	
}
