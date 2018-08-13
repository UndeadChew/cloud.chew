package cloud.chew.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cloud.chew.CloudPlayer;
import cloud.chew.Statistics;
import cloud.chew.Teleporting;

public class Jumpto implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		//sender.sendMessage(args[0] + " " + args[1]);
		if (cmd.getName().equalsIgnoreCase("jumpto")) {
			//case 1
			if (args.length == 0) {
				p.sendMessage("0");
			}
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("clear")) {
					return true;
				}
				if (args[0].equalsIgnoreCase("up")) {
					Location loc = p.getLocation();
					Integer y = loc.getBlockY();
					for(int x = 256-y; x<=256; x++) {
						if (!p.getWorld().getBlockAt(loc.getBlockX(), x, loc.getBlockZ()).isEmpty()) {
							if (p.getWorld().getBlockAt(loc.getBlockX(), x +1, loc.getBlockZ()).isEmpty()) {
								if (p.getWorld().getBlockAt(loc.getBlockX(), x +2, loc.getBlockZ()).isEmpty()) {
									loc.setY(x+1);
									p.teleport(loc);
								}
							}
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("remove")) {
					if (args.length >= 2) {
						if (args[1] != null) {
							String typedName = args[1];

							Teleporting.removePlayer((Player) sender, Statistics.getIdByName(typedName));
							return true;
						}
					} else {
						p.sendMessage("/jumpto remove [player]");
					}
				}
				if (args[0].equalsIgnoreCase("allow")) {
					if (args.length >= 2) {
						if (args[1] != null) {
							String typedName = args[1];

							Teleporting.allowPlayer((Player) sender, Statistics.getIdByName(typedName));
							return true;
						}
					} else {
						p.sendMessage("/jumpto allow [player]");
					}
				} else {
					String typedName = args[0];
					Teleporting.jumptoPlayerName(p, typedName);
					return true;
				}
			} 
		}
		return false;
	}

}
