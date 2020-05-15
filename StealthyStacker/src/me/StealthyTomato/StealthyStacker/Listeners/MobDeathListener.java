package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class MobDeathListener implements Listener {
	@EventHandler
	public void onMobDeath(EntityDeathEvent event) {
		Creature mob = (Creature) event.getEntity();
		EntityStack entityStack = Main.getEntityStacks().get(mob.getUniqueId());
		if(entityStack != null) {
			entityStack.decrementEntityStack();
			if(entityStack.getSize() == 0)
				Main.getEntityStacks().remove(mob.getUniqueId());
			else
				mob.getWorld().spawnEntity(mob.getLocation(), EntityType.COW);
		}
	}
}