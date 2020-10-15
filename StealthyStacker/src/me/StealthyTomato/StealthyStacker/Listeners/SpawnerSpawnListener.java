package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;
import me.StealthyTomato.StealthyStacker.Utils.ObjectStackUtils;

public class SpawnerSpawnListener implements Listener {

	@EventHandler
	public void onSpawnerSpawnEvent(SpawnerSpawnEvent event) {
		Location loc = event.getSpawner().getLocation();
		int stackSize = Main.getEntityStacks().get(loc).getSize();
		
		
		if(event.getEntity() instanceof Creature) {
			Creature mob = (Creature) event.getEntity();
			int x = Main.getPlugin().config.getInt("mob_distance.x");
			int y = Main.getPlugin().config.getInt("mob_distance.y");
			int z = Main.getPlugin().config.getInt("mob_distance.z");
			EntityStack largestNearbyStack = ObjectStackUtils.findLargestEntityStackNearEntity(mob, x, y, z);
				if(largestNearbyStack == null) {
					CreatureStack newCreatureStack = new CreatureStack(mob);
					newCreatureStack.setSize(stackSize);
					Main.getEntityStacks().put(mob.getUniqueId(), newCreatureStack);
				} 
				else{
					event.setCancelled(true);
					largestNearbyStack.incrementObjectStack(stackSize);
				}
		} 
		else{
			
		}
	}
}
