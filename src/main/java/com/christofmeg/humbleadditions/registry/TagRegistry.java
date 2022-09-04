package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
	
	public static final class Blocks {
		private static final TagKey<Block> forgeTag(final String path) {
			return BlockTags.create(new ResourceLocation("forge", path));
		}
		@SuppressWarnings("unused")
		private static final TagKey<Block> vanillaTag(final String path) {
			return BlockTags.create(new ResourceLocation("minecraft", path));
		}
		@SuppressWarnings("unused")
		private static final TagKey<Block> modTag(final String path) {
			return BlockTags.create(new ResourceLocation(ModConstants.MOD_ID, path));
		}
		
		public static final TagKey<Block> ORES_ENDORIUM = forgeTag("ores/endorium");
		
		public static final TagKey<Block> STORAGE_BLOCKS_CHARCOAL = forgeTag("storage_blocks/charcoal");
		public static final TagKey<Block> STORAGE_BLOCKS_ROSE_GOLD = forgeTag("storage_blocks/rose_gold");
		public static final TagKey<Block> STORAGE_BLOCKS_RAW_ENDORIUM = forgeTag("storage_blocks/raw_endorium");
		public static final TagKey<Block> STORAGE_BLOCKS_ENDORIUM = forgeTag("storage_blocks/endorium");
		public static final TagKey<Block> STORAGE_BLOCKS_ENDORITE = forgeTag("storage_blocks/endorite");
		public static final TagKey<Block> STORAGE_BLOCKS_RAW_ROSE_GOLD = forgeTag("storage_blocks/raw_rose_gold");

	}
	
	public static final class Items {
		@SuppressWarnings("unused")
		private static final TagKey<Item> vanillaTag(final String path) {
			return ItemTags.create(new ResourceLocation("minecraft", path));
		}
		@SuppressWarnings("unused")
		private static final TagKey<Item> modTag(final String path) {
			return ItemTags.create(new ResourceLocation(ModConstants.MOD_ID, path));
		}
		private static final TagKey<Item> forgeTag(final String path) {
			return ItemTags.create(new ResourceLocation("forge", path));
		}
		
		public static final TagKey<Item> ORES_ENDORIUM = forgeTag("ores/endorium");
		
		public static final TagKey<Item> STORAGE_BLOCKS_CHARCOAL = forgeTag("storage_blocks/charcoal");
		public static final TagKey<Item> STORAGE_BLOCKS_ROSE_GOLD = forgeTag("storage_blocks/rose_gold");
		public static final TagKey<Item> STORAGE_BLOCKS_RAW_ENDORIUM = forgeTag("storage_blocks/raw_endorium");
		public static final TagKey<Item> STORAGE_BLOCKS_ENDORIUM = forgeTag("storage_blocks/endorium");
		public static final TagKey<Item> STORAGE_BLOCKS_ENDORITE = forgeTag("storage_blocks/endorite");
		public static final TagKey<Item> STORAGE_BLOCKS_RAW_ROSE_GOLD = forgeTag("storage_blocks/raw_rose_gold");
		
		public static final TagKey<Item> ROSE_GOLD_INGOT = forgeTag("ingots/rose_gold");
		public static final TagKey<Item> RAW_ENDORIUM = forgeTag("raw_materials/endorium");
		public static final TagKey<Item> ENDORIUM_INGOT = forgeTag("ingots/endorium");
		public static final TagKey<Item> ENDORITE_INGOT = forgeTag("ingots/endorite");
		public static final TagKey<Item> RAW_ROSE_GOLD = forgeTag("raw_materials/rose_gold");
		
		
	}
	
	public static final class Biomes {
		private static TagKey<Biome> create(String p_207631_) {
		      return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(p_207631_));
		}
		
		@SuppressWarnings("unused")
		private static final TagKey<Biome> vanillaTag(final String path) {
			return TagRegistry.Biomes.create((new ResourceLocation("minecraft", path).toString()));
		}
		@SuppressWarnings("unused")
		private static final TagKey<Biome> modTag(final String path) {
			return TagRegistry.Biomes.create((new ResourceLocation(ModConstants.MOD_ID, path).toString()));
		}
		@SuppressWarnings("unused")
		private static final TagKey<Biome> forgeTag(final String path) {
			return TagRegistry.Biomes.create((new ResourceLocation("forge", path).toString()));
		}
		
		
		
//		public static final TagKey<Biome> RED_HUSK_BIOMES = modTag("red_husk_biomes");
		
		
	}
	
	public static final class EntityTypes {
		@SuppressWarnings("unused")
		private static final TagKey<net.minecraft.world.entity.EntityType<?>> vanillaTag(final String path) {
			return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("minecraft", path));
		}
		private static final TagKey<net.minecraft.world.entity.EntityType<?>> modTag(final String path) {
			return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(ModConstants.MOD_ID, path));
		}
		@SuppressWarnings("unused")
		private static final TagKey<net.minecraft.world.entity.EntityType<?>> forgeTag(final String path) {
			return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge", path));
		}
		
		public static final TagKey<net.minecraft.world.entity.EntityType<?>> QUICK_SAND_WALKABLE_MOBS = modTag("quick_sand_walkable_mobs");
		
	}	
		

		
		
		
		
		
	
	

}
