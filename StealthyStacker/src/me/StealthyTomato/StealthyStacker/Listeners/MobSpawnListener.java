package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;
import me.StealthyTomato.StealthyStacker.Utils.ObjectStackUtils;

public class MobSpawnListener implements Listener {
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		if(event.getSpawnReason() == SpawnReason.SPAWNER) {

			//Bukkit.getLogger().info("skipping CreatureSpawnEvent cus spawner spawn event :p");
			return;
		}
		
		if(event.getEntity() instanceof Creature) {
			Creature mob = (Creature) event.getEntity();
			SpawnReason spawnReason = event.getSpawnReason();
			int x = Main.getPlugin().config.getInt("mob_distance.x");
			int y = Main.getPlugin().config.getInt("mob_distance.y");
			int z = Main.getPlugin().config.getInt("mob_distance.z");
			EntityStack largestNearbyStack = ObjectStackUtils.findLargestEntityStackNearEntity(mob, x, y, z);
			if(!spawnReason.equals(SpawnReason.CUSTOM))  {
				if(largestNearbyStack == null) {
					CreatureStack newCreatureStack = new CreatureStack(mob);
					Main.getEntityStacks().put(mob.getUniqueId(), newCreatureStack);
				} else if(mob.getType().equals(largestNearbyStack.getPrincipalEntity().getType())) {
					event.setCancelled(true);
					largestNearbyStack.incrementObjectStack();
				}
			} else {
				
			}
		}
	}
}