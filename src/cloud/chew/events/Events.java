package cloud.chew.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cloud.chew.CloudPlayer;
import cloud.chew.Statistics;
import cloud.chew.database.Connection;

public class Events implements Listener{

	long time = System.currentTimeMillis() /1000L;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		
		
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();
		p.sendMessage("test");
		if(!Statistics.checkForPlayer(p)) { //throws error
			p.sendMessage("test2");
			CloudPlayer playerClass = new CloudPlayer();
			
			Statistics.players.put(uuid, playerClass);
			playerClass.newJoin(p);
			playerClass.setPermissions(0);
			Connection.createTables();
		} else {
			CloudPlayer stats = Statistics.players.get(uuid);
			stats.join();
		}
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		//event.getPlayer().sendMessage("" + Statistics.players.get(event.getPlayer().getUniqueId()).check());
	}
	@EventHandler
	public void onPlayerDissconnect(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		if(Statistics.checkForPlayer(p)) {
			UUID uuid = p.getUniqueId();
			CloudPlayer stats = Statistics.getStats(uuid); //this may be causes errors when a player disconnects without stats.
			Bukkit.getServer().broadcastMessage("" + time);
			stats.end();
		}
		
		
	}
	
}
