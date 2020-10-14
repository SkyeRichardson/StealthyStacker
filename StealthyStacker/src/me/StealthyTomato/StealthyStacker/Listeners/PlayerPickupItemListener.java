package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.StealthyTomato.StealthyStacker.Main;

public class PlayerPickupItemListener implements Listener {
	@EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
		Item item = event.getItem();
		ItemStack itemStack = item.getItemStack();
		if (Main.getEntityStacks().containsKey(item.getUniqueId())) {
			int stackSize = Main.getEntityStacks().get(item.getUniqueId()).getSize();
			itemStack.setAmount(stackSize);
			Player player = event.getPlayer();
			Inventory inv = player.getInventory();
			inv.addItem(itemStack);
		}

	}
}