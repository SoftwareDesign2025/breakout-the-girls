//Katherine Hoadley
package application;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Aircraft extends UserControl{
	
	protected final int CONTROLLER_HEIGHT = 50;
	protected final int CONTROLLER_WIDTH = 30;

	public Aircraft() {
		controllerWidth = CONTROLLER_WIDTH;
		controller = new Rectangle(controllerWidth, CONTROLLER_HEIGHT);
		Image image = new Image(getClass().getResource("aircraft.png").toExternalForm());
		controller.setFill(new ImagePattern(image));
		controller.setStroke(null);
	}


}
