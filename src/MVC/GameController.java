package MVC;

import Logic.Game;
import UI.GameUI;

public class GameController implements GameModelEventsListener , GameUIEventsListener{

	private Game theGame;
	private GameUI theGameUI;	
	
	
	public GameController(Game theGame, GameUI theGameUI) {
		this.theGame = theGame;
		this.theGameUI = theGameUI;
		
		theGame.registerListener(this);
		theGameUI.registerListener(this);
	}
	@Override
	public void addMissileLauncherInModel() {
		//TODO how we going do it in UI

		theGameUI.showAddMissileLauncher();
		
	}

	@Override
	public void addMissileLauncherDestructorInModel() {
		//TODO how we going do it in UI

		theGameUI.showAddMissileLauncherDestructor();
		
	}

	@Override
	public void addMissileDestructorInModel() {
		//TODO how we going do it in UI
		theGameUI.showAddMissileDestructor();
		
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
	public void launchMissileInModel() {
		// TODO change it in UI
		
	}
	@Override
	public void destructMissileLauncherInModel() {
		// TODO change it in UI
		
		
	}
	@Override
	public void destructMissileInModel() {
		// TODO change it in UI
		
		
	}
	@Override
	public void launchMissileFromUI(String id) {
		//TODO every missile we need to decide destination , damage , fly time- randomly
		theGame.launchMissile(id);
		
	}
	@Override
	public void destructMissileLauncherFromUI(String missileLaucherIdToDestruct , String missileLaucherDestructId) {
		
		theGame.destructMissileLauncher(missileLaucherIdToDestruct ,missileLaucherDestructId);
		
	}
	@Override
	public void destructMissileFromUI(String missileIdToDestruct , String missileLaucherId) {
		theGame.destructMissile(missileIdToDestruct,missileLaucherId);
		
	}
	

}
