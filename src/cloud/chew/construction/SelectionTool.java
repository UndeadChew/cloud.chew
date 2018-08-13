package cloud.chew.construction;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionTool {
	public static  HashMap<UUID, Location> point1 = new HashMap<UUID, Location>();
	public static  HashMap<UUID, Location> point2 = new HashMap<UUID, Location>();

	public static Integer getLength(Location p1, Location p2) {
		Integer i = 1 + Math.abs(p1.getBlockX() - p2.getBlockX());
		return i;
	}
	public static Integer getWidth(Location p1, Location p2) {
		Integer i = 1 + Math.abs(p1.getBlockZ() - p2.getBlockZ());
		return i;
	}
	public static boolean compareWorlds(Location p1, Location p2) {
		if (p1.getWorld().getName().equalsIgnoreCase(p2.getWorld().getName())) {
			return true;
		}
		return false;
	}
	public static void select(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (!(event.getItem() == null)) { //? error
				if (event.getItem().getType() == Material.STICK) {
					UUID selector = event.getPlayer().getUniqueId();
					if (event.getClickedBlock() != null) {
						Location location = event.getClickedBlock().getLocation();

						Integer points = SelectionTool.countPoints(selector);

						if (points == 2) {
							clearPoints(selector);
							setPoint1(selector, location);
							event.getPlayer().sendMessage("set first point.");
							return;
						} else if (points == 1) {
							setPoint2(selector, location);
							
							if (compareWorlds(getPoint1(selector), getPoint2(selector))) {
								event.getPlayer().sendMessage("set second point. X: " + getLength(getPoint1(selector), getPoint2(selector)) + " Z: " + getWidth(getPoint1(selector), getPoint2(selector))); 
								return;
							}
							clearPoints(selector);
							setPoint1(selector, location);
							event.getPlayer().sendMessage("set first point.");
							return;
						} else {
							
							setPoint1(selector, location);
							event.getPlayer().sendMessage("set first point.");
							return;
						}


					}
				}
			}
		}
	}
	public static void setPoint1(UUID uuid, Location location) {
		point1.put(uuid, location);
	}

	public static void setPoint2(UUID uuid, Location location) {
		point2.put(uuid, location);
	}
	public static void clearPoints(UUID uuid) {
		if (point2.containsKey(uuid)) {
			point2.remove(uuid);
		}
		if (point1.containsKey(uuid)) {
			point1.remove(uuid);
		}

	}
	public static Location getPoint1(UUID uuid) {
		if (point1.containsKey(uuid)) {
			return point1.get(uuid);
		}
		return null;

	}
	public static Location getPoint2(UUID uuid) {
		if (point2.containsKey(uuid)) {
			return point2.get(uuid);
		}
		return null;

	}
	public static Integer countPoints(UUID uuid) {
		if (point2.containsKey(uuid)) {
			return 2;
		} else if (point1.containsKey(uuid)) {
			return 1;
		}
		return 0;
	}
}
