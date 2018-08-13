package cloud.chew;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class TestPopulator extends BlockPopulator {

	
	@Override
	public  void populate(final World world, final Random random, final Chunk source) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				final int chance = random.nextInt(100);
				//if (chance > 0) {
				int realX = x + source.getX() * 16; //find the world location of chunk location x
				int realZ = z + source.getZ() * 16;
					final Block handle = world.getHighestBlockAt(x + source.getX() * 16, z + source.getZ() * 16);
					if (handle.getRelative(BlockFace.DOWN).getType().equals(Material.STONE)) {
						//handle.setTypeIdAndData(Material.WOOL.getId(), (byte) 0x9, false);
						int remainder = (handle.getX() / 16);
						if ( (((handle.getX() / 16) % 2 == 0) && ((handle.getZ() / 16) % 2 != 0)) || (((handle.getX() / 16) % 2 != 0) && ((handle.getZ() / 16) % 2 == 0))) {
						//handle.setTypeIdAndData(Material.WOOL.getId(), (byte) 0x6, false);
						} else {
							
						}
					}
				//}
			}
		}
	}

}
