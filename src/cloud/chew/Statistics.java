package cloud.chew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//import cloud.chew.zones.Zone;

public class Statistics {
	public static HashMap<UUID, CloudPlayer> players = new HashMap<UUID, CloudPlayer>();
	public static HashMap<UUID, String> displayNames = new HashMap<UUID, String>();
	
	
	
	
	public static List<UUID> hiddenPlayers() { 
		List<UUID> hiddenPlayers = new ArrayList<UUID>();
		
		for(UUID uuid : players.keySet()) {
			CloudPlayer player = players.get(uuid);
			if(player.isHidden) {
				hiddenPlayers.add(uuid);
			}
		}
		return hiddenPlayers;
	}
	
	public static List<UUID> busyPlayers() { //have commands check this for player usage for target and sender
		List<UUID> busyPlayers = new ArrayList<UUID>();
		
		for(UUID uuid : players.keySet()) {
			CloudPlayer player = players.get(uuid);
			if(player.isBusy) {
				busyPlayers.add(uuid);
			}
		}
		return busyPlayers;
	}
	
	
	public static List<UUID> offlinePlayers() {
		List<UUID> offlinePlayers = new ArrayList<UUID>();
		
		for(UUID uuid : players.keySet()) {
			CloudPlayer player = players.get(uuid);
			if(!player.isOnline) {
				offlinePlayers.add(uuid);
			}
		}
		return offlinePlayers;
	}
	
	
	public static List<UUID> onlinePlayers() {
		List<UUID> onlinePlayers = new ArrayList<UUID>();
		
		for(UUID uuid : players.keySet()) {
			CloudPlayer player = players.get(uuid);
			if(player.isOnline) {
				onlinePlayers.add(uuid);
			}
		}
		return onlinePlayers;
	}
	
	
	
	
	public static boolean checkForPlayer(Player p) {
		if(players.containsKey(getId(p))) {
			return true;
		}
		return false;
	}
	
	public static boolean checkForPlayerByName(String displayName) {
		if(displayNames.containsValue(displayName)) {
			return true;
		}
		return false;
	}
	public static UUID getIdByName(String displayName) {
		
		//if(displayNames.containsValue(displayName)) {
			//Set<UUID> uuid = displayNames.entrySet();
			for (Entry<UUID, String> e : displayNames.entrySet()) {
			    UUID key = e.getKey();
			    String value = e.getValue();
			    
			    if (value.equalsIgnoreCase(displayName)) {
			    	//Bukkit.getServer().broadcastMessage(key.toString());
			    	return key;
			    }
			}
			return null;
		//}
	}
	public static CloudPlayer getStats(UUID uuid) {
		
		return players.get(uuid);
		
	}
	public static UUID getId(Player p) {
		return p.getUniqueId();
	}
	
	
	public static long getTime() {
		long time = System.currentTimeMillis() /1000L;
		return time;
	}
	
}
