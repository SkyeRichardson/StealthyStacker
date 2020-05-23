package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.DroppedItemStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;
import me.StealthyTomato.StealthyStacker.Utils.ObjectStackUtils;

public class ItemSpawnListener implements Listener {
	@EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
		Item item = (Item) event.getEntity();
		int x = Main.getPlugin().config.getInt("item_distance.x");
		int y = Main.getPlugin().config.getInt("item_distance.y");
		int z = Main.getPlugin().config.getInt("item_distance.z");
		EntityStack largestNearbyStack = ObjectStackUtils.findLargestEntityStackNearEntity(item, x, y, z);
		if(largestNearbyStack == null) {
			DroppedItemStack droppedItemStack = new DroppedItemStack(item);
			Main.getEntityStacks().put(item.getUniqueId(), droppedItemStack);
		} else if(largestNearbyStack instanceof DroppedItemStack && item.getItemStack().getType().equals(((Item) largestNearbyStack.getPrincipalEntity()).getItemStack().getType())) {
			event.setCancelled(true);
			int newSize = largestNearbyStack.getSize() + item.getItemStack().getAmount();
			largestNearbyStack.setSize(newSize);
			largestNearbyStack.updateName(newSize);
		}
	}
}