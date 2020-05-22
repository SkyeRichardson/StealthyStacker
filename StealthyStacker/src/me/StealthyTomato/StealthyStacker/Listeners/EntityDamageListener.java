package me.StealthyTomato.StealthyStacker.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class EntityDamageListener implements Listener {
	@EventHandler
    public void onDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Creature) {
        Creature mob = (Creature) event.getEntity();
        EntityStack mobStack = Main.getEntityStacks().get(mob.getUniqueId());
	        if (mobStack != null && mobStack.getSize() > 1 && mob.getHealth() - event.getDamage() <= 0 ) {
	            event.setCancelled(true);
	            mobStack.decrementEntityStack();
	            mob.setHealth(mob.getMaxHealth());
	            Creature mobToDieImmediately = (Creature) mob.getWorld().spawnEntity(mob.getLocation(), mob.getType());
	            mobToDieImmediately.damage(mob.getMaxHealth());
	        }
		}
	}
}