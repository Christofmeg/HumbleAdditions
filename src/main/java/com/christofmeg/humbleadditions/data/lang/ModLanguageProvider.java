package com.christofmeg.humbleadditions.data.lang;

import java.util.function.Supplier;

import org.codehaus.plexus.util.StringUtils;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModLanguageProvider extends LanguageProvider {

	public ModLanguageProvider(DataGenerator gen, String locale) {
		super(gen, ModConstants.MOD_ID, locale);
	}

	@Override
	protected void addTranslations() {
		String locale = this.getName().replace("Languages: ", "");
		switch(locale) {
		case "en_us":			
			addBlock(BlockRegistry.CHARCOAL_BLOCK, "Charcoal Block");
			addBlock(BlockRegistry.ROSE_GOLD_BLOCK, "Rose Gold Block");
			addBlock(BlockRegistry.RAW_ROSE_GOLD_BLOCK, "Raw Rose Gold Block");
			addBlock(BlockRegistry.SMOOTH_ICE, "Smooth Ice");
			addBlock(BlockRegistry.JACK_O_SOUL_LANTERN, "Jack o'Soul Lantern");
			
			addBlock(BlockRegistry.LIMESTONE, "Limestone");
			addBlock(BlockRegistry.POLISHED_LIMESTONE_BRICKS, "Polished Limestone Bricks");
			addBlock(BlockRegistry.CHISELED_LIMESTONE, "Chiseled Limestone");
			addBlock(BlockRegistry.POLISHED_LIMESTONE, "Polished Limestone");
			addBlock(BlockRegistry.LIMESTONE_BRICKS, "Limestone Bricks");

			BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
			.filter(block -> (block instanceof WallBlock || block instanceof StairBlock || block instanceof SlabBlock))
			.forEach(block -> {
				addBlock( () -> block, 
					StringUtils.capitaliseAllWords(block.defaultBlockState().getBlock().toString()
						.replace("Block{humbleadditions:", "")
						.replace("}", "")
						.replace("_", " ")
						));
			});
				
				BlockRegistry.VANILLA_TEXTURED_BLOCKS.getEntries().stream().map(RegistryObject::get)
				.filter(block -> (block instanceof WallBlock || block instanceof StairBlock || block instanceof SlabBlock))
				.forEach(block -> {
					addBlock( () -> block, 
						StringUtils.capitaliseAllWords(block.defaultBlockState().getBlock().toString()
							.replace("Block{humbleadditions:", "").replace("}", "").replace("_", " ")));
				});
			
			BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
			.filter(block -> (block instanceof AbstractGlassBlock || block instanceof IronBarsBlock))
			.forEach(block -> {
				addBlock( () -> block, 
					StringUtils.capitaliseAllWords(block.defaultBlockState().getBlock().toString()
						.replace("Block{humbleadditions:", "")
						.replace("}", "")
						.replace("_", " ")
						));
			});
			
			BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
			.filter(block -> (block instanceof AbstractLayerBlock))
			.forEach(block -> {
				addBlock( () -> block, 
					StringUtils.capitaliseAllWords(block.defaultBlockState().getBlock().toString()
						.replace("Block{humbleadditions:", "")
						.replace("_layer_block", "")
						.replace("}", "")
						.replace("_", " ")
						));
			});
			
			add("itemGroup." + ModConstants.MOD_ID, ModConstants.MOD_NAME);
			
			addEntityType(EntityRegistry.RED_HUSK, "Red Husk");
			
			ItemRegistry.ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get)
			.forEach(item -> {
				addItem( () -> item, StringUtils.capitaliseAllWords(item.getDefaultInstance().getItem().toString()
					.replace("Item{humbleadditions:", "")
					.replace("}", "")
					.replace("milk_bottle","Bottle o' Milk")
					.replace("_", " ")
					));
			});
			
			ItemRegistry.BLOCK_ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get)
			.forEach(item -> {
				addItem( () -> item, StringUtils.capitaliseAllWords(item.getDefaultInstance().getItem().toString()
					.replace("Item{humbleadditions:", "")
					.replace("}", "")
					.replace("_", " ")
					));
			});
			

			
			break;
		default:
			break;
		}
	}
	
	public void addBlockByRegistry(Supplier<? extends Block> key, String name) {
        add(key.get(), name);
    }
	
	
}
