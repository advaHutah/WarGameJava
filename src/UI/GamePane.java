package UI;

import java.util.Vector;

import MVC.GameUIEventsListener;
import javafx.geometry.Orientation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class GamePane extends GridPane implements GameUI
{	
	private Vector<GameUIEventsListener> allListeners;
	private GameApplication theApplication;
	private MissileDestructorPane missileDestructorPane; 
	private MissileLauncherPane missileLauncherPane;
	private MissileLauncherDestructorPane missileLauncherDestructorPane;
	
	
	public GamePane(GameApplication theApplication) {
			
		this.theApplication = theApplication;
		
		this.prefHeightProperty().bind(theApplication.getPrimaryStage().getScene().heightProperty());
		this. prefWidthProperty().bind(theApplication.getPrimaryStage().getScene().widthProperty().subtract(theApplication.getMenu().widthProperty()));
		

		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
			this.add(new MissileLauncherDestructorView("adva"), i, j);	
			}
		}
	}




	@Override
	public void registerListener(GameUIEventsListener listener) {
		allListeners.add(listener);

		
	}




	@Override
	public void addMissileLauncher(String id) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void addMissileLauncherDestructor(String type) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void addMissileDestructor(String id) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void launchMissile(String missileLauncherId, String missileId, String destination, int damage) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void destructMissileLauncher(String missileLaucherDestructType, String missileLaucherDestructId) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void destructMissile(String missileIdToDestruct, String missileDestructorId) {
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
