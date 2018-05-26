package Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MissileDestructor implements MissileLaunchListener,Runnable {

	private String id;
	private Map<String,Integer> missilesToDestruct;
	private int currentWaitingTime;
	private Missile currentMissileToDestruct;
	public MissileDestructor(String id) {
		this.id = id;
		this.missilesToDestruct = new HashMap<String,Integer>();
	}

	public MissileDestructor(String id ,Map<String,Integer> missilesToDestruct ) {
		this.id = id;
		this.missilesToDestruct =missilesToDestruct;
	}

	public void addMissileToDestruct(String missileId,int destructAfterLaunch)
	{
		missilesToDestruct.put(missileId, destructAfterLaunch);
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
		System.out.println("In Missile Desturctor "+ id +" ::run");

		while(true){
			try {
				synchronized (this) {
					wait();
					new DestructTarget(currentMissileToDestruct, currentWaitingTime, id);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
