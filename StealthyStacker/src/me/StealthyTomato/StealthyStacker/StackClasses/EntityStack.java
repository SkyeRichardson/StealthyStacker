package me.StealthyTomato.StealthyStacker.StackClasses;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;

public abstract class EntityStack extends ObjectStack {

	private Entity principalEntity;
	
	public EntityStack(Entity entity) {
		super(entity);
		principalEntity = (Entity) this.getPrincipalObject();
	}
	
	@Override
	public void removeName() {
		principalEntity.setCustomName(null);
	}
	
	@Override
	public void addNearbyObjectsToStack(int... radii) {
		List<Entity> nearbyEntities = principalEntity.getNearbyEntities(radii[0], radii[1], radii[2]);
		for (Entity entity : nearbyEntities)
			if(entity.getType().equals(principalEntity.getType())) {
				incrementObjectStack();
				entity.remove();
			}
	}

	public Entity getPrincipalEntity() {
		return principalEntity;
	}

	public void setPrincipalEntity(Entity principalEntity) {
		this.principalEntity = principalEntity;
	}
}
