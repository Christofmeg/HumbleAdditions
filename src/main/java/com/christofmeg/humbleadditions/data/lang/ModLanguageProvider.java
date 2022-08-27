package com.christofmeg.humbleadditions.data.lang;

import org.codehaus.plexus.util.StringUtils;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
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
			addBlock(BlockRegistry.SMOOTH_ICE, "Smooth Ice");
			addBlock(BlockRegistry.JACK_O_SOUL_LANTERN, "Jack o'Soul Lantern");

			BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
			.filter(block -> (block instanceof AbstractGlassBlock | block instanceof IronBarsBlock))
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
			
			addItem(ItemRegistry.MILK_BOTTLE, "Bottle o' Milk");
			
			addItem(ItemRegistry.NETHERITE_HORSE_ARMOR, "Netherite Horse Armor");
			
			addItem(ItemRegistry.RED_HUSK_SPAWN_EGG, "Red Husk Spawn Egg");


			
			break;
		default:
			break;
		}
	}
	
	
}
