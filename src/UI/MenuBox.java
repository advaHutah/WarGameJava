package UI;

import java.awt.MenuBar;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuBox extends VBox implements Menu
{


	private GameApplication theApplication;
	
	private Vector<Button> buttons =new Vector<>();
	public MenuBox(GameApplication theApplication) {
		this.theApplication = theApplication;

		for(int i = 0 ; i< Menu.MENU_FUNCTIONS_NAMES.length ; i++)
		{
			Button btn =new Button(Menu.MENU_FUNCTIONS_NAMES[i]);
			buttons.addElement(btn);
		}
		for (Button button : buttons) {
			this.getChildren().add(button);
		}
	}

	@Override
	public void addMissileLauncher() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMissileLauncherDestructor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMissileDestructor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void launchMissile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destructMissileLauncher() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destructMissile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewGameStatus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}
}
