package Logic;

public class Missile {
	
	private int id;
	private String destination;
	private int flyTime;
	private int launchTime;
	private int damage;
	
	
	public Missile(int id, String destination, int flyTime, int launchTime, int damage) {
		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launchTime = launchTime;
		this.damage = damage;
	}

}
