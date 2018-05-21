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
		currentMissileToDestruct =launchedMissile;
		currentWaitingTime = missilesToDestruct.get(launchedMissile.getMissileId());

		synchronized (this) {
			notify();// 1 for missile to launch
		}


	}

	@Override
	public void run() {
		synchronized (this) {
			while(true){
				try {
					wait();//  1 for missile to launch
					System.out.println("Desturctor "+ id +" Launcher waits "+currentWaitingTime+ " for "+currentMissileToDestruct.getMissileId());
					Thread.sleep(currentWaitingTime*1000);
					System.out.println("Desturctor "+ id +" finish waiting to "+currentMissileToDestruct.getMissileId());
					if(currentWaitingTime< currentMissileToDestruct.getFlyTime() && !currentMissileToDestruct.isDestructed())
					{
						currentMissileToDestruct.setDestructed(true);
						currentMissileToDestruct.notify();
						System.out.println("Desturctor "+ id +" destoryed "+currentMissileToDestruct.getMissileId());
					}
					else
						System.out.println("Desturctor "+ id +" missed "+currentMissileToDestruct.getMissileId());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
