//Johnathan Meeks
package application;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainScreen {
	private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 0.8); // COLOR: BLACK / 80% OPACITY
	private static final Color TEXT_COLOR = Color.WHITE;
	private static final int TITLE_FONT_SIZE = 70;
	private static final int BUTTON_FONT_SIZE = 30;
	private static final String GAME_TITLE = "BREAKOUT";
	private static final String START_BUTTON_TEXT = "START";

	private Group root;
	private int windowWidth;
	private int windowHeight;
	private Text titleText;
	private Button startButton;
	private Rectangle backgroundOverlay;


	private void initializeStartButton() {
		startButton = new Button(START_BUTTON_TEXT);
		startButton.setFont(new Font(BUTTON_FONT_SIZE));
		startButton.setLayoutX((windowWidth - startButton.getWidth()) / 2);
		startButton.setLayoutY(windowHeight / 1.8);
	}

	private void initializeTitleText() {
		titleText = new Text(GAME_TITLE);
		titleText.setFont(new Font(TITLE_FONT_SIZE));
		titleText.setFill(TEXT_COLOR);
		titleText.setX((windowWidth - titleText.getLayoutBounds().getWidth()) / 2);
		titleText.setY(windowHeight / 3.0);
	}

	private void initializeElements() {
		backgroundOverlay = new Rectangle(0, 0, windowWidth, windowHeight);
		backgroundOverlay.setFill(OVERLAY_COLOR);

		initializeTitleText();
		initializeStartButton();

		startButton.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
			startButton.setLayoutX((windowWidth - newVal.getWidth()) / 2);
		});
	}


	public MainScreen(Group root, int windowWidth, int windowHeight) {
		this.root = root;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		initializeElements();
		this.display();
	}


	public void display() {
		if (!root.getChildren().contains(backgroundOverlay)) {
			root.getChildren().add(backgroundOverlay);
		}
		if (!root.getChildren().contains(titleText)) {
			root.getChildren().add(titleText);
		}
		if (!root.getChildren().contains(startButton)) {
			root.getChildren().add(startButton);
		}
	}

	public void hide() {
		root.getChildren().remove(backgroundOverlay);
		root.getChildren().remove(titleText);
		root.getChildren().remove(startButton);
	}

	public Button getStartButton() {
		return startButton;
	}
}
