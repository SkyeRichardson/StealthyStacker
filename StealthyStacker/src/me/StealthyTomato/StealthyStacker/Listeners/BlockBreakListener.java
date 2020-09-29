package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.BlockStack;

public class BlockBreakListener implements Listener {
	@EventHandler
    public void onBlockPlace(BlockBreakEvent event) {
		Block block = event.getBlock();
		if(block.getType().equals(Material.MOB_SPAWNER)) {
			BlockStack blockStack = Main.blockStacks.get(block.getLocation());
			if(blockStack != null) {
				blockStack.decrementObjectStack();
				if(blockStack.getSize() > 0)
					event.setCancelled(true);
			}
		}
	}
}