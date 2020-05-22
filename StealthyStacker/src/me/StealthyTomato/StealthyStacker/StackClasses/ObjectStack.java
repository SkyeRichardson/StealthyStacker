package me.StealthyTomato.StealthyStacker.StackClasses;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.metadata.Metadatable;

public abstract class ObjectStack {
	
	private int size;
	
	private String name;

	private Metadatable principalObject;
	
	public ObjectStack(Metadatable object) {
		this.size = 1;
		this.principalObject = object;
	}
	
	public abstract void updateName(int size);

	public void incrementEntityStack() {
		size++;
		updateName(size);
	}
	
	public void decrementEntityStack() {
		size--;
		if(size > 1)
			updateName(size);
		else
			removeName();
	}

	public abstract void removeName();
	
	public abstract void addNearbyEntitiesToStack(int rx, int ry, int rz);

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

	public Metadatable getPrincipalObject() {
		return principalObject;
	}

	public void setPrincipalEntity(Entity principalEntity) {
		this.principalObject = principalEntity;
	}
}
