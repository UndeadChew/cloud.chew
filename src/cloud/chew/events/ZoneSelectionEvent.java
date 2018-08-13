package cloud.chew.events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerInteractEvent;

import cloud.chew.construction.SelectionTool;

public class ZoneSelectionEvent implements Listener{



	@EventHandler
	public void onSlectionEvent(PlayerInteractEvent event) {
		
		SelectionTool.select(event);
	}
}
