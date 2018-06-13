package Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import Logger.GameLogger;

public class MissileDestructor implements MissileLaunchListener,Runnable {

	private String id;
	private Map<String,Integer> missilesToDestruct;
	private int currentWaitingTime;
	private Missile currentMissileToDestruct;
	private Vector<MissileDestructListener> listeners;
	
	public MissileDestructor(String id) {
		this.id = id;
		this.missilesToDestruct = new HashMap<String,Integer>();
		this.listeners = new Vector<MissileDestructListener>();

		GameLogger.addFileHandler(this, id);
	}

	

	public void addMissileToDestruct(String missileId,int destructAfterLaunch)
	{
		missilesToDestruct.put(missileId, destructAfterLaunch);
	}
	public void setMissilesToDestruct(Map<String, Integer> missilesToDestruct) {
		this.missilesToDestruct = missilesToDestruct;
	}
	public String getId() {
		return id;
	}
	
	public void registerListener(MissileDestructListener newListener) {
		listeners.add(newListener);
	}

	public void notifyAllListener(DestructTarget target) {
		int size = listeners.size();
		for (int i = 0; i < size; i++)
			listeners.elementAt(i).onLaunchEvent(target);
	}

	@Override
	public void onLaunchEvent(Missile launchedMissile) {
		if(missilesToDestruct.containsKey(launchedMissile.getMissileId()))
		{
			synchronized (this) {
				currentMissileToDestruct =launchedMissile;
				currentWaitingTime = missilesToDestruct.get(launchedMissile.getMissileId());
				notify();
			}
		}
	}
	@Override
	public void onLandEvent(Missile landMissile) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run() {
		GameLogger.log(this, Level.INFO, "In Missile Desturctor "+ id +" ::run");

		while(true){
			try {
				synchronized (this) {
					wait();
					DestructTarget target = new DestructTarget(currentMissileToDestruct, currentWaitingTime, this);
					notifyAllListener(target);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



	
}
