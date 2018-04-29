package Logic;

import java.util.Vector;

public class MissileDestructor {
	
	private String id;
	private Vector<Missile> missilesToDestruct;
	
	public MissileDestructor(String id) {
		this.id = id;
		this.missilesToDestruct = new Vector<Missile>();
	}
	

}
