package me.StealthyTomato.StealthyStacker.StackClasses;

import org.bukkit.entity.Creature;

public class CreatureStack extends EntityStack {
		
	public CreatureStack(Creature creature) {
		super(creature);
	}
	
	@Override
	public void updateName(int size) {
		this.getPrincipalEntity().setCustomName(String.valueOf(size) + " " + this.getPrincipalEntity().getType().toString());
	}
}
