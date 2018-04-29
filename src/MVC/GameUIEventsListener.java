package MVC;

public interface GameUIEventsListener {

	void addMissileLauncherFromUI(String id);

	void addMissileLauncherDestructorFromUI(String type);

	void addMissileDestructorFromUI(String id);

	void launchMissileFromUI(String id);

	void destructMissileLauncherFromUI(String missileLaucherIdToDestruct , String missileLaucherDestructId);

	void destructMissileFromUI(String missileIdToDestruct , String missileLaucherId);

}
