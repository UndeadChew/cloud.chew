package cloud.chew;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

public class WorldManager {
	
	
	
	public static void dreamLand() {
		if (Bukkit.getWorld("dream") == null) {
			WorldCreator wc = new WorldCreator("dream");
			if (wc.generator() == null) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED +"Creating DREAM World");
				wc.generator(getDefaultWorldGenerator("dream", null));
				wc.environment(Environment.NORMAL);
				wc.generateStructures(false);
				wc.seed(1024);
				//Bukkit.getScheduler().runTaskLater(this, (Runnable) this::loadWorld, 1L);
				Bukkit.createWorld(wc);
			}

		} 
		Bukkit.getConsoleSender().sendMessage(Bukkit.getWorlds().toString());
	}
	
	
	public static ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new TestGenerator();
    }
}
