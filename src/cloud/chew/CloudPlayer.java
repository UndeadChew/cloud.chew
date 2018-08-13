package cloud.chew;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cloud.chew.zones.Zone;

public class CloudPlayer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1009280906379055354L; 
	//public UUID uuid;
	public String displayName;
	public Integer currency;
	public Integer permissions;
	public HashSet<UUID> allowedPlayers;
	public transient List<Zone> zones = new ArrayList<Zone>(); //hey Chew, when you end up wanting to remove a players zone, remove their subzones first, and clear them.
	//public Location savedLocation; //this might not work because it needs to be serialized.
	public String savedWorld;
	public double savedX;
	public double savedY;
	public double savedZ;
	public float savedYaw;
	public float savedPitch;
	public String organization;
	public String currIP;
	public String firstIP;
	public Integer rank; //range from 0-10
	public Long joinDate;
	public Long currJoin;
	public Long playTime;
	public Boolean isOnline;
	public Boolean isHidden;
	public Boolean isBusy;
	
	
	public void setPermissions(Integer i) {
		permissions = i;
	}
	//below this all has to do with a player's in game time record.
	public void newJoin(Player p) {
		Bukkit.getServer().broadcastMessage("warning");
		playTime = (long) 0; 
		displayName = p.getDisplayName();
		currency = 0;
		firstIP = p.getAddress().toString();
		rank = 0;
		permissions = 0;
		joinDate = Statistics.getTime();
		join();
	}
	public void join() {
		isOnline = true;
		start();
	}
	public void leave() {
		end();
		isOnline = false;
	}
	
	
	public void start() {
		currJoin = Statistics.getTime();
	}
	
	public void restart() {
		playTime = playTime - (currJoin - Statistics.getTime());
		currJoin = Statistics.getTime();
	}
	public Long check() {
		return playTime - (currJoin - Statistics.getTime());
	}
	
	public void end() {
		playTime = playTime - (currJoin - Statistics.getTime());
		Bukkit.getServer().broadcastMessage("leave " + playTime);
	}
	public void changeName(UUID id, String newName) {
	    displayName = newName;
	    Statistics.displayNames.get(id);
	  }
	
}
