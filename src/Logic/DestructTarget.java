package Logic;

public class DestructTarget extends Thread {
	private Missile target;
	private int waitingTime;
	private String destructorId;


	public DestructTarget(Missile target,int waitingTime,String destructorId) {
		this.target = target;
		this.waitingTime=waitingTime;
		this.destructorId=destructorId;
		this.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("Desturctor "+ destructorId +" Launcher waits "+waitingTime+ " for "+target.getMissileId());
			Thread.sleep(waitingTime*1000);

			System.out.println("Desturctor "+ destructorId +" finish waiting to "+target.getMissileId());

			if(waitingTime< target.getFlyTime() && !target.isDestructed())
			{
				synchronized (target) {
					target.setDestructed(true);
					target.notify();

				}
				System.out.println("Desturctor "+ destructorId +" destoryed "+target.getMissileId());
			}
			else
				System.out.println("Desturctor "+ destructorId +" missed "+target.getMissileId());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
