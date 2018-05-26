package Logic;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Vector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import MVC.GameModelEventsListener;
import jdk.nashorn.internal.parser.JSONParser;

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
	
	public void readDataFromConfigFile(String fileName){
        try(Reader reader = new InputStreamReader(Game.class.getResourceAsStream(fileName), "UTF-8")){
        	 Gson gson = new GsonBuilder().create();
             MissileLauncher p = gson.fromJson(reader, MissileLauncher.class);
             
             System.out.println(p);
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Game game = getInstance();
		long s = (System.nanoTime()/1000000000);

		MissileLauncher l101 = new MissileLauncher("L101",false,s);
		MissileLauncher l102 = new MissileLauncher("L102",true,s);
	
		HashMap<String, Integer> tod = new HashMap<>();
		tod.put("M1", 4);
		tod.put("M3", 8);
		tod.put("M4", 8);
		
		HashMap< Integer,MissileLauncher> r = new HashMap<>();
		r.put( 8,l102);
		r.put(12,l102);
		MissileLauncherDestructor ship = new MissileLauncherDestructor("shipA", r);
		HashMap< Integer,MissileLauncher> r2 = new HashMap<>();
		r2.put( 4,l101);
		MissileLauncherDestructor plane = new MissileLauncherDestructor("plane", r2);

		
		MissileDestructor D201 = new MissileDestructor("D201",tod);
		MissileDestructor D202 = new MissileDestructor("D202");
		
		l101.registerListener(D201);
		l102.registerListener(D201);

		Thread TD201 = new Thread(D201);
		//Thread TD202 = new Thread(D202);
		Thread TDship = new Thread(ship);
		Thread TDplane = new Thread(plane);

		Thread TL101 = new Thread(l101);
		Thread TL102 = new Thread(l102);
		TL101.start();
		TL102.start();
		TD201.start();
		TDship.start();
		TDplane.start();
		//TD202.start();
		
		
		Missile m1 = new Missile("M1", "Sderot", 12 ,2, 1500, l101,s);
		Missile m2 = new Missile("M2", "Beer-Sheva", 7, 5, 2000, l101,s);
		Missile m3 = new Missile("M3", "Ofakim", 4, 3, 5000, l102,s);
		Missile m4 = new Missile("M4", "Beer-Sheva", 7, 9, 1000, l102,s);
		l101.addMissile(m1);
		l101.addMissile(m2);

		l102.addMissile(m3);
		l102.addMissile(m4);
//		
		//
		// game.missileLaunchers.addElement(l101);
		// game.missileLaunchers.addElement(l102);

	}
}
