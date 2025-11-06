// Johnathan 
package application;


import java.util.List;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TitleScreen extends Screen {
    private static final int TITLE_FONT_SIZE = 70;
    private static final int INSTRUCTION_FONT_SIZE = 40;
    
    private Text titleText;
    private Text instructionsText;
    
    
    public TitleScreen(Group root, WindowDimensions window) {
        super(root, window);
        initializeElements();
        this.display();
    }
    
    
    
    private void initializeElements() {
        configureOverlay(OVERLAY_COLOR);
        
        titleText = new Text("ARCADE GAMES");
        titleText.setFont(new Font(TITLE_FONT_SIZE));
        titleText.setFill(TEXT_COLOR);
        titleText.setX(getCenteredX(titleText));
        titleText.setY(window.getWindowHeight() / 3.0);
        
        instructionsText = new Text("Press A for Breakout or B for Galaga");
        instructionsText.setFont(new Font(INSTRUCTION_FONT_SIZE));
        instructionsText.setFill(TEXT_COLOR);
        instructionsText.setX(getCenteredX(instructionsText));
        instructionsText.setY(window.getWindowHeight() / 1.5);
    }
    
    
    @Override
    public void display() {
        addUIElements(List.of(backgroundOverlay, titleText, instructionsText));
    }
    
    @Override
    public void hide() {
        removeUIElements(List.of(backgroundOverlay, titleText, instructionsText));
    }
    
    @Override
    public void clearText() {
        titleText.setText("");
        instructionsText.setText("");
    }
}