package Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import Logger.GameLogger;

public class MissileDestructor implements MissileLaunchListener,Runnable {

	private String id;
	private Map<String,Integer> missilesToDestruct;
	private int currentWaitingTime;
	private Missile currentMissileToDestruct;
	public MissileDestructor(String id) {
		this.id = id;
		this.missilesToDestruct = new HashMap<String,Integer>();
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
	public void run() {
		GameLogger.log(this, Level.INFO, "In Missile Desturctor "+ id +" ::run");

		while(true){
			try {
				synchronized (this) {
					wait();
					new DestructTarget(currentMissileToDestruct, currentWaitingTime, this);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
