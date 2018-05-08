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
//			missileLaunchers.add(new MissileLauncher(id));
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

	public static void main(String[] args) throws InterruptedException {
		Game game = getInstance();
		long s = (System.nanoTime()/1000000000);

		MissileLauncher l101 = new MissileLauncher("L101");
		MissileLauncher l102 = new MissileLauncher("L102");
		Thread TL101 = new Thread(l101);
		Thread TL102 = new Thread(l102);
		TL101.start();
//		TL102.start();

		Missile m1 = new Missile("M1", "Sderot", 12 ,2, 1500, l101,s);
		Missile m2 = new Missile("M2", "Beer-Sheva", 7, 5, 2000, l101,s);
		Missile m3 = new Missile("M3", "Ofakim", 4, 3, 5000, l102,s);
		Missile m4 = new Missile("M4", "Beer-Sheva", 9, 7, 1000, l102,s);
		l101.addMissile(m1);
		l101.addMissile(m2);
//		l102.addMissile(m3);
//		l102.addMissile(m4);
		
		m1.join();
		m2.join();
		m3.join();
		m4.join();

		//
		// game.missileLaunchers.addElement(l101);
		// game.missileLaunchers.addElement(l102);

	}
}
