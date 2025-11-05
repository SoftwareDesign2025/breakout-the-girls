//Katherine Hoadley
package application;

import javafx.scene.shape.Rectangle;

public class Aircraft extends UserControl{
	
	protected final int CONTROLLER_HEIGHT = 50;
	protected final int CONTROLLER_WIDTH = 30;

	public Aircraft() {
		controllerWidth = CONTROLLER_WIDTH;
		controller = new Rectangle(controllerWidth, CONTROLLER_HEIGHT);
	}


}
