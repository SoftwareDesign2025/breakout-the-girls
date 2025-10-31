package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bug extends Target {

	@Override
	public void createTarget(int bugHeight, int bugWidth) {
		targetHeight = bugHeight;
		targetWidth = bugWidth;
		target = new Rectangle(bugWidth, bugHeight);	
		target.setFill(Color.BLUEVIOLET);
		target.setStroke(Color.BLACK);
		
		target.setX(xCoordinate);
		target.setY(yCoordinate);
	}

}
