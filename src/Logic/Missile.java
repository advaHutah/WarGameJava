package Logic;

import java.util.Calendar;

public class Missile extends Thread implements Comparable<Missile> {

	private String id;
	private String destination;
	private int flyTime;
	private int launchTime;
	private int damage;
	private MissileLauncher theLauncher;
	private long s;

	public Missile(String id, String destination, int flyTime, int launchTime, int damage, MissileLauncher theLauncher,
			long s) {
		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launchTime = launchTime;
		this.damage = damage;
		this.theLauncher = theLauncher;
		this.s = s;
	}

	public void launch() throws InterruptedException {
		synchronized (this) {
			theLauncher.addWaitingMissile(this);
			System.out.println("Missile #" + getMissileId() + " is waiting to launch");
			// gets notified by launcher
			wait();
			long f = (System.nanoTime() / 1000000000);
			System.out.println("Missile #" + getMissileId() + " started launch " + (f - s));
		}

	}

	public synchronized void fly() throws InterruptedException {
		synchronized (theLauncher) {
			System.out.println(" Missile #" + getMissileId() + " starts flying for " + flyTime + "ms");
			Thread.sleep(flyTime * 1000);
			// if missile was destroyed

			// else
			theLauncher.notify();
		}

	}

	public String getMissileId() {
		return id;
	}

	// public long getId() {
	// return id;
	// }
	//
	@Override
	public void run() {
		try {
			launch();
			fly();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getLaunchTime() {
		return launchTime;
	}

	@Override
	public int compareTo(Missile otherMissile) {
		if (this.launchTime > otherMissile.launchTime)
			return 1;
		else if (this.launchTime < otherMissile.launchTime)
			return -1;
		return 0;
	}

}
