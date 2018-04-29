package Logic;

import java.util.Random;
import java.util.Vector;

public class MissileLauncher {
	
	private String id;
	private boolean isHidden;
	private Vector<Missile> missilesToLaunch;
	
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
	

}
