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
	public void updateName(int size) {
		if (principalEntity instanceof Creature)
			principalEntity.setCustomName(String.valueOf(size) + " " + principalEntity.getType().toString());
		if (principalEntity instanceof Item)
			principalEntity.setCustomName(String.valueOf(size) + " " + ((Item) principalEntity).getItemStack().getType().toString());
		principalEntity.setCustomNameVisible(true);
	}

	
	@Override
	public void addNearbyEntitiesToStack(int rx, int ry, int rz) {
		List<Entity> nearbyEntities = principalEntity.getNearbyEntities(rx, ry, rz);
		for (Entity entity : nearbyEntities)
			if(entity.getType().equals(principalEntity.getType())) {
				incrementEntityStack();
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
