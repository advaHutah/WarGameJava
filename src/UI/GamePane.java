package UI;

import java.util.Vector;

import javax.swing.JOptionPane;

import MVC.GameUIEventsListener;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePane extends AnchorPane implements GameUI {
	final static int NUM_OF_TREES = 6;
	final static int MAX_NUM_OF_MISSILE_LAUNCHER = 5;
	final static int MAX_NUM_OF_MISSILE_LAUNCHER_D_PLANE = 6;
	final static int MAX_NUM_OF_MISSILE_LAUNCHER_D_SHIP = 6;
	final static int MAX_NUM_OF_MISSILE_DESTRUCTOR = 5;

	private Vector<GameUIEventsListener> allListeners;
	private VisualApplication theApplication;
	private VBox missileLauncherVBox;
	private VBox missileDestructorVBox;
	private HBox missileLauncherDestructorPlaneHBox;
	private HBox missileLauncherDestructorShipHBox;
	private VBox treeVBox;
	private int numOfMissileL = 0;
	private int numOfMissileD = 0;
	private int numOfMissileLD_Plane = 0;
	private int numOfMissileLD_Ship = 0;

	// private MissileDestructorPane missileDestructorPane;
	// private MissileLauncherPane missileLauncherPane;
	// private MissileLauncherDestructorPane missileLauncherDestructorPane;

	public GamePane(VisualApplication theApplication) {

		this.theApplication = theApplication;
		this.allListeners = new Vector<GameUIEventsListener>();
		this.prefHeightProperty().bind(theApplication.getPrimaryStage().getScene().heightProperty());
		this.prefWidthProperty().bind(theApplication.getPrimaryStage().getScene().widthProperty()
				.subtract(theApplication.getMenu().widthProperty()));

		// create vBox for missileLaunchers - on left size
		this.missileLauncherVBox = new VBox();
		vboxDesign(missileLauncherVBox);

		// create vBox for missileDestructor on right
		missileDestructorVBox = new VBox();
		vboxDesign(missileDestructorVBox);

		// create hBox for missileLauncherDestructorPlaneHBox on top
		missileLauncherDestructorPlaneHBox = new HBox();
		hboxDesign(missileLauncherDestructorPlaneHBox);

		// create hBox for missileLauncherDestructorShipHBox on top
		missileLauncherDestructorShipHBox = new HBox();
		hboxDesign(missileLauncherDestructorShipHBox);

		// addMissileLauncherDestructor("planeA");
		// addMissileLauncherDestructor("planeb");
		// addMissileLauncherDestructor("ship");
		// //addMissileLauncherDestructor("vv");
		//
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		// addMissileLauncher("a");
		//
		// addMissileDestructor("b");
		// addMissileDestructor("b");
		// addMissileDestructor("b");
		// addMissileDestructor("b");
		// addMissileDestructor("b");
		// addMissileDestructor("b");

		// create vBox for trees in the middle
		treeVBox = new VBox();
		vboxDesign(treeVBox);
		createTrees(treeVBox);

		// set location for all children
		setLocationViews();

		this.getChildren().addAll(missileLauncherVBox, missileDestructorVBox, treeVBox,
				missileLauncherDestructorPlaneHBox, missileLauncherDestructorShipHBox);

	}

	public void vboxDesign(VBox box) {
		box.setPadding(new Insets(10));
		box.setSpacing(8);

	}

	public void hboxDesign(HBox box) {
		box.setPadding(new Insets(10));
		box.setSpacing(8);

	}

	private void setLocationViews() {
		AnchorPane.setBottomAnchor(missileLauncherVBox, 60.0);
		AnchorPane.setLeftAnchor(missileLauncherVBox, 10.0);
		AnchorPane.setRightAnchor(missileDestructorVBox, 10.0);
		AnchorPane.setBottomAnchor(missileDestructorVBox, 60.0);
		AnchorPane.setRightAnchor(treeVBox, 200.0);
		AnchorPane.setBottomAnchor(treeVBox, 60.0);
		AnchorPane.setTopAnchor(missileLauncherDestructorPlaneHBox, 10.0);
		AnchorPane.setLeftAnchor(missileLauncherDestructorPlaneHBox, 10.0);
		AnchorPane.setTopAnchor(missileLauncherDestructorShipHBox, 120.0);
		AnchorPane.setLeftAnchor(missileLauncherDestructorShipHBox, 10.0);
	}

	private void createTrees(VBox treeVbox) {

		for (int i = 0; i < NUM_OF_TREES; i++) {
			TreeView t = new TreeView();
			treeVbox.getChildren().add(t);
		}

	}

	@Override
	public void registerListener(GameUIEventsListener listener) {
		allListeners.add(listener);

	}

	@Override
	public void addMissileLauncher(String id) {
		for (GameUIEventsListener l : allListeners)
			l.addMissileLauncherFromUI(id);
		// if (numOfMissileL < MAX_NUM_OF_MISSILE_LAUNCHER) {
		// MissileLauncherView m = new MissileLauncherView(id);
		// missileLauncherVBox.getChildren().add(m);
		// numOfMissileL++;
		// } else {
		// JOptionPane.showMessageDialog(null, "too many missile launcher", "InfoBox: "
		// + "Error", JOptionPane.INFORMATION_MESSAGE);
		// }

	}

	public void showAddMissileLauncher(String id) {
		MissileLauncherView m = new MissileLauncherView(id);
		missileLauncherVBox.getChildren().add(m);
	}

	@Override
	public void addMissileLauncherDestructor(String type) {
		if (type.contains("plane") || type.contains("ship"))
			for (GameUIEventsListener l : allListeners)
				l.addMissileLauncherDestructorFromUI(type);
		else {
			JOptionPane.showMessageDialog(null, "type must be ship/plane", "InfoBox: " + "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	@Override
	public void showAddMissileLauncherDestructor(String type) {
		if (type.contains("plane")) {
			MissileLauncherDestructorView mLd = new MissileLauncherDestructorView(type, "plane");
			missileLauncherDestructorPlaneHBox.getChildren().add(mLd);
		} else if (type.contains("ship")) {
			MissileLauncherDestructorView mLd = new MissileLauncherDestructorView(type, "ship");
			missileLauncherDestructorShipHBox.getChildren().add(mLd);
		}

	}

	@Override
	public void addMissileDestructor(String id) {
		for (GameUIEventsListener l : allListeners)
			l.addMissileDestructorFromUI(id);
	}
		//if (numOfMissileD < MAX_NUM_OF_MISSILE_DESTRUCTOR) {
//			MissileDestructorView md = new MissileDestructorView("");
//			missileDestructorVBox.getChildren().add(md);
//		//	numOfMissileD++;
//		} else {
//			JOptionPane.showMessageDialog(null, "too many missile destructor", "InfoBox: " + "Error",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
//	}
	
	@Override
	public void showAddMissileDestructor(String id) {
		MissileDestructorView md = new MissileDestructorView(id);
		missileDestructorVBox.getChildren().add(md);

	}

	@Override
	public void launchMissile(String missileLauncherId, String missileId, String destination, int damage) {
		for (GameUIEventsListener l : allListeners)
			l.launchMissileFromUI(missileLauncherId, missileId, destination, damage);

	}

	@Override
	public void destructMissileLauncher(String missileLaucherDestructType, String missileLaucherDestructId) {
		for (GameUIEventsListener l : allListeners)
			l.destructMissileLauncherFromUI(missileLaucherDestructType, missileLaucherDestructId);

	}

	@Override
	public void destructMissile(String missileIdToDestruct, String missileDestructorId) {
		for (GameUIEventsListener l : allListeners)
			l.destructMissileFromUI(missileIdToDestruct, missileDestructorId);

	}

	@Override
	public void viewGameStatus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMissileLaunch(String missileLauncherId, String missileId, String destination, int damage,
			int flytime) {
		JOptionPane.showMessageDialog(null, "too many missile destructor", "InfoBox: " + "Error",
		JOptionPane.INFORMATION_MESSAGE);
		MissileView missile = new MissileView(missileId);
		
		this.getChildren().add(missile);
		missile.MissileAnimation(flytime,(MissileLauncherView) missileLauncherVBox.getChildren().get(0));
		
	}

	@Override
	public void showDestructMissileLauncher(String type, String missileLauncherId) {
		JOptionPane.showMessageDialog(null, "too many missile destructor", "InfoBox: " + "Error",
				JOptionPane.INFORMATION_MESSAGE);
		
	}
}
