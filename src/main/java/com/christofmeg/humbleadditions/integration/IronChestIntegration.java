package com.christofmeg.humbleadditions.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class IronChestIntegration {

	public static class IronChest {

		private static String ID = "ironchest";
		public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);

		@SuppressWarnings("unused")
		private static final TagKey<Item> modItemTag(final String path) {
			return ItemTags.create(new ResourceLocation(ID, path));
		}

		private static final TagKey<Block> modBlockTag(final String path) {
			return BlockTags.create(new ResourceLocation(ID, path));
		}

		@ObjectHolder(registryName = "ironchest:iron_chest", value = "ironchest:iron_chest")
		//	public static final Block IRON_CHEST = register("iron_chest", new Block(BlockBehaviour.Properties.of(Material.STONE)));
		public static final RegistryObject<Block> IRON_CHEST = BLOCKS.register("iron_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchest:gold_chest", value = "ironchest:gold_chest")
		public static final RegistryObject<Block> GOLD_CHEST  = BLOCKS.register("gold_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchest:copper_chest", value = "ironchest:copper_chest")
		public static final RegistryObject<Block> COPPPER_CHEST  = BLOCKS.register("copper_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchest:diamond_chest", value = "ironchest:diamond_chest")
		public static final RegistryObject<Block> DIAMOND_CHEST  = BLOCKS.register("diamond_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchest:dirt_chest", value = "ironchest:dirt_chest")
		public static final RegistryObject<Block> DIRT_CHEST  = BLOCKS.register("dirt_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchest:obsidian_chest", value = "ironchest:obsidian_chest")
		public static final RegistryObject<Block> OBSIDIAN_CHEST  = BLOCKS.register("obsidian_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		public static final TagKey<Block> IRON_CHEST_TAG = modBlockTag("chests/iron");
		public static final TagKey<Block> GOLD_CHEST_TAG = modBlockTag("chests/gold");
		public static final TagKey<Block> COPPPER_CHEST_TAG = modBlockTag("chests/copper");
		public static final TagKey<Block> DIAMOND_CHEST_TAG = modBlockTag("chests/diamond");
		public static final TagKey<Block> DIRT_CHEST_TAG = modBlockTag("chests/dirt");
		public static final TagKey<Block> OBSIDIAN_CHEST_TAG = modBlockTag("chests/obsidian");


	}

	public static class IronChestRestocked {
		private static String ID = "ironchest";
		public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);

		@ObjectHolder(registryName = "ironchests:iron_chest", value = "ironchests:iron_chest")
		public static final RegistryObject<Block> IRON_CHEST = BLOCKS.register("iron_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchests:gold_chest", value = "ironchests:gold_chest")
		public static final RegistryObject<Block> GOLD_CHEST  = BLOCKS.register("gold_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchests:copper_chest", value = "ironchests:copper_chest")
		public static final RegistryObject<Block> COPPPER_CHEST  = BLOCKS.register("copper_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchests:diamond_chest", value = "ironchests:diamond_chest")
		public static final RegistryObject<Block> DIAMOND_CHEST  = BLOCKS.register("diamond_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchests:dirt_chest", value = "ironchests:dirt_chest")
		public static final RegistryObject<Block> DIRT_CHEST  = BLOCKS.register("dirt_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

		@ObjectHolder(registryName = "ironchests:obsidian_chest", value = "ironchests:obsidian_chest")
		public static final RegistryObject<Block> OBSIDIAN_CHEST  = BLOCKS.register("obsidian_chest", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));


		@SuppressWarnings("unused")
		private static final TagKey<Item> modItemTag(final String path) {
			return ItemTags.create(new ResourceLocation(ID, path));
		}

		private static final TagKey<Block> modBlockTag(final String path) {
			return BlockTags.create(new ResourceLocation(ID, path));
		}

		public static final TagKey<Block> IRON_CHEST_TAG = modBlockTag("chests/iron");
		public static final TagKey<Block> GOLD_CHEST_TAG = modBlockTag("chests/gold");
		public static final TagKey<Block> COPPPER_CHEST_TAG = modBlockTag("chests/copper");
		public static final TagKey<Block> DIAMOND_CHEST_TAG = modBlockTag("chests/diamond");
		public static final TagKey<Block> DIRT_CHEST_TAG = modBlockTag("chests/dirt");
		public static final TagKey<Block> OBSIDIAN_CHEST_TAG = modBlockTag("chests/obsidian");
	}
}
