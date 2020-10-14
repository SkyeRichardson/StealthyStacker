package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.StealthyTomato.StealthyStacker.Main;

//InventoryPickupItemEvent used explicitly for hoppers
public class InventoryPickupListener implements Listener {
	@EventHandler
	public void onHopperGobble(InventoryPickupItemEvent event) {
		UUID gobbledStack = event.getItem().getUniqueId();
		if(Main.getEntityStacks().containsKey(gobbledStack)) {
			
			int stackSize = Main.entityStacks.get(gobbledStack).getSize();
			ItemStack gobbled = new ItemStack(event.getItem().getItemStack().getType(),stackSize);
			
			HashMap<Integer, ItemStack> itemStackHash = new HashMap<Integer,ItemStack>();
			Inventory inv = event.getInventory();
			itemStackHash = inv.addItem(gobbled);
			if(!itemStackHash.isEmpty()) {
				Main.entityStacks.get(gobbledStack).setSize(itemStackHash.get(0).getAmount());
				return;
			}
			else {
				Main.entityStacks.remove(gobbledStack);
				return;
			}
		}
	
	}
}
