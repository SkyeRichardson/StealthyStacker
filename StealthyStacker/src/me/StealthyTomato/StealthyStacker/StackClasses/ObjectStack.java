package me.StealthyTomato.StealthyStacker.StackClasses;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.Metadatable;

public abstract class ObjectStack {
	
	private int size;
	
	private String name;

	private Metadatable principalObject;
	
	public ObjectStack(Metadatable object) {
		this.size = 0;
		this.principalObject = object;
	}
	
	public abstract void updateName(int size);

	public void incrementObjectStack() {
		size++;
		updateName(size);
	}
	
	public void decrementObjectStack() {
		size--;
		if(size > 0)
			updateName(size);
		else if(size == 0)
			removeName();
		else
			removeObjectStack();
	}

	public abstract void removeName();
	
	public abstract void removeObjectStack();
	
	public abstract void addNearbyObjectsToStack(int... radii);

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		updateName(size);
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
