package MVC;

import Logic.Game;
import UI.GameUI;

public class GameController implements GameModelEventsListener, GameUIEventsListener {

	private Game theGame;
	private GameUI theGameUI;

	public GameController(Game theGame, GameUI theGameUI) {
		this.theGame = theGame;
		this.theGameUI = theGameUI;

		theGame.registerListener(this);
		theGameUI.registerListener(this);
	}

	@Override
	public void addMissileLauncherInModel(String id) {
		 theGameUI.showAddMissileLauncher(id);

	}

	@Override
	public void addMissileLauncherDestructorInModel(String type) {
		 theGameUI.showAddMissileLauncherDestructor(type);

	}

	@Override
	public void addMissileDestructorInModel(String id) {
		 theGameUI.showAddMissileDestructor(id);

	}

	@Override
	public void addMissileLauncherFromUI(String id) {
		theGame.addMissileLauncher(id);

	}

	@Override
	public void addMissileLauncherDestructorFromUI(String type) {
		theGame.addMissileLauncherDestructor(type);

	}

	@Override
	public void addMissileDestructorFromUI(String id) {
		theGame.addMissileDestructor(id);

	}

	@Override
	public 	void launchMissileInModel(String missileLauncherId, String missileId, String destination, int damage,int flytime) {
		theGameUI.showMissileLaunch(missileLauncherId, missileId, destination, damage, flytime);

	}

	@Override
	public void destructMissileLauncherInModel(String type,String missileLauncherId) {
		theGameUI.showDestructMissileLauncher(type,missileLauncherId);


	}

	@Override
	public void destructMissileInModel() {
		// TODO change it in UI

	}

	@Override
	public void launchMissileFromUI(String missileLauncherId, String missileId, String destination, int damage) {
		try {
			theGame.launchMissile(missileLauncherId, missileId, destination, damage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destructMissileLauncherFromUI(String missileLaucherDestructType, String missileLaucherDestructId) {
		try {
			theGame.destructMissileLauncher(missileLaucherDestructType, missileLaucherDestructId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destructMissileFromUI(String missileIdToDestruct, String missileDestructorId) {
		theGame.destructMissile(missileIdToDestruct, missileDestructorId);

	}

	@Override
	public void viewGameStatusFromUI() {
		theGame.showTotalSumarry();		
	}

}
