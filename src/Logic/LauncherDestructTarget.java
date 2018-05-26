package Logic;

public class LauncherDestructTarget extends Thread {
	private MissileLauncher target;
	private int waitingTime;
	private String type;

	public LauncherDestructTarget(MissileLauncher target,int waitingTime,String type) {
		this.target = target;
		this.waitingTime=waitingTime;
		this.type=type;
		this.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(waitingTime*1000);

			System.out.println("Missile Launcher Desturctor "+type +" finish waiting to "+target.getId());

			if(!target.isHidden())
			{
				target.setIsDestroyed(true);
				System.out.println("Missile Launcher Desturctor "+type +" destoryed "+target.getId());
			}
			else
				System.out.println("Missile Launcher Desturctor "+type +" missed "+target.getId());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
