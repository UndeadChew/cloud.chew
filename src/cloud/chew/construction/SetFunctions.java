package cloud.chew.construction;

import org.bukkit.Location;

public class SetFunctions {
	
	
	
	
	public static Boolean cuboidIntersect(int xAMin, int yAMin, int zAMin, int xAMax, int yAMax, int zAMax, int xBMin, int yBMin, int zBMin, int xBMax, int yBMax, int zBMax) {
		return ((xAMin >= xBMin && xAMin <= xBMax) || (xAMax >= xBMin && xAMax <= xBMax)
				|| (yAMin >= yBMin && yAMin <= yBMax) || (yAMax >= yBMin && yAMax <= yBMax)
				|| (zAMin >= zBMin && zAMin <= zBMax) || (zAMax >= zBMin && zAMax <= zBMax));
	}
	
	public static Boolean isInside(Location loc, int x1, int y1, int z1, int x2, int y2, int z2) {
		int xMin = x1;
		int xMax = x2;
		int yMin = y1;
		int yMax = y2;
		int zMin = z1;
		int zMax = z2;
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		return (x >= xMin && x <= xMax && y >= yMin && y <= yMax && z >= zMin && z <= zMax);
	}
	
	
	public static Location getMax(Location l1, Location l2) {
		int x1 = l1.getBlockX();
		int y1 = l1.getBlockY();
		int z1 = l1.getBlockZ();
		int x2 = l2.getBlockX();
		int y2 = l2.getBlockY();
		int z2 = l2.getBlockZ();
		int x = x1 > x2 ? x1 : x2;
		int y = y1 > y2 ? y1 : y2;
		int z = z1 > z2 ? z1 : z2;
		return new Location(l1.getWorld(),x,y,z);
	}
	public static Location getMin(Location l1, Location l2) {
		int x1 = l1.getBlockX();
		int y1 = l1.getBlockY();
		int z1 = l1.getBlockZ();
		int x2 = l2.getBlockX();
		int y2 = l2.getBlockY();
		int z2 = l2.getBlockZ();
		int x = x1 < x2 ? x1 : x2;
		int y = y1 < y2 ? y1 : y2;
		int z = z1 < z2 ? z1 : z2;
		return new Location(l1.getWorld(),x,y,z);
	}

}
