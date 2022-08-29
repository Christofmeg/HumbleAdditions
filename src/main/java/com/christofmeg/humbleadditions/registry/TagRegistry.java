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
		
		public static final TagKey<Block> STORAGE_BLOCKS_CHARCOAL = forgeTag("storage_blocks/charcoal");

		
		
		
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
		
		public static final TagKey<Item> STORAGE_BLOCKS_CHARCOAL = forgeTag("storage_blocks/charcoal");
		
		
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

}
