package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.BlockStack;
import me.StealthyTomato.StealthyStacker.StackClasses.DroppedItemStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;
import me.StealthyTomato.StealthyStacker.Utils.ObjectStackUtils;

public class BlockPlaceListener implements Listener {
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		if(block.getType().equals(Material.MOB_SPAWNER)) {
			BlockStack largestNearbyStack = ObjectStackUtils.findLargestBlockStackNearBlock(block);
			if(largestNearbyStack == null) {
				BlockStack blockStack = new BlockStack(block);
				Main.blockStacks.put(block.getLocation(), blockStack);
			} else if(block.getType().equals((largestNearbyStack.getPrincipalBlock()).getType())) {
				event.setCancelled(true);
				int newSize = largestNearbyStack.getSize() + 1;
				largestNearbyStack.incrementObjectStack();
				largestNearbyStack.updateName(newSize);
			}
		}
	}
}