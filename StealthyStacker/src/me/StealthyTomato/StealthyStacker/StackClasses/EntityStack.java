package me.StealthyTomato.StealthyStacker.StackClasses;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;

public abstract class EntityStack {
	
	private int size;
	
	private String name;

	private Entity principalEntity;
	
	public EntityStack(Entity entity) {
		this.size = 1;
		this.principalEntity = entity;
	}
	
	public void updateName(int size) {
		if (principalEntity instanceof Creature)
			principalEntity.setCustomName(String.valueOf(size) + " " + principalEntity.getType().toString());
		if (principalEntity instanceof Item)
			principalEntity.setCustomName(String.valueOf(size) + " " + ((Item) principalEntity).getItemStack().getType().toString());
		principalEntity.setCustomNameVisible(true);
	}

	public void incrementEntityStack() {
		size++;
		updateName(size);
	}
	
	public void decrementEntityStack() {
		size--;
		if(size > 1)
			updateName(size);
		else
			principalEntity.setCustomName(null);
	}
	
	public void addNearbyEntitiesToStack(int rx, int ry, int rz) {
		List<Entity> nearbyEntities = principalEntity.getNearbyEntities(rx, ry, rz);
		for (Entity entity : nearbyEntities)
			if(entity.getType().equals(principalEntity.getType())) {
				incrementEntityStack();
				entity.remove();
			}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity getPrincipalEntity() {
		return principalEntity;
	}

	public void setPrincipalEntity(Entity principalEntity) {
		this.principalEntity = principalEntity;
	}
}
