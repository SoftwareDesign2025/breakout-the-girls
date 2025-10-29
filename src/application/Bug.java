package application;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bug extends Target {

	@Override
	public void createTarget(int brickHeight, int brickWidth) {
		target = new Rectangle();		
		double xCoordinate = windowWidth/2;
		double yCoordinate = windowHeight * 0.8;
		
		projectile.setCenterX(xCoordinate);
		projectile.setCenterY(yCoordinate);
		projectile.setRadius(RADIUS);
		projectile.setFill(Color.BLACK);
		
		ballVelocity = new Point2D(0,0); // how many pixels over and down it moves by
	}

}
