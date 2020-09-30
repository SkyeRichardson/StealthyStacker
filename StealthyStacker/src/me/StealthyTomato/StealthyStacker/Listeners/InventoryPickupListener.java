package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftHopper;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.StealthyTomato.StealthyStacker.Main;

//E
public class InventoryPickupListener implements Listener {
	@EventHandler
	public void onHopperGobble(InventoryPickupItemEvent event) {
		UUID gobbledStack = event.getItem().getUniqueId();
		if(Main.getEntityStacks().containsKey(gobbledStack)) {
			
			HashMap<Integer, ItemStack> itemStackHash = new HashMap<Integer,ItemStack>();
			itemStackHash.put(0, event.getItem().getItemStack());
			
			Inventory inv = event.getInventory();
			int stackSize = Main.entityStacks.get(gobbledStack).getSize();
			ItemStack gobbled = new ItemStack(event.getItem().getItemStack().getType(),stackSize-1);
			
			itemStackHash = inv.addItem(gobbled);
			if(!itemStackHash.isEmpty()) {
				event.setCancelled(true);
				Main.entityStacks.get(gobbledStack).setSize(itemStackHash.get(0).getAmount());
				return;
			}
			else {
				return;
			}
		}
	
	}
}
