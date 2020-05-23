package me.StealthyTomato.StealthyStacker.StackClasses;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import me.StealthyTomato.StealthyStacker.Utils.ObjectStackUtils;

public class BlockStack extends ObjectStack {

	private Block principalBlock;
	
	private ArmorStand nameTag;

	public BlockStack(Block block) {
		super(block);
		principalBlock = (Block) getPrincipalObject();
		nameTag = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().add(0.5, 0.5, 0.5), EntityType.ARMOR_STAND);
		nameTag.setVisible(false);
		nameTag.setGravity(false);
		nameTag.setSmall(true);
	}
	
	@Override
	public void removeName() {
		
	}
	
	@Override
	public void updateName(int size) {
		nameTag.setCustomName(String.valueOf(size) +  " " + principalBlock.getType());
		nameTag.setCustomNameVisible(true);
	}

	@Override
	public void addNearbyObjectsToStack(int... radii) {
		List<Block> adjacentBlocks = ObjectStackUtils.getAdjacentBlocks(principalBlock);
		for (Block block : adjacentBlocks)
			if(block.getType().equals(principalBlock.getType())) {
				incrementObjectStack();
				block.setType(Material.AIR);
			}
	}

	public Block getPrincipalBlock() {
		return principalBlock;
	}

	public void setPrincipalBlock(Block principalBlock) {
		this.principalBlock = principalBlock;
	}
	
	public ArmorStand getNameTag() {
		return nameTag;
	}

	public void setNameTag(ArmorStand nameTag) {
		this.nameTag = nameTag;
	}
}
