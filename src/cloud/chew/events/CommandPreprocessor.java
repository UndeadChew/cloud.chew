package cloud.chew.events;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

import cloud.chew.CloudPlayer;
import cloud.chew.Statistics;

public class CommandPreprocessor implements Listener{



	@EventHandler
	private void onCommand(PlayerCommandPreprocessEvent event) {

		Player player = event.getPlayer();
		
		if (!Statistics.checkForPlayer(player) || !player.isOp()) {
			player.sendMessage("You suck stop.");
			//return;
		}
		UUID uuid = Statistics.getId(player);
		CloudPlayer stats = Statistics.getStats(uuid);
		if (!player.isOp()) {
			player.sendMessage("You suck stop.");
			return;
		}
		//admin commands after this.
		 
		String message = event.getMessage();

		switch (message) {
		case "/test":
			if (Bukkit.getWorld("dream") != null) {
				player.teleport(Bukkit.getWorld("dream").getSpawnLocation());
				//player.sendMessage(Statistics.getStats(uuid).permissions + "");
				event.setCancelled(true);
			} else {
				player.sendMessage("Dream world not loaded.");
			}
			break;
		case "/test2":

			player.sendMessage("HERES YA GLASS BUB");
			String world = Bukkit.getWorld("dream").getName();
			player.teleport(Bukkit.getWorld("world").getSpawnLocation());
			if (Bukkit.unloadWorld(world, true) == true) {
				Bukkit.getConsoleSender().sendMessage(Bukkit.getWorlds().toString());



				try {
					FileUtils.deleteDirectory(new File(world));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				event.setCancelled(true);
				break;
			} else {
				Bukkit.getServer().broadcastMessage("could not unload");
			}
		}
		
	}
}
