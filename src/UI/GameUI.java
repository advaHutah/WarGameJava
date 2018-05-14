package UI;

//import MVC.GameController;

public interface GameUI {
	
	static final String[] MENU_FUNCTIONS_NAMES = {"Add Missile Launcher","Add Missile Launcher Destructor","Add Missile Destructor",
			"Launch Missile","Destruct Missile Launcher","Destruct Missile","View Game Status","Exit"};
	void addMissileLauncher();
	void addMissileLauncherDestructor();
	void addMissileDestructor();
	void launchMissile();
	void destructMissileLauncher();
	void destructMissile();
	void viewGameStatus();
	void exit();
	


}
