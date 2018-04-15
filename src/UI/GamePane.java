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
		//set sub-Pane location
//		this.setLeft(missileLauncherPane);
//		this.setRight(missileDestructorPane);
//		this.setTop(missileLauncherDestructorPane);
	}

}
