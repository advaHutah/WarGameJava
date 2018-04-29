package MVC;

public interface GameModelEventsListener {

	void addMissileLauncherInModel();

	void addMissileLauncherDestructorInModel();

	void addMissileDestructorInModel();

	void launchMissileInModel();

	void destructMissileLauncherInModel();

	void destructMissileInModel();

//	void notifyFailedAddMissileLauncherInModel(String message);
//
//	void notifyFailedAddMissileLauncherDestructorInModel(String message);
//
//	void notifyFailedAddMissileDestructorInModel(String message);
//
//	void notifyFaildLaunchMissileInModel(String massage);
//
//	void notifyFaildDestructMissileLauncherInModel(String massage);
//
//	void notifyFaildDestructMissileInModel(String massage);

}
