package UI;

import MVC.GameUIEventsListener;

//import MVC.GameController;

public interface GameUI {
	
	static final String[] MENU_FUNCTIONS_NAMES = {"Add Missile Launcher","Add Missile Launcher Destructor","Add Missile Destructor",
			"Launch Missile","Destruct Missile Launcher","Destruct Missile","View Game Status","Exit"};
	void addMissileLauncher(String id);
	void addMissileLauncherDestructor(String type);
	void addMissileDestructor(String id);
	void launchMissile(String missileLauncherId, String missileId, String destination, int damage);
	void destructMissileLauncher(String missileLaucherDestructType, String missileLaucherDestructId);
	void destructMissile(String missileIdToDestruct, String missileDestructorId);
	void viewGameStatus();
	void exit();
	void registerListener(GameUIEventsListener listener);

	


}
