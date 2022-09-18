package com.christofmeg.humbleadditions.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ObjectHolder;

public class IronChestIntegration {

	public static class IronChest {
		@ObjectHolder(registryName = "ironchest:iron_chest", value = "ironchest:iron_chest")
		public static final Block IRON_CHEST = null;
		
		@ObjectHolder(registryName = "ironchest:gold_chest", value = "ironchest:gold_chest")
		public static final Block GOLD_CHEST = null;
		
		@ObjectHolder(registryName = "ironchest:copper_chest", value = "ironchest:copper_chest")
		public static final Block COPPPER_CHEST = null;
		
		@ObjectHolder(registryName = "ironchest:diamond_chest", value = "ironchest:diamond_chest")
		public static final Block DIAMOND_CHEST = null;
		
		@ObjectHolder(registryName = "ironchest:dirt_chest", value = "ironchest:dirt_chest")
		public static final Block DIRT_CHEST = null;
		
		@ObjectHolder(registryName = "ironchest:obsidian_chest", value = "ironchest:obsidian_chest")
		public static final Block OBSIDIAN_CHEST = null;
		
		private static String ID = "ironchest";
		private static final TagKey<Item> modTag(final String path) {
			return ItemTags.create(new ResourceLocation(ID, path));
		}
		
		public static final TagKey<Item> IRON_CHEST_TAG = modTag("chests/iron");
		public static final TagKey<Item> GOLD_CHEST_TAG = modTag("chests/gold");
		public static final TagKey<Item> COPPPER_CHEST_TAG = modTag("chests/copper");
		public static final TagKey<Item> DIAMOND_CHEST_TAG = modTag("chests/diamond");
		public static final TagKey<Item> DIRT_CHEST_TAG = modTag("chests/dirt");
		public static final TagKey<Item> OBSIDIAN_CHEST_TAG = modTag("chests/obsidian");
		
	}
	
	public static class IronChestRestocked {
		@ObjectHolder(registryName = "ironchests:iron_chest", value = "ironchests:iron_chest")
		public static final Block IRON_CHEST = null;
		
		@ObjectHolder(registryName = "ironchests:gold_chest", value = "ironchests:gold_chest")
		public static final Block GOLD_CHEST = null;
		
		@ObjectHolder(registryName = "ironchests:copper_chest", value = "ironchests:copper_chest")
		public static final Block COPPPER_CHEST = null;
		
		@ObjectHolder(registryName = "ironchests:diamond_chest", value = "ironchests:diamond_chest")
		public static final Block DIAMOND_CHEST = null;
		
		@ObjectHolder(registryName = "ironchests:dirt_chest", value = "ironchests:dirt_chest")
		public static final Block DIRT_CHEST = null;
		
		@ObjectHolder(registryName = "ironchests:obsidian_chest", value = "ironchests:obsidian_chest")
		public static final Block OBSIDIAN_CHEST = null;
		
		private static String ID = "ironchest";
		private static final TagKey<Item> modTag(final String path) {
			return ItemTags.create(new ResourceLocation(ID, path));
		}
		
		public static final TagKey<Item> IRON_CHEST_TAG = modTag("chests/iron");
		public static final TagKey<Item> GOLD_CHEST_TAG = modTag("chests/gold");
		public static final TagKey<Item> COPPPER_CHEST_TAG = modTag("chests/copper");
		public static final TagKey<Item> DIAMOND_CHEST_TAG = modTag("chests/diamond");
		public static final TagKey<Item> DIRT_CHEST_TAG = modTag("chests/dirt");
		public static final TagKey<Item> OBSIDIAN_CHEST_TAG = modTag("chests/obsidian");
	}
}
