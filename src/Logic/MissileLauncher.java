package Logic;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

public class MissileLauncher implements Runnable {

	private String id;
	private boolean isHidden;
	private Vector<Missile> missilesToLaunch;
	private Queue<Missile> waitingMissiles = new PriorityQueue<Missile>();
	private long s;
	private Vector<MissileLaunchListener> listeners;
	private boolean isDestroyed = false;
	private int launchedMissileCounter =0;

	public MissileLauncher(String id, boolean isHidden) {
		this.id = id;
		this.isHidden = isHidden;
		this.missilesToLaunch = new Vector<Missile>();
		this.s = s;
		this.listeners = new Vector<MissileLaunchListener>();
	}

	public MissileLauncher(String id) {
		this.id = id;
		// seed if hidden
		Random random = new Random();
		this.isHidden = random.nextBoolean();
		this.missilesToLaunch = new Vector<Missile>();
		this.s = s;
		this.listeners = new Vector<MissileLaunchListener>();
	}

	public String getId() {
		return id;
	}

	public void setIsDestroyed(boolean val) {
		this.isDestroyed = val;
		
	}

	public boolean isDestroyed() {
		return this.isDestroyed;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void registerListener(MissileLaunchListener newListener) {
		listeners.add(newListener);
	}

	public void notifyAllListener(Missile missileFly) {
		int size = listeners.size();
		for (int i = 0; i < size; i++)
			listeners.elementAt(i).onLaunchEvent(missileFly);
	}

	public void addMissile(Missile newMissile) throws InterruptedException {
		missilesToLaunch.add(newMissile);

		newMissile.start();
	}

	public synchronized void addWaitingMissile(Missile missile) {
		waitingMissiles.add(missile);

		System.out.println("After adding missile #" + missile.getMissileId() + " there are " + waitingMissiles.size()
				+ " missiles waiting in " + id);

		synchronized (/* dummyWaiter */this) {
			if (waitingMissiles.size() == 1) {
				/* dummyWaiter. */notify(); // to let know there is an missile
											// waiting
			}
		}
	}

	public synchronized void launchMissile() throws InterruptedException {
		Missile firstMissile = waitingMissiles.poll();
		boolean wasHidden = false;
		if (isHidden)
			wasHidden = true;
		if (firstMissile != null) {

			System.out.println("MissileLauncher " + id + " is notifying Missile #" + firstMissile.getMissileId());
			synchronized (firstMissile) {
				firstMissile.notifyAll();
				launchedMissileCounter++;
				isHidden = false;
			}
		}

		synchronized (this) {
			try {

				System.out.println("MissileLauncher " + id + " waits that missile #" + firstMissile.getMissileId()
						+ " will land/be destructed");

				notifyAllListener(firstMissile);

				wait(); // wait till the missile finishes
				long f = (System.nanoTime() / 1000000000);
				System.out.println("Missile Launcher " + id + " was announced that Missile #"
						+ firstMissile.getMissileId() + " is landed/destructed " + (f - s));
				if (wasHidden)
					isHidden = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getLaunchedMissileCounter()
	{
		return launchedMissileCounter;
	}
	
	public Vector<Missile> getMissilesToLaunch() {
		return missilesToLaunch;
	}

	public void run() {
		System.out.println("In Missile Launcher " + id + " ::run");

		while (!isDestroyed) {
			try {
				if (!waitingMissiles.isEmpty()) {
					launchMissile();
					
				} else {
					synchronized (this) {

						System.out.println("Missile Launcher " + id + " has no missiles waiting");
						wait(); // wait till there is a missile waiting

						// gets notified
						System.out.println("Missile Launcher " + id + " was notified there is a missile waiting");

					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Missile Launcher " + id + " was Desrructed");
		Thread.currentThread().interrupt();

	}

}
