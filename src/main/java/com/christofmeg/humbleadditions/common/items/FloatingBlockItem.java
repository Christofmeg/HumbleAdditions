package com.christofmeg.humbleadditions.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class FloatingBlockItem extends BlockItem {

	public FloatingBlockItem(Block block, Properties properties) {
		super(block, properties);
		properties.fireResistant();
	}

}
