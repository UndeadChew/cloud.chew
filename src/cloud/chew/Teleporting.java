package cloud.chew;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import cloud.chew.chat.ChatManager;

public class Teleporting {

	public static void jumptoHere(Player sender) {
		CloudPlayer player = Statistics.getStats(sender.getUniqueId());
	    player.savedWorld = sender.getWorld().getName();
	    player.savedX = sender.getLocation().getX();
	    player.savedY = sender.getLocation().getY();
	    player.savedZ = sender.getLocation().getZ();
	    player.savedPitch = sender.getLocation().getPitch();
	    player.savedYaw = sender.getLocation().getYaw();
	    sender.sendMessage("You have saved your location.");
	}
	
	public static Location jumptoThere(Player sender) {
		CloudPlayer player = Statistics.getStats(sender.getUniqueId());
	    if (player.savedWorld != null)
	    {
	      sender.sendMessage("You have jumped to your saved location.");
	      Location loc = new Location(Bukkit.getWorld(player.savedWorld), player.savedX, player.savedY, player.savedZ, player.savedYaw, player.savedPitch);
	      return loc;
	    }
	    sender.sendMessage("You have not saved a location yet.");
	    return sender.getLocation();
	}
	
	public static void allowPlayer(Player sender, UUID target) {
		if (target != null) {
		Statistics.getStats(sender.getUniqueId()).allowedPlayers.add(target);
		sender.sendMessage("You have allowed " + Bukkit.getPlayer(target).getDisplayName() + " to jump to you.");
		Bukkit.getPlayer(target).sendMessage(sender.getDisplayName() + " has allowed you to jump to them.");
		}
		
	}
	
	public static void removePlayer(Player sender, UUID target) {
		if (Statistics.getStats(sender.getUniqueId()).allowedPlayers.contains(target)) {
		Statistics.getStats(sender.getUniqueId()).allowedPlayers.remove(target);
		sender.sendMessage("You have removed " + Bukkit.getPlayer(target).getDisplayName() + " to jump to you.");
		Bukkit.getPlayer(target).sendMessage(sender.getDisplayName() + " has removed you to jump to them.");
		} else {
			sender.sendMessage(Bukkit.getPlayer(target).getDisplayName() + " is already not allowed to jump to you.");
		}
		
		
	}
	
	
	public static void jumptoPlayerName(Player sender, String typedName) {
		sender.sendMessage("3");
		if (sender != null) {
			sender.sendMessage("4");
			if (typedName != null) {
				sender.sendMessage("5");
				if (Statistics.checkForPlayerByName(typedName)) {
					sender.sendMessage("6");
					UUID id = Statistics.getIdByName(typedName);
					
					if(Statistics.busyPlayers().contains(id)) {
						ChatManager.returnBusy(sender, id);
						return;
					} 

					if(Statistics.hiddenPlayers().contains(id)) {
						ChatManager.returnOffline(sender, id);
						return;
					}
					
					if(Statistics.onlinePlayers().contains(id)) {
						sender.sendMessage("7");
						//then check if even allowed
						sender.sendMessage(sender.getUniqueId().toString() + Statistics.getStats(id).allowedPlayers.toString());
						if (Statistics.getStats(id).allowedPlayers.contains(sender.getUniqueId())) {
							sender.sendMessage("8");
							Location location = Bukkit.getPlayer(id).getLocation();
							teleportPlayer(sender, location);
							ChatManager.returnAllowed(sender, id); //allowed title warp?
						} else {
							ChatManager.returnUnallowed(sender, id); //not allowed
						}
						return;
					}
				} else {
					//never joined
					ChatManager.returnOutBounds(sender, typedName);
				}
			}
		}


		return;
	}

	private static void teleportPlayer(Player sender, Location location) {
		sender.teleport(location);
		return;
	}
	
}
