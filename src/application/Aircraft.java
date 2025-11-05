//Katherine Hoadley
package application;

import javafx.scene.shape.Rectangle;

public class Aircraft extends UserControl{
	
	protected final int CONTROLLER_HEIGHT = 50;
	protected final int CONTROLLER_WIDTH = 30;

	public Aircraft() {
		controller = new Rectangle(CONTROLLER_WIDTH, CONTROLLER_HEIGHT);
	}


}
