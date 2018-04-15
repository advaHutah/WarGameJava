package UI;

import java.io.File;
import java.io.FileInputStream;

import Util.CloseApplicationUtil;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameApplication extends Application {

	private Stage primaryStage;
	private GamePane mainPanel;

	public GamePane getMainPanel() {
		return mainPanel;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		BorderPane borderPane = new BorderPane();
//		Scene scene = new Scene(borderPane, 500, 400);
		FXMLLoader f = new FXMLLoader();
		Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File("war.fxml")));

		Scene scene = new Scene(fxmlRoot);
		
		//TODO change css
		scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        
		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("War Game");
	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				e.consume(); // to prevent the application from default closing
				CloseApplicationUtil.closeApplication(GameApplication.this);
			}
		});
		
//		mainPanel = new GamePane(this);
//		borderPane.setLeft(mainPanel);
//		
//		
//		GameMenu theMenu = new GameMenu();
//		borderPane.setLeft(theMenu);
		
        primaryStage.show();
       // new GameController(new Game("Tribe 1", "Tribe 2"), mainPanel);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
