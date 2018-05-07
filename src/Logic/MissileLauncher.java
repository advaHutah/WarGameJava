package Logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

public class MissileLauncher implements Runnable{
	
	private String id;
	private boolean isHidden;
	private Vector<Missile> missilesToLaunch;
	private Queue<Missile> waitingMissiles = new LinkedList<Missile>();

	
	public MissileLauncher(String id, boolean isHidden) {
		this.id = id;
		this.isHidden = isHidden;
		this.missilesToLaunch = new Vector<Missile>();
	}

	public MissileLauncher(String id) {
		this.id = id;
		//seed if hidden 
		Random random =  new Random();
		this.isHidden = random.nextBoolean();
	}
	
	public void addMissile(Missile newMissile) {
		missilesToLaunch.add(newMissile);
		newMissile.start();
	}
	
	public synchronized void addWaitingMissile(Missile missile) {
		waitingMissiles.add(missile);

		System.out.println("After adding missile #" + missile.getId()
				+ " there are " + waitingMissiles.size()
				+ " missiles waiting");

		synchronized (/*dummyWaiter*/this) {
			if (waitingMissiles.size() == 1) {
				/*dummyWaiter.*/notify(); // to let know there is an missile
										// waiting
			}
		}
	}
	
	public synchronized void launchMissile() {
		Missile firstMissile = waitingMissiles.poll();
		if (firstMissile != null) {

			System.out.println("MissileLauncher is notifying Missile #"
					+ firstMissile.getId());
			synchronized (firstMissile) {
				firstMissile.notifyAll();
			}
		}

	//	synchronized (this) {
			try {

				System.out.println("MissileLauncher waits that missile #"
						+ firstMissile.getId()
						+ " will land/be destructed");

				wait(); // wait till the missile finishes

				System.out.println("Missile Launcher was announced that Missile #"
						+ firstMissile.getId() + " is landed/destructed");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}

	public void run() {
		
		//while not being destroyed () ??? 
		System.out.println("In Missile Launcher "+ id +" ::run");
		
			if (!waitingMissiles.isEmpty()) {
				launchMissile();
			} else {
				synchronized (this) {
					try {
						System.out.println("Missile Launcher has no missiles waiting");
						wait(); // wait till there is a missile  waiting
		
						//gets notified
						System.out.println("Missile Launcher was notified there is a missile waiting");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		
	}
		
	
	

}
