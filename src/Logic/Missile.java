package Logic;

import java.util.Calendar;
import java.util.Random;

public class Missile extends Thread implements Comparable<Missile> {

	private String id;
	private String destination;
	private int flyTime;
	private int launchTime;
	private int damage;
	private boolean Destructed = false;
	private boolean willHit = true;
	private boolean hitTarget =false;
	private MissileLauncher theLauncher;
	private long s;

	public Missile(String id, String destination, int flyTime, int launchTime, int damage, MissileLauncher theLauncher) {
		Random r = new Random();

		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launchTime = launchTime;
		this.damage = damage;
		this.theLauncher = theLauncher;
		this.willHit = r.nextBoolean();
		this.s = s;
	}
	
	public void launch() throws InterruptedException {
		synchronized (this) {
			Thread.sleep(launchTime * 1000);
			theLauncher.addWaitingMissile(this);
			long f = (System.nanoTime() / 1000000000);
			System.out.println("Missile #" + getMissileId() + " is waiting to launch " + (f - s));
			// gets notified by launcher
			wait();
			f = (System.nanoTime() / 1000000000);
			System.out.println("Missile #" + getMissileId() + " started launch " + (f - s));
		}

	}

	public synchronized void fly() throws InterruptedException {
		synchronized (theLauncher) {
			System.out.println("Missile #" + getMissileId() + " starts flying for " + flyTime + "ms");
			synchronized (this) {
				wait(flyTime * 1000);// if missile was destroyed MissileDestructor notify
				if(Destructed)	{
					willHit = false;
					System.out.println("Missile #" + getMissileId() + " was destructed");
				}
			}
			if(willHit){
				hitTarget=true;
				System.out.println("Missile #" + getMissileId() + " hit target");
			}
			else
				System.out.println("Missile #" + getMissileId() + " missed target");

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
	
	public boolean isDestructed() {
		return Destructed;
	}
	
	public void setDestructed(boolean destructed) {
		Destructed = destructed;
	}
	
	public int getFlyTime() {
		return flyTime;
	}
	
	 public boolean isHitTarget() {
		return hitTarget;
	}
	 public int getDamage() {
		return damage;
	}
	 public MissileLauncher getTheLauncher() {
		return theLauncher;
	}
	 public String getDestination() {
		return destination;
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
