package com.christofmeg.humbleadditions.data.lang;

import java.util.function.Supplier;

import org.codehaus.plexus.util.StringUtils;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.registry.PotionRegistry;
import com.christofmeg.humbleadditions.registry.PotionRegistry.PotionEffectRegistry;
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
			addBlock(BlockRegistry.BLAZE_ROD_BLOCK, "Block of Blaze Rods");
			addBlock(BlockRegistry.CHARCOAL_BLOCK, "Charcoal Block");
			addBlock(BlockRegistry.ROSE_GOLD_BLOCK, "Rose Gold Block");
			addBlock(BlockRegistry.RAW_ROSE_GOLD_BLOCK, "Raw Rose Gold Block");
			addBlock(BlockRegistry.SMOOTH_ICE, "Smooth Ice");
			addBlock(BlockRegistry.JACK_O_SOUL_LANTERN, "Jack o'Soul Lantern");
			addBlock(BlockRegistry.PACKED_SNOW, "Packed Snow");
			addBlock(BlockRegistry.SNOW_BRICKS, "Snow Bricks");

			addBlock(BlockRegistry.LIMESTONE, "Limestone");
			addBlock(BlockRegistry.POLISHED_LIMESTONE_BRICKS, "Polished Limestone Bricks");
			addBlock(BlockRegistry.CHISELED_LIMESTONE, "Chiseled Limestone");
			addBlock(BlockRegistry.POLISHED_LIMESTONE, "Polished Limestone");
			addBlock(BlockRegistry.LIMESTONE_BRICKS, "Limestone Bricks");

			addBlock(BlockRegistry.PLAYER_PRESSURE_PLATE, "Player Pressure Plate");

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
			addEntityType(EntityRegistry.SNOW_GOLEM, "Snow Golem");

			addItem(ItemRegistry.AGGRESSIVE_PANDA_SPAWN_EGG, "Agressive Panda Spawn Egg");
			addItem(ItemRegistry.KILLER_BUNNY_SPAWN_EGG, "Killer Bunny Spawn Egg");
			addItem(ItemRegistry.LAZY_PANDA_SPAWN_EGG, "Lazy Panda Spawn Egg");
			addItem(ItemRegistry.PLAYFUL_PANDA_SPAWN_EGG, "Playful Panda Spawn Egg");
			addItem(ItemRegistry.WEAK_PANDA_SPAWN_EGG, "Weak Panda Spawn Egg");
			addItem(ItemRegistry.WORRIED_PANDA_SPAWN_EGG, "Worried Panda Spawn Egg");
			addItem(ItemRegistry.BABY_VILLAGER_SPAWN_EGG, "Baby Villager Spawn Egg");
			addItem(ItemRegistry.CHARGED_CREEPER_SPAWN_EGG, "Charged Creeper Spawn Egg");
			addItem(ItemRegistry.SHEARED_SHEEP_SPAWN_EGG, "Sheared Sheep Spawn Egg");
			addItem(ItemRegistry.SCREAMING_GOAT_SPAWN_EGG, "Screaming Goat Spawn Egg");
			addItem(ItemRegistry.JACK_O_SOUL_SNOW_GOLEM_SPAWN_EGG, "Jack o'Soul Snow Golem Spawn Egg");
			addItem(ItemRegistry.JACK_O_SNOW_GOLEM_SPAWN_EGG, "Jack o'Snow Golem Spawn Egg");
			addItem(ItemRegistry.SNOW_GOLEM_SPAWN_EGG, "Snow Golem Spawn Egg");


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

			add("item.minecraft.potion.effect." + PotionRegistry.CHORUS.getId().toString().replace(":", "_"), "Potion of Chorus Teleport");
			add("item.minecraft.lingering_potion.effect." + PotionRegistry.CHORUS.getId().toString().replace(":", "_"), "Lingering Potion of Chorus Teleport");
			add("item.minecraft.splash_potion.effect." + PotionRegistry.CHORUS.getId().toString().replace(":", "_"), "Splash Potion of Chorus Teleport");
			add("item.minecraft.tipped_arrow.effect." + ModConstants.MOD_ID + "_" + "chorus", "Arrow of Chorus Teleport");
			addEffect(()-> PotionEffectRegistry.CHORUS.get(), "Chorus Teleport");

			break;
		default:
			break;
		}
	}

	public void addBlockByRegistry(Supplier<? extends Block> key, String name) {
		add(key.get(), name);
	}

}
