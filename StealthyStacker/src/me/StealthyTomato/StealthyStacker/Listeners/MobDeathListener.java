package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

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
			Main.getEntityStacks().remove(mob.getUniqueId());
			if(entityStack.getSize() > 0) {
				Creature newPrincipal = (Creature) mob.getWorld().spawnEntity(mob.getLocation(), mob.getType());
				CreatureStack newStack = new CreatureStack(newPrincipal);
				newStack.setSize(entityStack.getSize());
				newStack.updateName(entityStack.getSize());
				Main.getEntityStacks().remove(mob.getUniqueId());
				Main.getEntityStacks().put(newPrincipal.getUniqueId(), newStack);
			} else {
				mob.setCustomName(null);
			}
		}
	}
}