package me.StealthyTomato.StealthyStacker.Listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
			Inventory playerInv = event.getPlayer().getInventory();
			HashMap<Integer, ItemStack> leftoverItems = playerInv.addItem(new ItemStack(Material.MOB_SPAWNER, 1));
			if(!leftoverItems.isEmpty())
				block.getWorld().dropItem(event.getPlayer().getLocation(), leftoverItems.get(0));

		}
	}
}