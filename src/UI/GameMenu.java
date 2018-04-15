package UI;

import java.awt.MenuBar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameMenu extends BorderPane {


	public GameMenu() {
		Menu menu = new Menu();
		menu.getItems().addAll(
				Stream.of("Add Missile Launcher", "Add Missile Launcher Destructor", "Add Missile Destructor",
						"Launch Missile","Destruct Missile Launcher Destructor", "Destruct Missile",
						"Destruct Missile Launcher", "Game Status", "Close")
                    .map(MenuItem::new).collect(Collectors.toList()));
       
	}
}
