// Johnathan


//	FIXME (johnathan):
/*
 * 	* Lots of for loops with pretty similar structures, but different bodies
 * 	  -- so investigate ways to condense it into a helper function so we have one for loop instead of 3 or 4
 *
 */


package application;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Screen {
	protected final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8);
	protected final Color TEXT_COLOR = Color.WHITE;
	
	protected Group root;
	protected Rectangle backgroundOverlay;
	protected WindowDimensions window;
	
	
	public Screen(Group root, WindowDimensions window) {
		this.root = root;
		this.window = window;	
	}
	
	
	/**
	 * Clears the content of the provided text elements (sets text to "")
	 */
	private void clearText(List<Text> textElements) {
		for (Text text : textElements) {
			if (text != null) {
				text.setText("");
			}
		}
	}
	
	
	public abstract void hide();
	public abstract void display();
	public abstract void clearText();
	
	
	protected void configureOverlay(Color color) {
	    if (backgroundOverlay == null) {
	        backgroundOverlay = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight());
	        backgroundOverlay.setFill(color);
	        
	        if (!root.getChildren().contains(backgroundOverlay)) {
	            root.getChildren().add(backgroundOverlay);
	        }
	    } 
	    else {
	        backgroundOverlay.setWidth(window.getWindowWidth());
	        backgroundOverlay.setHeight(window.getWindowHeight());
	        backgroundOverlay.setFill(color);
	        
	        if (!root.getChildren().contains(backgroundOverlay)) {
	            root.getChildren().add(backgroundOverlay);
	        }
	    }
	}
	
	protected void showOverlay() {
		if (backgroundOverlay != null) {
			backgroundOverlay.toFront();
		}
	}
	
	/**
	 * Configures title text and adds it to the root.
	 */
	/*
	 * TODO Need to make another method like this but separate a "title message" from a generic text box
	 * (i.e. still need to implement permanent score screen while playing the game so you can see live score changes)
	 * then rename to configureTitle
	 */
	protected void configureText(Text text, String content, double x, double y, 
								 Font font, Color color) {
		text.setText(content);
		text.setFont(font);
		text.setFill(color);
		text.setX(getCenteredX(text));
		text.setY(y);
		
		if (!root.getChildren().contains(text)) {
			root.getChildren().add(text);
		}
		
		text.toFront();
	}
	
	/**
	 * Calculates the X position to center text horizontally.
	 */
	protected double getCenteredX(Text text) {
		return (window.getWindowWidth() - text.getLayoutBounds().getWidth()) / 2;
	}
	
	/**
	 * Adds UI elements to the scene. Accepts JavaFX objects such as Text, Circles, and Rectangles.
	 */
	protected void addUIElements(List<Node> elements) {
	    for (Node element : elements) {
	    	boolean doesElementExist = root.getChildren().contains(element);
	    	
	        if (element != null && !doesElementExist) {
	            root.getChildren().add(element);
	        }
	    }
	}

	/**
	 * Removes any UI elements from the root.
	 */
	protected void removeUIElements(List<Node> elements) {
	    for (Node element : elements) {
	        if (element != null) {
	            root.getChildren().remove(element);
	        }
	    }
	}
	
	protected void initializeText(List<Text> textElements) {
		clearText(textElements);
		
		for (Text text : textElements) {
			boolean doesTextExist = root.getChildren().contains(text);
					
			if (text != null && !doesTextExist) {
				root.getChildren().add(text);
			}
		}
	}
}
