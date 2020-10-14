package me.StealthyTomato.StealthyStacker.StackClasses;

import org.bukkit.entity.Item;

public class DroppedItemStack extends EntityStack {
	public DroppedItemStack(Item item) {
		super(item);
	}
	
	@Override
	public void updateName(int size) {
		this.getPrincipalEntity().setCustomName(String.valueOf(size+ 1) + " " + ((Item) this.getPrincipalEntity()).getItemStack().getType().toString());
		this.getPrincipalEntity().setCustomNameVisible(true);
	}
}
