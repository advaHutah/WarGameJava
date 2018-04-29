package UI;

import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane
{	
	//private Vector<GameUIEventsListener> allListeners;
	private GameApplication theApplication;
	private MissileDestructorPane missileDestructorPane; 
	private MissileLauncherPane missileLauncherPane;
	private MissileLauncherDestructorPane missileLauncherDestructorPane;
	
	
	public GamePane(GameApplication theApplication) {
			
		this.theApplication = theApplication;
		
		this.prefHeightProperty().bind(theApplication.getPrimaryStage().getScene().heightProperty());
		this. prefWidthProperty().bind(theApplication.getPrimaryStage().getScene().widthProperty().subtract(theApplication.getMenu().widthProperty()));
		
		missileLauncherPane = new MissileLauncherPane();
		missileDestructorPane = new MissileDestructorPane();
		missileLauncherDestructorPane = new MissileLauncherDestructorPane();
		missileLauncherDestructorPane.setId("missileLauncherDestructorPane");
		missileLauncherDestructorPane.prefWidthProperty().bind(this.widthProperty());
		missileLauncherDestructorPane.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
		missileLauncherPane.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

		//set sub-Pane location
		this.setLeft(missileLauncherPane);
		this.setRight(missileDestructorPane);
		this.setTop(missileLauncherDestructorPane);
	}
}
