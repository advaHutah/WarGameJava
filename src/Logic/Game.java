package Logic;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import Logger.GameLogger;
import MVC.GameModelEventsListener;

public class Game implements MissileLaunchListener,LauncherDestructListener {

	private HashMap<String, MissileLauncher> missileLaunchers;
	private HashMap<String, MissileDestructor> missileDestructors;
	private HashMap<String, MissileLauncherDestructor> missileLauncherDestructors;
	private Vector<GameModelEventsListener> allListeners;
	private static final int RANDOM_BOUND = 10;

	// singleton object
	private static Game theGame;

	private Game() {
		allListeners = new Vector<GameModelEventsListener>();

		missileLaunchers = new HashMap<String, MissileLauncher>();
		missileDestructors = new HashMap<String, MissileDestructor>();
		missileLauncherDestructors = new HashMap<String, MissileLauncherDestructor>();
		GameLogger.addFileHandler(this, "game");
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
	
	public void addMissileLauncherFromConfig(MissileLauncher missileLauncher) {
		try {
			
			missileLaunchers.put(missileLauncher.getId(), missileLauncher);
			missileLauncher.registerListener(theGame);

			fireAddMissileLauncher(missileLauncher.getId());
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncher(e.getMessage());
		}
	}
	public void addMissileLauncher(String id) {
		try {
			MissileLauncher newml = new MissileLauncher(id);
			missileLaunchers.put(id, newml);
			newml.registerListener(theGame);
			Thread newmlT = new Thread(newml);
			newmlT.start();
			fireAddMissileLauncher(id);
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncher(e.getMessage());
		}
	}

	public void addMissileDestructorFromConfig(MissileDestructor destructor) {
		try {
			missileDestructors.put(destructor.getId(), destructor);
			for (MissileLauncher launcher : missileLaunchers.values()) {
				launcher.registerListener(destructor);
			}
			fireAddMissileDestructor(destructor.getId());
		} catch (Exception e) {
			fireNotificationFailedAddMissileDestructor(e.getMessage());
		}
	}
	
	
	
	public void addMissileDestructor(String id) {
		try {
			MissileDestructor newmd = new MissileDestructor(id);
			missileDestructors.put(id, newmd);
			Thread newmdT = new Thread(newmd);
			newmdT.start();
			fireAddMissileDestructor(id);
		} catch (Exception e) {
			fireNotificationFailedAddMissileDestructor(e.getMessage());
		}
	}
	
	public void addMissileLauncherDestructorFromConfig(MissileLauncherDestructor missileLauncherDestructor) {
		try {
			missileLauncherDestructors.put(missileLauncherDestructor.getType(), missileLauncherDestructor);
			missileLauncherDestructor.registerListener(theGame);
			fireAddMissileLauncherDestructor(missileLauncherDestructor.getType());
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncherDestructor(e.getMessage());
		}
	}

	public void addMissileLauncherDestructor(String type) {
		try {
			MissileLauncherDestructor newmld = new MissileLauncherDestructor(type);
			newmld.registerListener(theGame);
			missileLauncherDestructors.put(type, newmld);
			Thread newmldT = new Thread(newmld);
			newmldT.start();
			fireAddMissileLauncherDestructor(type);
		} catch (Exception e) {
			fireNotificationFailedAddMissileLauncherDestructor(e.getMessage());
		}
	}
	
	public void startAllObjects(){
		for (MissileLauncher launcher : missileLaunchers.values()) {
			Thread newmlT = new Thread(launcher);
			newmlT.start();
		}
		
		for (MissileDestructor destructor : missileDestructors.values()) {
			Thread newmD = new Thread(destructor);
			newmD.start();
		}
		for (MissileLauncherDestructor launcherDestructor : missileLauncherDestructors.values()) {
			Thread newmlD = new Thread(launcherDestructor);
			newmlD.start();
		}
	}

	private void fireAddMissileLauncher(String id) {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileLauncherInModel(id);
		}
	}

	private void fireAddMissileDestructor(String id) {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileDestructorInModel(id);
		}

	}

	private void fireAddMissileLauncherDestructor(String id) {
		for (GameModelEventsListener g : allListeners) {
			g.addMissileLauncherDestructorInModel(id);
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
	
	private void fireMissileLaunch(Missile missile) {
		for (GameModelEventsListener g : allListeners) {
			 g.launchMissileInModel(missile.getTheLauncher().getId(),missile.getMissileId(),missile.getDestination(),missile.getDamage(),missile.getFlyTime());
		}
	}
	
	private void fireDestructMissileLauncher(LauncherDestructTarget target) {
		for (GameModelEventsListener g : allListeners) {
			 g.destructMissileLauncherInModel(target.getType(),target.getTargetID());
		}
	}

	public void launchMissile(String missileLauncherId, String missileId, String destination, int damage)
			throws InterruptedException {
		Random r = new Random();
		Missile newMissile = new Missile(missileId, destination, r.nextInt(RANDOM_BOUND), 0, damage,
				missileLaunchers.get(missileLauncherId));
		missileLaunchers.get(missileLauncherId).addMissile(newMissile);
	}

	public void destructMissileLauncher(String missileLaucherDestructType, String missileLaucherDestructId)
			throws InterruptedException {
		missileLauncherDestructors.get(missileLaucherDestructType)
				.addlauncherToDestruct(missileLaunchers.get(missileLaucherDestructId), 0);

	}

	public void destructMissile(String missileIdToDestruct, String missileDestructorId) {
		Random r = new Random();
		missileDestructors.get(missileDestructorId).addMissileToDestruct(missileIdToDestruct, r.nextInt(RANDOM_BOUND));
	}

	public void showTotalSumarry() {
		int hitMissile = 0;
		int launchMissileCounter = 0;
		int totalDamage = 0;
		int missileLauncherDestroy = 0;

		for (MissileLauncher missileLauncher : missileLaunchers.values()) {
			// how many missile hit
			Vector<Missile> missiles = missileLauncher.getMissilesToLaunch();
			for (Missile m : missiles) {
				if (m.isHitTarget()) {
					hitMissile++;
					// total damage
					totalDamage += m.getDamage();
				}
			}
			// how many missile destroy
			if (missileLauncher.isDestroyed())
				missileLauncherDestroy++;
			// how many missile launch
			launchMissileCounter += missileLauncher.getLaunchedMissileCounter();

		}
		printTotalSumarry(hitMissile, launchMissileCounter, totalDamage, missileLauncherDestroy);

	}

	private void printTotalSumarry(int hitMissile, int launchMissileCounter, int totalDamage,
			int missileLauncherDestroy) {
		System.out.println("number of missile hits: " + hitMissile);
		System.out.println("number of missile that were launched: " + launchMissileCounter);
		System.out.println("total damage: " + totalDamage);
		System.out.println("number od missile launcher destroy destroyed: " + missileLauncherDestroy);

	}

	public void exit() {
		// todo
	}

	@Override
	public void onLaunchEvent(Missile launchedMissile) {
		fireMissileLaunch(launchedMissile);
		
	}

	@Override
	public void onLaunchEvent(LauncherDestructTarget target) {
		fireDestructMissileLauncher(target);
		
	}
}
