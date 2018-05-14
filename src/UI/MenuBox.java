package UI;

import java.util.Vector;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuBox extends VBox {

	private GameApplication theApplication;

	private Vector<Button> buttons = new Vector<>();

	public MenuBox(GameApplication theApplication) {
		this.theApplication = theApplication;

		for (int i = 0; i < GameUI.MENU_FUNCTIONS_NAMES.length; i++) {
			Button btn = new Button(GameUI.MENU_FUNCTIONS_NAMES[i]);
			buttons.addElement(btn);
			
		}
		for (Button button : buttons) {
			this.getChildren().add(button);
		}
	}

}