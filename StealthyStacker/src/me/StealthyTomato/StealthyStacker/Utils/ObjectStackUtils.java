package me.StealthyTomato.StealthyStacker.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.BlockStack;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class ObjectStackUtils {
	public static EntityStack findLargestEntityStackNearEntity(Entity entity, int rx, int ry, int rz) {
		EntityStack largestEntityStack = null;
		List<Entity> nearbyEntities = entity.getNearbyEntities(rx, ry, rz);
		for (Entity ent : nearbyEntities)
			if(Main.getEntityStacks().get(ent.getUniqueId()) != null)
				if(largestEntityStack == null || Main.getEntityStacks().get(ent.getUniqueId()).getSize() > largestEntityStack.getSize())
					largestEntityStack = Main.getEntityStacks().get(ent.getUniqueId());
		return largestEntityStack;
	}
	
	public static BlockStack findLargestBlockStackNearBlock(Block block) {
		BlockStack largestBlockStack = null;
		List<Block> adjacentBlocks = getAdjacentBlocks(block);
		for (Block adjacentBlock : adjacentBlocks)
			if(Main.blockStacks.get(adjacentBlock.getLocation()) != null)
				if(largestBlockStack == null || Main.blockStacks.get(adjacentBlock.getLocation()).getSize() > largestBlockStack.getSize())
					largestBlockStack = Main.blockStacks.get(adjacentBlock.getLocation());
		return largestBlockStack;
	}
	
	public static List<Block> getAdjacentBlocks(Block block) {
		World world = block.getWorld();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		Location location = new Location(world, x, y, z);
		List<Location> adjacentLocations = new ArrayList<Location>();
		Location adjacentXPlus = new Location(world, x + 1, y, z);
		Location adjacentXMinus = new Location(world, x - 1, y, z);
		Location adjacentYPlus = new Location(world, x, y + 1, z);
		Location adjacentYMinus = new Location(world, x, y - 1, z);
		Location adjacentZPlus = new Location(world, x, y, z + 1);
		Location adjacentZMinus = new Location(world, x, y, z - 1);
		adjacentLocations.addAll(Arrays.asList(adjacentXPlus, adjacentXMinus, adjacentYPlus, adjacentYMinus, adjacentZPlus, adjacentZMinus));
		List<Block> adjacentBlocks = new ArrayList<Block>();
		for(Location adjacentLocation : adjacentLocations)
			adjacentBlocks.add(adjacentLocation.getBlock());
		return adjacentBlocks;
	}
}
