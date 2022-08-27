package com.christofmeg.humbleadditions.data.loot;

import java.util.stream.Collectors;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends net.minecraft.data.loot.BlockLoot {
	private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
	public static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
	  
    @Override
    protected void addTables() {
    	BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
    	.filter(block -> (!(block instanceof AbstractLayerBlock || block instanceof AbstractGlassBlock)))
    	.forEach(block -> {
    		dropSelf(block);
    	});
    	
    	BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
    	.filter(block -> (block instanceof AbstractGlassBlock))
    	.forEach(block -> {
    		dropWhenSilkTouch(block);
    	});
    	
    	
        
    	this.add(BlockRegistry.MOSS_LAYER_BLOCK.get(), (block) -> {
    		return LootTable.lootTable().withPool(LootPool.lootPool()
    			.when(ExplosionCondition.survivesExplosion())
	    			.add(AlternativesEntry.alternatives(AbstractLayerBlock.LAYERS
	    				.getPossibleValues(), (value) -> {
							return (LootPoolEntryContainer.Builder<?>)(value == 8 ? LootItem.lootTableItem(Blocks.MOSS_BLOCK) : LootItem.lootTableItem(BlockRegistry.MOSS_LAYER_BLOCK.get())
	    						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
	    							.setProperties(StatePropertiesPredicate.Builder.properties()
	    								.hasProperty(AbstractLayerBlock.LAYERS, value)))
	    						.apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) value.intValue()))));
	            		}) 
	    			));    		
    	});
    	
    	this.add(BlockRegistry.SAND_LAYER_BLOCK.get(), (block) -> {
    		return LootTable.lootTable().withPool(LootPool.lootPool()
    			.when(ExplosionCondition.survivesExplosion())
    			.add(AlternativesEntry.alternatives(AbstractLayerBlock.LAYERS
    				.getPossibleValues(), (value) -> {
						return (LootPoolEntryContainer.Builder<?>)(value == 8 ? LootItem.lootTableItem(Blocks.SAND) : LootItem.lootTableItem(BlockRegistry.SAND_LAYER_BLOCK.get())
    						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
    							.setProperties(StatePropertiesPredicate.Builder.properties()
    								.hasProperty(AbstractLayerBlock.LAYERS, value)))
    						.apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) value.intValue()))));
            		}) 
    			));
    	});
    	
    	this.add(BlockRegistry.RED_SAND_LAYER_BLOCK.get(), (block) -> {
    		return LootTable.lootTable().withPool(LootPool.lootPool()
    			.when(ExplosionCondition.survivesExplosion())
    			.add(AlternativesEntry.alternatives(AbstractLayerBlock.LAYERS
    				.getPossibleValues(), (value) -> {
						return (LootPoolEntryContainer.Builder<?>)(value == 8 ? LootItem.lootTableItem(Blocks.RED_SAND) : LootItem.lootTableItem(BlockRegistry.RED_SAND_LAYER_BLOCK.get())
    						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
    							.setProperties(StatePropertiesPredicate.Builder.properties()
    								.hasProperty(AbstractLayerBlock.LAYERS, value)))
    						.apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) value.intValue()))));
            		}) 
    			));
    	});
    	
    	this.add(BlockRegistry.GRAVEL_LAYER_BLOCK.get(), (block) -> {
    		return LootTable.lootTable().withPool(LootPool.lootPool()
    			.when(ExplosionCondition.survivesExplosion())
    			.add(AlternativesEntry.alternatives(AbstractLayerBlock.LAYERS
    				.getPossibleValues(), (value) -> {
						return (LootPoolEntryContainer.Builder<?>)(value == 8 ? LootItem.lootTableItem(Blocks.GRAVEL) : LootItem.lootTableItem(BlockRegistry.GRAVEL_LAYER_BLOCK.get())
    						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
    							.setProperties(StatePropertiesPredicate.Builder.properties()
    								.hasProperty(AbstractLayerBlock.LAYERS, value)))
    						.apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) value.intValue()))));
            		}) 
    			));
    	});
    	
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

}