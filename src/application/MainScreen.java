//Johnathan Meeks

package application;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainScreen extends Screen {
	private static final int TITLE_FONT_SIZE = 70;
	private static final int BUTTON_FONT_SIZE = 30;
	private static final String START_BUTTON_TEXT = "START";

	private String gameTitle;
	private Text titleText;
	private Button startButton;

	
	public MainScreen(Group root, int windowWidth, int windowHeight, String gameTitle) {
		super(root, windowWidth, windowHeight);
		initializeElements();
		this.gameTitle = gameTitle.toUpperCase();
		initializeElements();
		this.display();
	}

	
	private void initializeElements() {
		configureOverlay(OVERLAY_COLOR);
		
		initializeTitleText();
		initializeStartButton();

		startButton.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
			startButton.setLayoutX((windowWidth - newVal.getWidth()) / 2);
		});
	}
	
	
	private void initializeTitleText() {		
		titleText = new Text(gameTitle);
		titleText.setFont(new Font(TITLE_FONT_SIZE));
		titleText.setFill(TEXT_COLOR);
		titleText.setX(getCenteredX(titleText));
		titleText.setY(windowHeight / 3.0);
	}

	private void initializeStartButton() {
		startButton = new Button(START_BUTTON_TEXT);
		startButton.setFont(new Font(BUTTON_FONT_SIZE));
		startButton.setLayoutX((windowWidth - startButton.getWidth()) / 2);
		startButton.setLayoutY(windowHeight / 1.8);
	}
	
	public Button getStartButton() {
		return startButton;
	}
	
	@Override 
	public void clearText() {
		titleText.setText("");
	}
	
	@Override
	public void display() {
		addUIElements(List.of(backgroundOverlay, titleText, startButton));
	}

	@Override
	public void hide() {
		removeUIElements(List.of(backgroundOverlay, titleText, startButton));
	}
}