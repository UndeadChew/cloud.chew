package cloud.chew;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class TestGenerator extends ChunkGenerator {
	byte[] result = null;
	//This is where you stick your populators - these will be covered in another tutorial

	//This needs to be set to return true to override minecraft's default behaviour
	@Override
	public boolean canSpawn(World world, int x, int z) {
		return true;
	}
	//This converts relative chunk locations to bytes that can be written to the chunk
	public int xyzToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}

	void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
		//if the Block section the block is in hasn't been used yet, allocate it
		if (chunk[y >> 4] == null)
			chunk[y >> 4] = new byte[16 * 16 * 16];
		if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
			return;
		try {
			chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material.getId();
		} catch (Exception e) {
			// do nothing
		}
	}

	public byte[][] generateBlockSections(World world, Random rand, int ChunkX,
			int ChunkZ, BiomeGrid biome) {

		byte[][] chunk = new byte[world.getMaxHeight() / 16][];

		if (world != null) {
			world.setAnimalSpawnLimit(0);
			//result = new byte[32768];
			//int y = 0;
			//This will set the floor of each chunk at bedrock level to bedrock

			//for(int x=0; x<16; x++) {
			//	for(int z=0; z<16; z++) {
			//		int y = 0;
			//		result[xyzToByte(x,y,z)] = (byte) Material.STONE.getId();
			//	}
			//}

			SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(world,8);
			VoronoiGenerator gen2 = new VoronoiGenerator(world.getSeed(), (short) 0);
			gen1.setScale(1/64.0);
			//gen2.setDistanceMethod((short)20);



			for (int x=0; x<16; x++) { 
				for (int z=0; z<16; z++) {

					int realX = x + ChunkX * 16; //used so that the noise function gives us
					int realZ = z + ChunkZ * 16; //different values each chunk
					double frequency = 0.5; // the reciprocal of the distance between points
					double mountainFrequency = 1/64;
					double amplitude = 0.5; // The distance between largest min and max values
					int multitude = 10; //how much we multiply the value between -1 and 1. It will determine how "steep" the hills will be.
					int sea_level = 64;


					double mountainHeight = gen1.noise(realX, realZ, mountainFrequency, amplitude) * 32 + sea_level;
					double varitionHeight = gen1.noise(realX, realZ, 1.00/32.00, amplitude) * 16 + sea_level;
					double maxHeight = Math.max(varitionHeight, gen1.noise(realX, realZ, frequency, amplitude) * multitude + sea_level);
					
					double density = gen2.noise(realX, realZ, frequency) * multitude + sea_level;

					for (int y=30;y<sea_level;y++) {

						setBlock(x,y,z,chunk,Material.WATER); //set the current block to stone

					}
					for (int y=0;y<maxHeight;y++) {
						
						double d3nsity = gen2.valueNoise3D(realX, y, realZ, world.getSeed());
						//if (d3nsity > 0) {
						//Bukkit.getServer().broadcastMessage("denisty: " + d3nsity);
						//if (density <= 60) {
						setBlock(x,y,z,chunk,Material.STONE); //set the current block to stone
						//}	
						//}
					}
					//setBlock(x, (int) maxHeight,z,chunk,Material.GRASS);
					//setBlock(x,(int) maxHeight,z,chunk,Material.WOOL);
					//Location loc = new Location(world, x, maxHeight, z);
					//world.getBlockAt(x, (int) maxHeight, z).setData((byte) 6);
					//loc.getBlock().setType(Material.WOOL);
					//loc.getBlock().setData((byte) 6);
				}
			}

		}
		return chunk;
	}
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return Arrays.asList((BlockPopulator) new TestPopulator());
	}

}