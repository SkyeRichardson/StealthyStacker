package me.StealthyTomato.StealthyStacker.Utils;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import me.StealthyTomato.StealthyStacker.Main;
import me.StealthyTomato.StealthyStacker.StackClasses.CreatureStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class EntityStackUtils {
	public static EntityStack findLargestEntityStackNearEntity(Entity entity, int rx, int ry, int rz) {
		EntityStack largestEntityStack = null;
		List<Entity> nearbyEntities = entity.getNearbyEntities(rx, ry, rz);
		for (Entity ent : nearbyEntities)
			if(Main.getEntityStacks().get(ent.getUniqueId()) != null)
				if(largestEntityStack == null || Main.getEntityStacks().get(ent.getUniqueId()).getSize() > largestEntityStack.getSize())
					largestEntityStack = Main.getEntityStacks().get(ent.getUniqueId());
		return largestEntityStack;
	}
}
