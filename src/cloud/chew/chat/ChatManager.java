package cloud.chew.chat;

import java.util.UUID;

import org.bukkit.entity.Player;

import cloud.chew.Statistics;

public class ChatManager {
	
	

	public static void setDisplayName(Player sender, String displayName) {
		sender.setDisplayName(displayName);
		Statistics.getStats(sender.getUniqueId()).displayName = displayName;
	}
	
	public static String getDisplayName(UUID id) {
		return Statistics.getStats(id).displayName;
	}
	
	public static void returnBusy(Player sender, UUID id) {
		String message = Statistics.getStats(id).displayName + " is currently busy.";
		sender.sendMessage(message);
		
	}
	//be able to control the color and text of everything.

	public static void returnOffline(Player sender, UUID id) {
		String message = Statistics.getStats(id).displayName + " is currently Offline.";
		sender.sendMessage(message);
		
	}

	public static void returnOnline(Player sender, UUID id) {
		String message = Statistics.getStats(id).displayName + " is currently Online.";
		sender.sendMessage(message);
		
	}
	
	public static void returnOutBounds(Player sender, String typedName) {
		String message = typedName + " could not be found.";
		sender.sendMessage(message);
		
	}

	public static void returnAllowed(Player sender, UUID id) {
		String message = "You have jumped to " + Statistics.getStats(id).displayName + ".";
		sender.sendMessage(message);
		
	}

	public static void returnUnallowed(Player sender, UUID id) {
		String message = "You are not allowed to jump to " + Statistics.getStats(id).displayName + ".";
		sender.sendMessage(message);
		
	}
}
