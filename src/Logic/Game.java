package Logic;

import java.util.Vector;

import MVC.GameModelEventsListener;

public class Game {

	private Vector<MissileLauncher> missileLaunchers;
	private Vector<MissileDestructor> missileDestructors;
	private Vector<MissileLauncherDestructor> missileLauncherDestructors;
	private Vector<GameModelEventsListener> allListeners;

	// singleton object
	private static Game theGame;

	private Game() {
		allListeners = new Vector<GameModelEventsListener>();

		missileLaunchers = new Vector<MissileLauncher>();
		missileDestructors = new Vector<MissileDestructor>();
		missileLauncherDestructors = new Vector<MissileLauncherDestructor>();
	}

	public static Game getInstance() {
		if (theGame == null) {
			// synchronized block to remove overhead
			synchronized (Game.class) {
				if (theGame == null) {
					// if theGame is null, initialize
					theGame = new Game();
				}

			}
		}
		return theGame;
	}


	public void registerListener(GameModelEventsListener listener) {
		allListeners.add(listener);
	}

	public void addMissileLauncher(String id) {
		try {
			missileLaunchers.add(new MissileLauncher(id));
			fireAddMissileLauncher();
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncher(e.getMessage());
		}
	}

	public void addMissileDestructor(String id) {
		try {
			missileDestructors.add(new MissileDestructor(id));
			fireAddMissileDestructor();
		} catch (Exception e) {
			fireNotificationFailedAddMissileDestructor(e.getMessage());
		}
	}

	public void addMissileLauncherDestructor(String type) {
		try {
			missileLauncherDestructors.add(new MissileLauncherDestructor(type));
			fireAddMissileLauncherDestructor();
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncherDestructor(e.getMessage());
		}
	}

	private void fireAddMissileLauncher() {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileLauncherInModel();
		}
	}

	private void fireAddMissileDestructor() {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileDestructorInModel();
		}

	}

	private void fireAddMissileLauncherDestructor() {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileLauncherDestructorInModel();
		}
	}

	private void fireNotificationFailedAddMissileLauncher(String message) {
		for (GameModelEventsListener g : allListeners) {
			// g.notifyFailedAddMissileLauncherInModel(message);
		}
	}

	private void fireNotificationFailedAddMissileLauncherDestructor(String message) {
		for (GameModelEventsListener g : allListeners) {
			// g.notifyFailedAddMissileLauncherDestructorInModel(message);
		}

	}

	private void fireNotificationFailedAddMissileDestructor(String message) {
		for (GameModelEventsListener g : allListeners) {
			// g.notifyFailedAddMissileDestructorInModel(message);
		}
	}

	public void launchMissile(String id) {
		// TODO with thread

	}

	public void destructMissileLauncher(String missileLaucherIdToDestruct, String missileLaucherDestructId) {
		// TODO with thread

	}

	public void destructMissile(String missileIdToDestruct, String missileLaucherId) {
		// TODO with thread

	}

}
