package UI;

import Logic.Game;
import MVC.GameController;
import Util.CloseApplicationUtil;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VisualApplication extends Application {

	private Stage primaryStage;
	private BorderPane mainPanel;
	private GamePane gamePanel;
	private MenuBox menu;

	public BorderPane getMainPanel() {
		return mainPanel;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainPanel = new BorderPane();
	
		Scene scene = new Scene(mainPanel, 700, 600);
		//		FXMLLoader f = new FXMLLoader();
		//		Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File("war.fxml")));

		//		Scene scene = new Scene(fxmlRoot);

		//TODO change css
		this.primaryStage = primaryStage;
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				e.consume(); // to prevent the application from default closing
				CloseApplicationUtil.closeApplication(VisualApplication.this);
			}
		});
		menu = new MenuBox(this);
		gamePanel = new GamePane(this);
		
		primaryStage.setTitle("War Game");

		gamePanel.setId("game_pane");
		menu.setId("menu_box");
		
		mainPanel.setRight(menu);
		mainPanel.setLeft(gamePanel);

		menu.setAlignment(Pos.TOP_RIGHT);
		menu.getStyleClass().addAll("vbox","button");
		
		primaryStage.show();
		new GameController(Game.getInstance() , gamePanel);
	}
	public GamePane getGamePanel() {
		return gamePanel;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public MenuBox getMenu() {
		return menu;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
