package Logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

public class MissileLauncherDestructor implements Runnable{

	private String type;
	private Map<Integer,MissileLauncher> launchersToDestruct = new HashMap<>();

	public MissileLauncherDestructor(String type) {
		super();
		this.type = type;
	}
	
	
	public void addlauncherToDestruct(MissileLauncher newMissileLauncher,int waitingTime) throws InterruptedException {
		launchersToDestruct.put(waitingTime,newMissileLauncher);
	}
	
	public void setLaunchersToDestruct(Map<Integer, MissileLauncher> launchersToDestruct) {
		this.launchersToDestruct = launchersToDestruct;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public void run() {
		System.out.println("In Missile Launcher Destructor " + type + " ::run");
		while(true){
			if(!launchersToDestruct.isEmpty()){
				for(Iterator<Map.Entry<Integer, MissileLauncher>> it = launchersToDestruct.entrySet().iterator(); it.hasNext(); ) {
				      Map.Entry<Integer, MissileLauncher> entry = it.next();
						new LauncherDestructTarget( entry.getValue(),entry.getKey(), type);
				        it.remove();
				      }
//				for (Entry<Integer, MissileLauncher> entry : launchersToDestruct.entrySet()) {
//					new LauncherDestructTarget( entry.getValue(),entry.getKey(), type);
//					launchersToDestruct.remove(entry.getKey());
//				}
			}
			
		}
		
	}
 

	
	
}
