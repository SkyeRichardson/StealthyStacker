package me.StealthyTomato.StealthyStacker.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;
import me.StealthyTomato.StealthyStacker.Utils.EntityStackUtils;

public class MobSpawnListener implements Listener {
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		Creature mob = (Creature) event.getEntity();
		EntityType mobType = mob.getType();
		int x = Main.getPlugin().config.getInt("mob_distance.x");
		int y = Main.getPlugin().config.getInt("mob_distance.y");
		int z = Main.getPlugin().config.getInt("mob_distance.z");
		EntityStack largestNearbyStack = EntityStackUtils.findLargestEntityStackNearEntity(mob, x, y, z);
		if(largestNearbyStack == null) {
			CreatureStack newCreatureStack = new CreatureStack(mob);
			Main.entityStacks.put(mob.getUniqueId(), newCreatureStack);
		} else {
			largestNearbyStack.addNearbyEntitiesToStack(x, y, z);
		}

	}
}