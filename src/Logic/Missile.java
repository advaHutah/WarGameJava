package Logic;

import java.util.Calendar;

public class Missile extends Thread {
	
	private int id;
	private String destination;
	private int flyTime;
	private int launchTime;
	private int damage;
	private MissileLauncher theLauncher;

	
	public Missile(int id, String destination, int flyTime, int launchTime, int damage,MissileLauncher theLauncher) {
		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launchTime = launchTime;
		this.damage = damage;
		this.theLauncher=theLauncher;
	}

	public void launch() throws InterruptedException {
		synchronized (this) {
			theLauncher.addWaitingMissile(this);
			System.out.println(Calendar.getInstance().getTimeInMillis()
					+ " Missile #" + getId() + " is waiting to launch");
			//gets notified by launcher
			wait();
		}

		synchronized (theLauncher) {
			System.out.println(Calendar.getInstance().getTimeInMillis()
					+ " --> Missile #" + getId() + " started launch");
	
			theLauncher.notifyAll();
		}
	}

	public synchronized void fly() throws InterruptedException {
		System.out.println(Calendar.getInstance().getTimeInMillis()
				+ " Missile #" + getId() + " starts flying for " + flyTime
				+ "ms");
		
		wait(flyTime);
		//if missile was destroyed
		
		//else
		
		
	}


	
	public long getId() {
		return id;
	}
	
	@Override
	public void run() {
		try {
			launch();
			fly();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
