package Logic;

import java.util.logging.Level;

import Logger.GameLogger;

public class DestructTarget extends Thread {
	private Missile target;
	private int waitingTime;
	private MissileDestructor destructor;


	public DestructTarget(Missile target,int waitingTime,MissileDestructor destructor) {
		this.target = target;
		this.waitingTime=waitingTime;
		this.destructor=destructor;
		this.start();
	}

	@Override
	public void run() {
		try {
			GameLogger.log(destructor, Level.INFO, "Desturctor "+ destructor.getId() +" Launcher waits "+waitingTime+ " for "+target.getMissileId());
			Thread.sleep(waitingTime*1000);

			GameLogger.log(destructor, Level.INFO, "Desturctor "+ destructor.getId() +" finish waiting to "+target.getMissileId());

			if(waitingTime< target.getFlyTime() && !target.isDestructed())
			{
				synchronized (target) {
					target.setDestructed(true);
					target.notify();

				}
				GameLogger.log(destructor, Level.INFO, "Desturctor "+ destructor.getId() +" destoryed "+target.getMissileId());
			}
			else
				GameLogger.log(destructor, Level.INFO, "Desturctor "+ destructor.getId() +" missed "+target.getMissileId());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
