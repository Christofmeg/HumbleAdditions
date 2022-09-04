package com.christofmeg.humbleadditions.data.loot;

import java.util.stream.Collectors;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.QuickSandBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLotTablesVanillaTexturedBlocks extends net.minecraft.data.loot.BlockLoot {
	
    @Override
    protected void addTables() {
    	BlockRegistry.VANILLA_TEXTURED_BLOCKS.getEntries().stream().map(RegistryObject::get)
    	.filter(block -> (!(block instanceof AbstractLayerBlock || block instanceof AbstractGlassBlock || block instanceof QuickSandBlock)))
    	.forEach(block -> {
    		dropSelf(block);
    	});
    	
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.VANILLA_TEXTURED_BLOCKS.getEntries().stream().map(RegistryObject::get)
        	.collect(Collectors.toList());
    }


}