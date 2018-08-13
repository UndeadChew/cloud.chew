package cloud.chew;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import cloud.chew.commands.Jumpto;
import cloud.chew.database.MySql;
import cloud.chew.events.CommandPreprocessor;
import cloud.chew.events.Events;
import cloud.chew.events.ZoneSelectionEvent;



public class Main extends JavaPlugin{

	public static Main plugin = null;
	

	
	public void onEnable() {
		MySql.connect();
		plugin = this;
		
		WorldManager.dreamLand();
		//create dream world
		getCommand("jumpto").setExecutor(new Jumpto());
		
		
		
		registerEvents(this, new Events());
		registerEvents(this, new ZoneSelectionEvent());
		registerEvents(this, new CommandPreprocessor());
		//Bukkit.getConsoleSender().sendMessage(ChatColor.RED +"Testfu");
	}
	
	
	public void onDisable() {
		MySql.disconnect();
		plugin = null;

	}

	public static void saveFile() {
		plugin.saveConfig();

	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	//To access the plugin variable from other classes
	public static Plugin getPlugin() {
		return plugin;

	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		return false;
	}
}