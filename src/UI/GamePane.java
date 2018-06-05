package UI;

import java.util.Vector;

import MVC.GameUIEventsListener;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class GamePane extends AnchorPane implements GameUI
{	
	private Vector<GameUIEventsListener> allListeners;
	private VisualApplication theApplication;
	private MissileDestructorPane missileDestructorPane; 
	private MissileLauncherPane missileLauncherPane;
	private MissileLauncherDestructorPane missileLauncherDestructorPane;
	
	
	public GamePane(VisualApplication theApplication) {
			
		this.theApplication = theApplication;
		
		this.prefHeightProperty().bind(theApplication.getPrimaryStage().getScene().heightProperty());
		this. prefWidthProperty().bind(theApplication.getPrimaryStage().getScene().widthProperty().subtract(theApplication.getMenu().widthProperty()));
		
		MissileLauncherView v = new MissileLauncherView("liran");
//		BorderPane.setAlignment(v, Pos.BOTTOM_LEFT);
		MissileLauncherView v1 = new MissileLauncherView("lddddddddiran");
//		BorderPane.setAlignment(v1, Pos.BOTTOM_LEFT);
		this.getChildren().addAll(v,v1);
		AnchorPane.setBottomAnchor(v,60.0);
		//AnchorPane.setTopAnchor(v,80.0);
		AnchorPane.setBottomAnchor(v1,50.0);
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
