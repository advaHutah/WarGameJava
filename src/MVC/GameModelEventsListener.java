package MVC;

public interface GameModelEventsListener {

	void addMissileLauncherInModel(String id);

	void addMissileLauncherDestructorInModel(String id);

	void addMissileDestructorInModel(String id);

	void launchMissileInModel(String missileLauncherId, String missileId, String destination, int damage,int flyTime);

	void destructMissileLauncherInModel(String type,String missileLauncherId);

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
