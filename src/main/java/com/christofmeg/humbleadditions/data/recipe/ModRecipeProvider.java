package com.christofmeg.humbleadditions.data.recipe;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.common.collect.ImmutableMap;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import net.minecraftforge.fml.loading.StringUtils;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
	
	public ModRecipeProvider(DataGenerator pGenerator) {
		super(pGenerator);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Recipes";
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
		BlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((block) -> {
	         generateRecipes(consumer, block);
		});	
			
		this.addShapedRecipes(consumer);	
		this.addBlastingRecipes(consumer);
		this.addSmeltingRecipes(consumer);
		this.addStonecuttingRecipes(consumer);
		
		this.addShapelessRecipes(consumer);
		this.addSmithingRecipes(consumer);
		this.addSlabRecipes(consumer);
		this.addStairsRecipes(consumer);
		this.addWallsRecipes(consumer);
	}
	
	private void addSmithingRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "smithing/";
		String stone = "stone/";
		String iron = "iron/";
		String gold = "gold/";
		String diamond = "diamond/";
		String netherite = "netherite/";
		String chain = "chain/";
		String horse = "horse_armor/";
		
		stoneSmithing(consumer, modLoc(folder + stone + "axe"), Items.WOODEN_AXE, Items.STONE_AXE);
		stoneSmithing(consumer, modLoc(folder + stone + "hoe"), Items.WOODEN_HOE, Items.STONE_HOE);
		stoneSmithing(consumer, modLoc(folder + stone + "pickaxe"), Items.WOODEN_PICKAXE, Items.STONE_PICKAXE);
		stoneSmithing(consumer, modLoc(folder + stone + "shovel"), Items.WOODEN_SHOVEL, Items.STONE_SHOVEL);
		stoneSmithing(consumer, modLoc(folder + stone + "sword"), Items.WOODEN_SWORD, Items.STONE_SWORD);
		
		goldSmithing(consumer, modLoc(folder + gold + "axe"), Items.STONE_AXE, Items.GOLDEN_AXE);
		goldSmithing(consumer, modLoc(folder + gold + "hoe"), Items.STONE_HOE, Items.GOLDEN_HOE);
		goldSmithing(consumer, modLoc(folder + gold + "pickaxe"), Items.STONE_PICKAXE, Items.GOLDEN_PICKAXE);
		goldSmithing(consumer, modLoc(folder + gold + "shovel"), Items.STONE_SHOVEL, Items.GOLDEN_SHOVEL);
		goldSmithing(consumer, modLoc(folder + gold + "sword"), Items.STONE_SWORD, Items.GOLDEN_SWORD);
		
		ironSmithing(consumer, modLoc(folder + iron + "axe"), Items.GOLDEN_AXE, Items.IRON_AXE);
		ironSmithing(consumer, modLoc(folder + iron + "hoe"), Items.GOLDEN_HOE, Items.IRON_HOE);
		ironSmithing(consumer, modLoc(folder + iron + "pickaxe"), Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE);
		ironSmithing(consumer, modLoc(folder + iron + "shovel"), Items.GOLDEN_SHOVEL, Items.IRON_SHOVEL);
		ironSmithing(consumer, modLoc(folder + iron + "sword"), Items.GOLDEN_SWORD, Items.IRON_SWORD);
		
		diamondSmithing(consumer, modLoc(folder + diamond + "axe"), Items.IRON_AXE, Items.DIAMOND_AXE);
		diamondSmithing(consumer, modLoc(folder + diamond + "hoe"), Items.IRON_HOE, Items.DIAMOND_HOE);
		diamondSmithing(consumer, modLoc(folder + diamond + "pickaxe"), Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE);
		diamondSmithing(consumer, modLoc(folder + diamond + "shovel"), Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL);
		diamondSmithing(consumer, modLoc(folder + diamond + "sword"), Items.IRON_SWORD, Items.DIAMOND_SWORD);
		
		ironSmithing(consumer, modLoc(folder + chain + "helmet"), Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET);
		ironSmithing(consumer, modLoc(folder + chain + "chestplate"), Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE);
		ironSmithing(consumer, modLoc(folder + chain + "leggings"), Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS);
		ironSmithing(consumer, modLoc(folder + chain + "boots"), Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS);
		
		goldSmithing(consumer, modLoc(folder + gold + "helmet"), Items.CHAINMAIL_HELMET, Items.GOLDEN_HELMET);
		goldSmithing(consumer, modLoc(folder + gold + "chestplate"), Items.CHAINMAIL_CHESTPLATE, Items.GOLDEN_CHESTPLATE);
		goldSmithing(consumer, modLoc(folder + gold + "leggings"), Items.CHAINMAIL_LEGGINGS, Items.GOLDEN_LEGGINGS);
		goldSmithing(consumer, modLoc(folder + gold + "boots"), Items.CHAINMAIL_BOOTS, Items.GOLDEN_BOOTS);
		
		ironSmithing(consumer, modLoc(folder + iron + "helmet"), Items.GOLDEN_HELMET, Items.IRON_HELMET);
		ironSmithing(consumer, modLoc(folder + iron + "chestplate"), Items.GOLDEN_CHESTPLATE, Items.IRON_CHESTPLATE);
		ironSmithing(consumer, modLoc(folder + iron + "leggings"), Items.GOLDEN_LEGGINGS, Items.IRON_LEGGINGS);
		ironSmithing(consumer, modLoc(folder + iron + "boots"), Items.GOLDEN_BOOTS, Items.IRON_BOOTS);
		
		diamondSmithing(consumer, modLoc(folder + diamond + "helmet"), Items.IRON_HELMET, Items.DIAMOND_HELMET);
		diamondSmithing(consumer, modLoc(folder + diamond + "chestplate"), Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
		diamondSmithing(consumer, modLoc(folder + diamond + "leggings"), Items.IRON_LEGGINGS, Items.DIAMOND_LEGGINGS);
		diamondSmithing(consumer, modLoc(folder + diamond + "boots"), Items.IRON_BOOTS, Items.DIAMOND_BOOTS);
		
		ironSmithing(consumer, modLoc(folder + horse + iron), Items.LEATHER_HORSE_ARMOR, Items.IRON_HORSE_ARMOR);
		goldSmithing(consumer, modLoc(folder + horse + gold), Items.IRON_HORSE_ARMOR, Items.GOLDEN_HORSE_ARMOR);
		diamondSmithing(consumer, modLoc(folder + horse + diamond), Items.GOLDEN_HORSE_ARMOR, Items.DIAMOND_HORSE_ARMOR);
		netheriteSmithing(consumer, modLoc(folder + horse + netherite), Items.DIAMOND_HORSE_ARMOR, ItemRegistry.NETHERITE_HORSE_ARMOR.get());
		
	}
	
	private void addSlabRecipes(Consumer<FinishedRecipe> consumer) {
		
		String folder = "shaped/full_blocks/";
		
		fullBlockBuilder(Blocks.SMOOTH_STONE, Ingredient.of(Blocks.SMOOTH_STONE_SLAB))
		.unlockedBy("has_smooth_stone_slab",  has(Items.SMOOTH_STONE_SLAB))
		.save(consumer, modLoc(folder + "smooth_stone_slab"));
		
		fullBlockBuilder(BlockRegistry.LIMESTONE.get(), Ingredient.of(BlockRegistry.LIMESTONE_SLAB.get()))
		.unlockedBy("has_limestone",  has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "limestone_slab"));

		fullBlockBuilder(BlockRegistry.LIMESTONE_BRICKS.get(), Ingredient.of(BlockRegistry.LIMESTONE_BRICKS_SLAB.get()))
		.unlockedBy("has_limestone",  has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "limestone_bricks_slab"));

		fullBlockBuilder(BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get(), Ingredient.of(BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get()))
		.unlockedBy("has_limestone",  has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "polished_limestone_bricks_slab"));
		
		ShapedRecipeBuilder.shaped(Items.ACACIA_SLAB, 24)
		.define('W', ItemTags.ACACIA_LOGS)
		.pattern("WWW")
		.unlockedBy("has_acacia_logs", has(ItemTags.ACACIA_LOGS))
		.save(consumer, modLoc(folder + "acacia/slab"));
		
		ShapedRecipeBuilder.shaped(Items.OAK_SLAB, 24)
		.define('W', ItemTags.OAK_LOGS)
		.pattern("WWW")
		.unlockedBy("has_oak_logs", has(ItemTags.OAK_LOGS))
		.save(consumer, modLoc(folder + "oak/slab"));
		
		ShapedRecipeBuilder.shaped(Items.BIRCH_SLAB, 24)
		.define('W', ItemTags.BIRCH_LOGS)
		.pattern("WWW")
		.unlockedBy("has_birch_logs", has(ItemTags.BIRCH_LOGS))
		.save(consumer, modLoc(folder + "birch/slab"));
		
		ShapedRecipeBuilder.shaped(Items.JUNGLE_SLAB, 24)
		.define('W', ItemTags.JUNGLE_LOGS)
		.pattern("WWW")
		.unlockedBy("has_jungle_logs", has(ItemTags.JUNGLE_LOGS))
		.save(consumer, modLoc(folder + "jungle/slab"));
		
		ShapedRecipeBuilder.shaped(Items.SPRUCE_SLAB, 24)
		.define('W', ItemTags.SPRUCE_LOGS)
		.pattern("WWW")
		.unlockedBy("has_spruce_logs", has(ItemTags.SPRUCE_LOGS))
		.save(consumer, modLoc(folder + "spruce/slab"));
		
		ShapedRecipeBuilder.shaped(Items.DARK_OAK_SLAB, 24)
		.define('W', ItemTags.DARK_OAK_LOGS)
		.pattern("WWW")
		.unlockedBy("has_dark_oak_logs", has(ItemTags.DARK_OAK_LOGS))
		.save(consumer, modLoc(folder + "dark_oak/slab"));
		
		ShapedRecipeBuilder.shaped(Items.WARPED_SLAB, 24)
		.define('W', ItemTags.WARPED_STEMS)
		.pattern("WWW")
		.unlockedBy("has_warped_stems", has(ItemTags.WARPED_STEMS))
		.save(consumer, modLoc(folder + "warped/slab"));

		ShapedRecipeBuilder.shaped(Items.CRIMSON_SLAB, 24)
		.define('W', ItemTags.CRIMSON_STEMS)
		.pattern("WWW")
		.unlockedBy("has_crimson_stems", has(ItemTags.CRIMSON_STEMS))
		.save(consumer, modLoc(folder + "crimson/slab"));
		
		ShapedRecipeBuilder.shaped(Items.MANGROVE_SLAB, 24)
		.define('W', ItemTags.WARPED_STEMS)
		.pattern("WWW")
		.unlockedBy("has_mangrove_logs", has(ItemTags.MANGROVE_LOGS))
		.save(consumer, modLoc(folder + "mangrove/slab"));
		
		ShapedRecipeBuilder.shaped(BlockRegistry.LIMESTONE_SLAB.get(), 6)
		.define('W', BlockRegistry.LIMESTONE.get())
		.pattern("WWW")
		.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "limestone/slab"));
		
		ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE_SLAB.get(), 6)
		.define('W', BlockRegistry.POLISHED_LIMESTONE.get())
		.pattern("WWW")
		.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "polished_limestone/slab"));

		ShapedRecipeBuilder.shaped(BlockRegistry.LIMESTONE_BRICKS_SLAB.get(), 6)
		.define('W', BlockRegistry.LIMESTONE_BRICKS.get())
		.pattern("WWW")
		.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "limestone_bricks/slab"));
		
		ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get(), 6)
		.define('W', BlockRegistry.POLISHED_LIMESTONE_BRICKS.get())
		.pattern("WWW")
		.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
		.save(consumer, modLoc(folder + "polished_limestone_bricks/slab"));

	}
	
	private void addStairsRecipes(Consumer<FinishedRecipe> consumer) {
	
		String folder = "shaped/";
		
		stairBuilder(Blocks.PURPUR_STAIRS, Ingredient.of(Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR)).unlockedBy("has_purpur_block", has(Blocks.PURPUR_BLOCK)).save(consumer);
		stairBuilder(Blocks.QUARTZ_STAIRS, Ingredient.of(Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR)).unlockedBy("has_chiseled_quartz_block", has(Blocks.CHISELED_QUARTZ_BLOCK)).unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK)).unlockedBy("has_quartz_pillar", has(Blocks.QUARTZ_PILLAR)).save(consumer);
		stairBuilder(Blocks.RED_SANDSTONE_STAIRS, Ingredient.of(Blocks.RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE)).unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE)).unlockedBy("has_chiseled_red_sandstone", has(Blocks.CHISELED_RED_SANDSTONE)).unlockedBy("has_cut_red_sandstone", has(Blocks.CUT_RED_SANDSTONE)).save(consumer);
		stairBuilder(Blocks.SANDSTONE_STAIRS, Ingredient.of(Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE)).unlockedBy("has_sandstone", has(Blocks.SANDSTONE)).unlockedBy("has_chiseled_sandstone", has(Blocks.CHISELED_SANDSTONE)).unlockedBy("has_cut_sandstone", has(Blocks.CUT_SANDSTONE)).save(consumer);
		stairBuilder(Blocks.STONE_BRICK_STAIRS, Ingredient.of(Blocks.STONE_BRICKS)).unlockedBy("has_stone_bricks", has(ItemTags.STONE_BRICKS)).save(consumer);
		
		stairBuilder(BlockRegistry.LIMESTONE_STAIRS.get(), Ingredient.of(BlockRegistry.LIMESTONE.get())).unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE_STAIRS.get())).save(consumer, modLoc(folder + "limestone/stairs"));
		stairBuilder(BlockRegistry.LIMESTONE_BRICKS_STAIRS.get(), Ingredient.of(BlockRegistry.LIMESTONE_BRICKS.get())).unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE_STAIRS.get())).save(consumer, modLoc(folder + "limestone_bricks/stairs"));
		stairBuilder(BlockRegistry.POLISHED_LIMESTONE_STAIRS.get(), Ingredient.of(BlockRegistry.POLISHED_LIMESTONE.get())).unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE_STAIRS.get())).save(consumer, modLoc(folder + "polished_limestone/stairs"));
		stairBuilder(BlockRegistry.POLISHED_LIMESTONE_BRICKS_STAIRS.get(), Ingredient.of(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get())).unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE_STAIRS.get())).save(consumer, modLoc(folder + "polished_limestone_bricks/stairs"));
		
		stairBuilder(BlockRegistry.CUT_RED_SANDSTONE_STAIRS.get(), Ingredient.of(Blocks.CUT_RED_SANDSTONE)).unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE)).save(consumer, modLoc(folder + "stairs/cut_red_sandstone"));
		stairBuilder(BlockRegistry.CUT_SANDSTONE_STAIRS.get(), Ingredient.of(Blocks.CUT_SANDSTONE)).unlockedBy("has_sandstone", has(Blocks.SANDSTONE)).save(consumer, modLoc(folder + "stairs/cut_sandstone"));
		stairBuilder(BlockRegistry.SMOOTH_STONE_STAIRS.get(), Ingredient.of(Blocks.SMOOTH_STONE)).unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE)).save(consumer, modLoc(folder + "stairs/smooth_stone"));
		
		ShapedRecipeBuilder.shaped(Items.ACACIA_STAIRS, 24)
		.define('W', ItemTags.ACACIA_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_acacia_logs", has(ItemTags.ACACIA_LOGS))
		.save(consumer, modLoc(folder + "acacia/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.OAK_STAIRS, 24)
		.define('W', ItemTags.OAK_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_oak_logs", has(ItemTags.OAK_LOGS))
		.save(consumer, modLoc(folder + "oak/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.BIRCH_STAIRS, 24)
		.define('W', ItemTags.BIRCH_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_birch_logs", has(ItemTags.BIRCH_LOGS))
		.save(consumer, modLoc(folder + "birch/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.JUNGLE_STAIRS, 24)
		.define('W', ItemTags.JUNGLE_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_jungle_logs", has(ItemTags.JUNGLE_LOGS))
		.save(consumer, modLoc(folder + "jungle/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.SPRUCE_STAIRS, 24)
		.define('W', ItemTags.SPRUCE_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_spruce_logs", has(ItemTags.SPRUCE_LOGS))
		.save(consumer, modLoc(folder + "spruce/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.DARK_OAK_STAIRS, 24)
		.define('W', ItemTags.DARK_OAK_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_dark_oak_logs", has(ItemTags.DARK_OAK_LOGS))
		.save(consumer, modLoc(folder + "dark_oak/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.WARPED_STAIRS, 24)
		.define('W', ItemTags.WARPED_STEMS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_warped_stems", has(ItemTags.WARPED_STEMS))
		.save(consumer, modLoc(folder + "warped/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.CRIMSON_STAIRS, 24)
		.define('W', ItemTags.CRIMSON_STEMS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_crimson_stems", has(ItemTags.CRIMSON_STEMS))
		.save(consumer, modLoc(folder + "crimson/stairs"));
		
		ShapedRecipeBuilder.shaped(Items.MANGROVE_STAIRS, 24)
		.define('W', ItemTags.MANGROVE_LOGS)
		.pattern("W  ")
		.pattern("WW ")
		.pattern("WWW")
		.unlockedBy("has_mangrove_logs", has(ItemTags.MANGROVE_LOGS))
		.save(consumer, modLoc(folder + "mangrove/stairs"));
	}
	
	private void addWallsRecipes(Consumer<FinishedRecipe> consumer) {

		wallBuilder(BlockRegistry.LIMESTONE_WALL.get(), Ingredient.of(BlockRegistry.LIMESTONE.get())).unlockedBy("has_stone_bricks", has(BlockRegistry.LIMESTONE.get())).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.LIMESTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.LIMESTONE_BRICK_WALL.get(), Ingredient.of(BlockRegistry.LIMESTONE_BRICKS.get())).unlockedBy("has_stone_bricks", has(BlockRegistry.LIMESTONE_BRICKS.get())).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.LIMESTONE_BRICK_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_LIMESTONE_WALL.get(), Ingredient.of(BlockRegistry.POLISHED_LIMESTONE.get())).unlockedBy("has_stone_bricks", has(BlockRegistry.POLISHED_LIMESTONE.get())).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_LIMESTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_LIMESTONE_BRICK_WALL.get(), Ingredient.of(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get())).unlockedBy("has_stone_bricks", has(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get())).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_LIMESTONE_BRICK_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.CUT_RED_SANDSTONE_WALL.get(), Ingredient.of(Blocks.CUT_RED_SANDSTONE)).unlockedBy("has_stone_bricks", has(Blocks.CUT_RED_SANDSTONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.CUT_RED_SANDSTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.CUT_SANDSTONE_WALL.get(), Ingredient.of(Blocks.CUT_SANDSTONE)).unlockedBy("has_stone_bricks", has(Blocks.CUT_SANDSTONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.CUT_SANDSTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.DARK_PRISMARINE_WALL.get(), Ingredient.of(Blocks.DARK_PRISMARINE)).unlockedBy("has_stone_bricks", has(Blocks.DARK_PRISMARINE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.DARK_PRISMARINE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_ANDESITE_WALL.get(), Ingredient.of(Blocks.POLISHED_ANDESITE)).unlockedBy("has_stone_bricks", has(Blocks.POLISHED_ANDESITE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_ANDESITE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_DIORITE_WALL.get(), Ingredient.of(Blocks.POLISHED_DIORITE)).unlockedBy("has_stone_bricks", has(Blocks.POLISHED_DIORITE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_DIORITE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_GRANITE_WALL.get(), Ingredient.of(Blocks.POLISHED_GRANITE)).unlockedBy("has_stone_bricks", has(Blocks.POLISHED_GRANITE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_GRANITE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.POLISHED_BLACKSTONE_WALL.get(), Ingredient.of(Blocks.POLISHED_BLACKSTONE)).unlockedBy("has_stone_bricks", has(Blocks.POLISHED_BLACKSTONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.POLISHED_BLACKSTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.PRISMARINE_BRICK_WALL.get(), Ingredient.of(Blocks.PRISMARINE_BRICKS)).unlockedBy("has_stone_bricks", has(Blocks.PRISMARINE_BRICKS)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.PRISMARINE_BRICK_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.PURPUR_WALL.get(), Ingredient.of(Blocks.PURPUR_BLOCK)).unlockedBy("has_stone_bricks", has(Blocks.PURPUR_BLOCK)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.PURPUR_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.QUARTZ_WALL.get(), Ingredient.of(Blocks.QUARTZ_BLOCK)).unlockedBy("has_stone_bricks", has(Blocks.QUARTZ_BLOCK)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.QUARTZ_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.SMOOTH_QUARTZ_WALL.get(), Ingredient.of(Blocks.SMOOTH_QUARTZ)).unlockedBy("has_stone_bricks", has(Blocks.SMOOTH_QUARTZ)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.SMOOTH_QUARTZ_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.SMOOTH_RED_SANDSTONE_WALL.get(), Ingredient.of(Blocks.SMOOTH_RED_SANDSTONE)).unlockedBy("has_stone_bricks", has(Blocks.SMOOTH_RED_SANDSTONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.SMOOTH_RED_SANDSTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.SMOOTH_SANDSTONE_WALL.get(), Ingredient.of(Blocks.SMOOTH_SANDSTONE)).unlockedBy("has_stone_bricks", has(Blocks.SMOOTH_SANDSTONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.SMOOTH_SANDSTONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.SMOOTH_STONE_WALL.get(), Ingredient.of(Blocks.SMOOTH_STONE)).unlockedBy("has_stone_bricks", has(Blocks.SMOOTH_STONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.SMOOTH_STONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		wallBuilder(BlockRegistry.STONE_WALL.get(), Ingredient.of(Blocks.STONE)).unlockedBy("has_stone_bricks", has(Blocks.STONE)).save(consumer, modLoc("shaped/" + "walls/" + BlockRegistry.STONE_WALL.get().defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		
		
	}
	
	private void addShapedRecipes(Consumer<FinishedRecipe> consumer) {
		
		String folder = "shaped/";
	
		//ICE REWORK
			ShapedRecipeBuilder.shaped(Items.PACKED_ICE)
			.define('C', Items.ICE)
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_ice", has(Items.ICE))
			.save(consumer);
			
			ShapedRecipeBuilder.shaped(Items.BLUE_ICE)
			.define('C', Items.PACKED_ICE)
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_packed_ice", has(Items.PACKED_ICE))
			.save(consumer);
			
			ShapedRecipeBuilder.shaped(BlockRegistry.SMOOTH_ICE.get())
			.define('C', Items.BLUE_ICE)
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_blue_ice", has(Items.BLUE_ICE))
			.save(consumer, modLoc(folder + "smooth_ice"));
		
		//JACK O'SOUL LANTERN
			ShapedRecipeBuilder.shaped(BlockRegistry.JACK_O_SOUL_LANTERN.get())
			.define('S', Items.SOUL_TORCH)
			.define('C', Items.CARVED_PUMPKIN)
			.pattern("C")
			.pattern("S")
			.unlockedBy("has_soul_torch", has(Items.SOUL_TORCH))
			.save(consumer, modLoc(folder + "jack_o_soul_lantern"));
		
		//CHARCOAL BLOCK FROM ITEM
			ShapedRecipeBuilder.shaped(BlockRegistry.CHARCOAL_BLOCK.get())
			.define('C', Items.CHARCOAL)
			.pattern("CCC")
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_charcoal", has(Items.CHARCOAL))
			.save(consumer, modLoc(folder + "charcoal_block"));
		
		//MOSS BLOCK && LAYER BLOCKS
			ShapedRecipeBuilder.shaped(Items.MOSSY_COBBLESTONE)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.define('S', Tags.Items.COBBLESTONE)
			.pattern("CCC")
			.pattern("CSC")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLoc(folder + "mossy_cobblestone"));
			
			ShapedRecipeBuilder.shaped(Items.MOSSY_STONE_BRICKS)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.define('S', Items.STONE_BRICKS)
			.pattern("CCC")
			.pattern("CSC")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLoc(folder + "mossy_stone_bricks"));
			
			ShapedRecipeBuilder.shaped(Items.MOSS_BLOCK)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLoc(folder + "moss_block"));
			
			ShapedRecipeBuilder.shaped(Items.SAND)
			.define('C', BlockRegistry.SAND_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_sand", has(Items.SAND))
			.save(consumer, modLoc(folder + "sand"));
			
			ShapedRecipeBuilder.shaped(Items.RED_SAND)
			.define('C', BlockRegistry.RED_SAND_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_sand", has(Items.RED_SAND))
			.save(consumer, modLoc(folder + "red_sand"));
			
			ShapedRecipeBuilder.shaped(Items.GRAVEL)
			.define('C', BlockRegistry.GRAVEL_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_gravel", has(Items.GRAVEL))
			.save(consumer, modLoc(folder + "gravel"));
			
		//GLASS TO GLOWING GLASS	
			createGlowingBlocks(consumer, modLoc(folder + "glowing_glass"), Tags.Items.GLASS_COLORLESS, BlockRegistry.GLOWING_GLASS.get());
			
			createGlowingBlocks(consumer, modLoc(folder + "black" + "_stained_glowing_glass"), Tags.Items.GLASS_BLACK, BlockRegistry.BLACK_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "blue" + "_stained_glowing_glass"), Tags.Items.GLASS_BLUE, BlockRegistry.BLUE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "brown" + "_stained_glowing_glass"), Tags.Items.GLASS_BROWN, BlockRegistry.BROWN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "cyan" + "_stained_glowing_glass"), Tags.Items.GLASS_CYAN, BlockRegistry.CYAN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "gray" + "_stained_glowing_glass"), Tags.Items.GLASS_GRAY, BlockRegistry.GRAY_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "green" + "_stained_glowing_glass"), Tags.Items.GLASS_GREEN, BlockRegistry.GREEN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "light_blue" + "_stained_glowing_glass"), Tags.Items.GLASS_LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "light_gray" + "_stained_glowing_glass"), Tags.Items.GLASS_LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "lime" + "_stained_glowing_glass"), Tags.Items.GLASS_LIME, BlockRegistry.LIME_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "magenta" + "_stained_glowing_glass"), Tags.Items.GLASS_MAGENTA, BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "orange" + "_stained_glowing_glass"), Tags.Items.GLASS_ORANGE, BlockRegistry.ORANGE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "pink" + "_stained_glowing_glass"), Tags.Items.GLASS_PINK, BlockRegistry.PINK_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "purple" + "_stained_glowing_glass"), Tags.Items.GLASS_PURPLE, BlockRegistry.PURPLE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "red" + "_stained_glowing_glass"), Tags.Items.GLASS_RED, BlockRegistry.RED_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "white" + "_stained_glowing_glass"), Tags.Items.GLASS_WHITE, BlockRegistry.WHITE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLoc(folder + "yellow" + "_stained_glowing_glass"), Tags.Items.GLASS_YELLOW, BlockRegistry.YELLOW_STAINED_GLOWING_GLASS.get());
		
			createGlowingBlocks(consumer, modLoc(folder + "glowing_glass_pane"), Tags.Items.GLASS_PANES_COLORLESS, BlockRegistry.GLOWING_GLASS_PANE.get());

			createGlowingBlocks(consumer, modLoc(folder + "black" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BLACK, BlockRegistry.BLACK_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "blue" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BLUE, BlockRegistry.BLUE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "brown" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BROWN, BlockRegistry.BROWN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "cyan" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_CYAN, BlockRegistry.CYAN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "gray" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_GRAY, BlockRegistry.GRAY_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "green" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_GREEN, BlockRegistry.GREEN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "light_blue" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "light_gray" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "lime" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIME, BlockRegistry.LIME_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "magenta" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_MAGENTA, BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "orange" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_ORANGE, BlockRegistry.ORANGE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "pink" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_PINK, BlockRegistry.PINK_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "purple" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_PURPLE, BlockRegistry.PURPLE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "red" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_RED, BlockRegistry.RED_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "white" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_WHITE, BlockRegistry.WHITE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLoc(folder + "yellow" + "_stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_YELLOW, BlockRegistry.YELLOW_STAINED_GLOWING_GLASS_PANE.get());
		
			glowingBlockToPane(consumer, modLoc(folder + "glowing_glass_pane_from_block"), BlockRegistry.GLOWING_GLASS.get(), BlockRegistry.GLOWING_GLASS_PANE.get());

			glowingBlockToPane(consumer, modLoc(folder + "black" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.BLACK_STAINED_GLOWING_GLASS.get(), BlockRegistry.BLACK_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "blue" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.BLUE_STAINED_GLOWING_GLASS.get(), BlockRegistry.BLUE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "brown" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.BROWN_STAINED_GLOWING_GLASS.get(), BlockRegistry.BROWN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "cyan" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.CYAN_STAINED_GLOWING_GLASS.get(), BlockRegistry.CYAN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "gray" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.GRAY_STAINED_GLOWING_GLASS.get(), BlockRegistry.GRAY_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "green" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.GREEN_STAINED_GLOWING_GLASS.get(), BlockRegistry.GREEN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "light_blue" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "light_gray" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "lime" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.LIME_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIME_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "magenta" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS.get(), BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "orange" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.ORANGE_STAINED_GLOWING_GLASS.get(), BlockRegistry.ORANGE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "pink" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.PINK_STAINED_GLOWING_GLASS.get(), BlockRegistry.PINK_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "purple" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.PURPLE_STAINED_GLOWING_GLASS.get(), BlockRegistry.PURPLE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "red" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.RED_STAINED_GLOWING_GLASS.get(), BlockRegistry.RED_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "white" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.WHITE_STAINED_GLOWING_GLASS.get(), BlockRegistry.WHITE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLoc(folder + "yellow" + "_stained_glowing_glass_pane_from_block"), BlockRegistry.YELLOW_STAINED_GLOWING_GLASS.get(), BlockRegistry.YELLOW_STAINED_GLOWING_GLASS_PANE.get());
			
		//LIMESTONE
			ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE.get(), 4)
			.define('C', BlockRegistry.LIMESTONE.get())
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "polished_limestone"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get(), 4)
			.define('C', BlockRegistry.POLISHED_LIMESTONE.get())
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "limestone_bricks"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.LIMESTONE_BRICKS.get(), 2)
			.define('C', BlockRegistry.LIMESTONE_SLAB.get())
			.pattern("CC")
			.pattern("CC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "chiseled_limestone_bricks"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.CHISELED_LIMESTONE.get())
			.define('C', BlockRegistry.POLISHED_LIMESTONE_SLAB.get())
			.pattern("C")
			.pattern("C")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "chiseled_limestone"));
		
		//LIMESTONE WALLS
			ShapedRecipeBuilder.shaped(BlockRegistry.LIMESTONE_WALL.get(), 6)
			.define('C', BlockRegistry.LIMESTONE.get())
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "limestone_wall"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.LIMESTONE_BRICK_WALL.get(), 6)
			.define('C', BlockRegistry.LIMESTONE_BRICKS.get())
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "limestone_bricks_wall"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE_WALL.get(), 6)
			.define('C', BlockRegistry.POLISHED_LIMESTONE.get())
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "polished_limestone_wall"));
			
			ShapedRecipeBuilder.shaped(BlockRegistry.POLISHED_LIMESTONE_BRICK_WALL.get(), 6)
			.define('C', BlockRegistry.POLISHED_LIMESTONE_BRICKS.get())
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_limestone", has(BlockRegistry.LIMESTONE.get()))
			.save(consumer, modLoc(folder + "polished_limestone_bricks_wall"));
	
		//LANTERN RECIPES
			ShapedRecipeBuilder.shaped(Items.LANTERN)
			.define('I', Tags.Items.NUGGETS_IRON)
			.define('C', ItemTags.CANDLES)
			.pattern("III")
			.pattern("ICI")
			.pattern("III")
			.unlockedBy("has_candle", has(ItemTags.CANDLES))
			.save(consumer, modLoc(folder + "lantern"));
			
		//PACKED MUD
			ShapedRecipeBuilder.shaped(Items.PACKED_MUD, 8)
			.define('M', Items.MUD)
			.define('W', Tags.Items.CROPS_WHEAT)
			.pattern("MMM")
			.pattern("MWM")
			.pattern("MMM")
			.unlockedBy("has_mud", has(Items.MUD))
			.save(consumer, modLoc(folder + "packed_mud"));
			
	}
	
	private void addShapelessRecipes(Consumer<FinishedRecipe> consumer) {
		
		String folder = "shapeless/";

		//CHARCOAL FROM BLOCK
			ShapelessRecipeBuilder.shapeless(Items.CHARCOAL, 9)
			.requires(BlockRegistry.CHARCOAL_BLOCK.get())
			.unlockedBy("has_charcoal_block", has(BlockRegistry.CHARCOAL_BLOCK.get()))
			.save(consumer, modLoc(folder + "charcoal"));
		
		//MILK BUCKET & MILK BOTTLE
			ShapelessRecipeBuilder.shapeless(Items.MILK_BUCKET)
			.requires(Items.BUCKET)
			.requires(ItemRegistry.MILK_BOTTLE.get(), 3)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(consumer, modLoc(folder + "milk_bucket"));
			
			ShapelessRecipeBuilder.shapeless(ItemRegistry.MILK_BOTTLE.get(), 3)
			.requires(Items.MILK_BUCKET)
			.requires(Items.GLASS_BOTTLE, 3)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(consumer, modLoc(folder + "milk_bottle"));
		
		//MOSS LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.MOSS_LAYER_BLOCK.get(), 8)
			.requires(Items.MOSS_BLOCK)
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLoc(folder + "moss_layer_block"));
		//SAND LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.SAND_LAYER_BLOCK.get(), 8)
			.requires(Items.SAND)
			.unlockedBy("has_sand", has(Items.SAND))
			.save(consumer, modLoc(folder + "sand_layer_block"));
		//RED_SAND LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.RED_SAND_LAYER_BLOCK.get(), 8)
			.requires(Items.RED_SAND)
			.unlockedBy("has_sand", has(Items.RED_SAND))
			.save(consumer, modLoc(folder + "red_sand_layer_block"));
		//GRAVEL LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.GRAVEL_LAYER_BLOCK.get(), 8)
			.requires(Items.GRAVEL)
			.unlockedBy("has_gravel", has(Items.GRAVEL))
			.save(consumer, modLoc(folder + "gravel_layer_block"));
			
	//TODO USE CUSTOM RECIPE SERIALIZER TO AVOID OVERRIDING VANILLA REGISTRY OF BOATS/MINECARTS
		//CRAFT REMAINDER BOATS
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.OAK_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "oak" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.SPRUCE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "spruce" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.BIRCH_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "birch" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.JUNGLE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "jungle" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.ACACIA_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "acacia" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.DARK_OAK_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "dark_oak" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.MANGROVE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLoc(folder + "mangrove" + "_chest_boat"));
			
		//CRAFT REMAINDER MINECARTS
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.CHEST_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLoc(folder + "chest" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.FURNACE)
			.requires(Items.FURNACE_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLoc(folder + "furnace" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.TNT)
			.requires(Items.TNT_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLoc(folder + "tnt" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.HOPPER)
			.requires(Items.HOPPER_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLoc(folder + "hopper" + "_minecart"));

			
			
			
			
			
	}
	
	private void addBlastingRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "blasting/";
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(BlockRegistry.SMOOTH_STONE_STAIRS.get()), Items.STONE_STAIRS, 0.1F, 100)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_stone_stairs", has(Items.STONE_STAIRS))
			.save(consumer, modLoc(folder + "smooth_stone_stairs"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.COBBLESTONE_STAIRS), Items.STONE_STAIRS, 0.1F, 100)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_stairs", has(Items.COBBLESTONE_STAIRS))
			.save(consumer, modLoc(folder + "stone_stairs"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.COBBLESTONE_SLAB), Items.STONE_SLAB, 0.1F, 100)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_slab", has(Items.COBBLESTONE_SLAB))
			.save(consumer, modLoc(folder + "stone_slab"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Tags.Items.SAND), Blocks.GLASS.asItem(), 0.1F, 100)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_sand", has(Tags.Items.SAND))
			.save(consumer, modLoc(folder + "sand"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.RAW_IRON_BLOCK), Items.IRON_BLOCK, 6.3F, 900)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
			.save(consumer, modLoc(folder + "raw_iron"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.RAW_COPPER_BLOCK), Items.COPPER_BLOCK, 6.3F, 900)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
			.save(consumer, modLoc(folder + "raw_copper"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.RAW_GOLD_BLOCK), Items.GOLD_BLOCK, 9.0F, 900)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
			.save(consumer, modLoc(folder + "raw_gold"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(
				Items.SHIELD, 
				Items.IRON_BARS, 
				Items.RAIL, 
				Items.TRIPWIRE_HOOK, 
				Items.SHEARS, 
				Items.DAMAGED_ANVIL), Items.IRON_NUGGET, 0.1F, 100)
			.unlockedBy("has_shield", has(Items.SHIELD))
			.unlockedBy("has_iron_bars", has(Items.IRON_BARS))
			.unlockedBy("has_rail", has(Items.RAIL))
			.unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
			.unlockedBy("has_shears", has(Items.SHEARS))
			.unlockedBy("has_damaged_anvil", has(Items.DAMAGED_ANVIL))
			.save(consumer, modLoc(folder + "iron_nugget"));

		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(
				Items.IRON_DOOR, 
				Items.IRON_TRAPDOOR, 
				Items.CHAIN, 
				Items.HOPPER, 
				Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 
				Items.SMITHING_TABLE, 
				Items.CAULDRON, 
				Items.MINECART, 
				Items.BUCKET, 
				Items.CHIPPED_ANVIL), Items.IRON_INGOT, 0.1F, 100)
			.unlockedBy("has_iron_door", has(Items.IRON_DOOR))
			.unlockedBy("has_iron_trapdoor", has(Items.IRON_TRAPDOOR))
			.unlockedBy("has_chain", has(Items.CHAIN))
			.unlockedBy("has_hopper", has(Items.HOPPER))
			.unlockedBy("has_heavy_weighted_pressure_plate", has(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
			.unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
			.unlockedBy("has_cauldron", has(Items.CAULDRON))
			.unlockedBy("has_minecart", has(Items.MINECART))
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.unlockedBy("has_chipped_anvil", has(Items.CHIPPED_ANVIL))
			.save(consumer, modLoc(folder + "iron_ingot"));

		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.ANVIL), Items.IRON_BLOCK, 0.1F, 100)
			.unlockedBy("has_anvil", has(Items.ANVIL))
			.save(consumer, modLoc(folder + "iron_block"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(
			Items.LIGHT_WEIGHTED_PRESSURE_PLATE,
			Items.BELL
			), Items.GOLD_INGOT, 0.1F, 100)
			.unlockedBy("has_light_weighted_pressure_plate", has(Items.LIGHT_WEIGHTED_PRESSURE_PLATE))
			.save(consumer, modLoc(folder + "gold_ingot"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(
				Items.LIGHTNING_ROD,
				Items.CUT_COPPER_SLAB,
				Items.EXPOSED_CUT_COPPER_SLAB,
				Items.WEATHERED_CUT_COPPER_SLAB,
				Items.OXIDIZED_CUT_COPPER_SLAB), Items.COPPER_INGOT, 0.1F, 100)
			.unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
			.unlockedBy("has_cut_coper_slab", has(Items.CUT_COPPER_SLAB))
			.unlockedBy("has_exposed_cut_coper_slab", has(Items.EXPOSED_CUT_COPPER_SLAB))
			.unlockedBy("has_weathered_cut_coper_slab", has(Items.WEATHERED_CUT_COPPER_SLAB))
			.unlockedBy("has_oxidized_cut_coper_slab", has(Items.OXIDIZED_CUT_COPPER_SLAB))
			.save(consumer, modLoc(folder + "copper_ingot"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(
				Items.EXPOSED_COPPER, 
				Items.WEATHERED_COPPER, 
				Items.OXIDIZED_COPPER,
				Items.CUT_COPPER,
				Items.EXPOSED_CUT_COPPER,
				Items.WEATHERED_CUT_COPPER, 
				Items.OXIDIZED_CUT_COPPER,
				Items.CUT_COPPER_STAIRS,
				Items.EXPOSED_CUT_COPPER_STAIRS,
				Items.WEATHERED_CUT_COPPER_STAIRS,
				Items.OXIDIZED_CUT_COPPER_STAIRS), Items.COPPER_BLOCK, 0.1F, 100)
			.unlockedBy("has_exposed_copper", has(Items.EXPOSED_COPPER))
			.unlockedBy("has_weathered_copper", has(Items.WEATHERED_COPPER))
			.unlockedBy("has_oxidized_coppper", has(Items.OXIDIZED_COPPER))
			.unlockedBy("has_cut_coppper", has(Items.CUT_COPPER))
			.unlockedBy("has_exposed_cut_copper", has(Items.EXPOSED_CUT_COPPER))
			.unlockedBy("has_weathered_cut_copper", has(Items.WEATHERED_CUT_COPPER))
			.unlockedBy("has_oxidized_cut_coppper", has(Items.OXIDIZED_CUT_COPPER))
			.unlockedBy("has_cut_copper_stairs", has(Items.CUT_COPPER_STAIRS))
			.unlockedBy("has_exposed_cut_copper_stairs", has(Items.EXPOSED_CUT_COPPER_STAIRS))
			.unlockedBy("has_weathered_cut_copper_stairs", has(Items.WEATHERED_CUT_COPPER_STAIRS))
			.unlockedBy("has_oxidized_cut_copper_stairs", has(Items.OXIDIZED_CUT_COPPER_STAIRS))
			.save(consumer, modLoc(folder + "copper_block"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Tags.Items.STAINED_GLASS), Items.GLASS, 0.1F, 100)
			.unlockedBy("has_glass", has(Items.GLASS))
			.save(consumer, modLoc(folder + "glass"));
		
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(Items.CHORUS_FRUIT), Items.POPPED_CHORUS_FRUIT, 0.1F, 100)
			.unlockedBy("has_chorus_fruit", has(Items.CHORUS_FRUIT))
			.save(consumer, modLoc(folder + "popped_chorus_fruit"));
		
	}
	
	private void addSmeltingRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "smelting/";
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(BlockRegistry.SMOOTH_STONE_STAIRS.get()), Items.STONE_STAIRS, 0.1F, 200)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_stone_stairs", has(Items.STONE_STAIRS))
			.save(consumer, modLoc(folder + "smooth_stone_stairs"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.COBBLESTONE_STAIRS), Items.STONE_STAIRS, 0.1F, 200)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_stairs", has(Items.COBBLESTONE_STAIRS))
			.save(consumer, modLoc(folder + "stone_stairs"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.COBBLESTONE_SLAB), Items.STONE_SLAB, 0.1F, 200)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_slab", has(Items.COBBLESTONE_SLAB))
			.save(consumer, modLoc(folder + "stone_slab"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_IRON_BLOCK), Items.IRON_BLOCK, 6.3F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
			.save(consumer, modLoc(folder + "raw_iron"));
			
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_COPPER_BLOCK), Items.COPPER_BLOCK, 6.3F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
			.save(consumer, modLoc(folder + "raw_copper"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_GOLD_BLOCK), Items.GOLD_BLOCK, 9.0F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
			.save(consumer, modLoc(folder + "raw_gold"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(
				Items.SHIELD, 
				Items.IRON_BARS, 
				Items.RAIL, 
				Items.TRIPWIRE_HOOK, 
				Items.SHEARS, 
				Items.DAMAGED_ANVIL), Items.IRON_NUGGET, 0.1F, 200)
			.unlockedBy("has_shield", has(Items.SHIELD))
			.unlockedBy("has_iron_bars", has(Items.IRON_BARS))
			.unlockedBy("has_rail", has(Items.RAIL))
			.unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
			.unlockedBy("has_shears", has(Items.SHEARS))
			.unlockedBy("has_damaged_anvil", has(Items.DAMAGED_ANVIL))
			.save(consumer, modLoc(folder + "iron_nugget"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(
				Items.IRON_DOOR, 
				Items.IRON_TRAPDOOR, 
				Items.CHAIN, 
				Items.HOPPER, 
				Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 
				Items.SMITHING_TABLE, 
				Items.CAULDRON, 
				Items.MINECART, 
				Items.BUCKET, 
				Items.CHIPPED_ANVIL), Items.IRON_INGOT, 0.1F, 200)
			.unlockedBy("has_iron_door", has(Items.IRON_DOOR))
			.unlockedBy("has_iron_trapdoor", has(Items.IRON_TRAPDOOR))
			.unlockedBy("has_chain", has(Items.CHAIN))
			.unlockedBy("has_hopper", has(Items.HOPPER))
			.unlockedBy("has_heavy_weighted_pressure_plate", has(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
			.unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
			.unlockedBy("has_cauldron", has(Items.CAULDRON))
			.unlockedBy("has_minecart", has(Items.MINECART))
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.unlockedBy("has_chipped_anvil", has(Items.CHIPPED_ANVIL))
			.save(consumer, modLoc(folder + "iron_ingot"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.ANVIL), Items.IRON_BLOCK, 0.1F, 200)
			.unlockedBy("has_anvil", has(Items.ANVIL))
			.save(consumer, modLoc(folder + "iron_block"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), Items.GOLD_INGOT, 0.1F, 200)
			.unlockedBy("has_light_weighted_pressure_plate", has(Items.LIGHT_WEIGHTED_PRESSURE_PLATE))
			.save(consumer, modLoc(folder + "gold_ingot"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(
				Items.LIGHTNING_ROD,
				Items.CUT_COPPER_SLAB,
				Items.EXPOSED_CUT_COPPER_SLAB,
				Items.WEATHERED_CUT_COPPER_SLAB,
				Items.OXIDIZED_CUT_COPPER_SLAB), Items.COPPER_INGOT, 0.1F, 200)
			.unlockedBy("has_lightning_rod", has(Items.LIGHTNING_ROD))
			.unlockedBy("has_cut_coper_slab", has(Items.CUT_COPPER_SLAB))
			.unlockedBy("has_exposed_cut_coper_slab", has(Items.EXPOSED_CUT_COPPER_SLAB))
			.unlockedBy("has_weathered_cut_coper_slab", has(Items.WEATHERED_CUT_COPPER_SLAB))
			.unlockedBy("has_oxidized_cut_coper_slab", has(Items.OXIDIZED_CUT_COPPER_SLAB))
			.save(consumer, modLoc(folder + "copper_ingot"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(
				Items.EXPOSED_COPPER, 
				Items.WEATHERED_COPPER, 
				Items.OXIDIZED_COPPER,
				Items.CUT_COPPER,
				Items.EXPOSED_CUT_COPPER,
				Items.WEATHERED_CUT_COPPER, 
				Items.OXIDIZED_CUT_COPPER,
				Items.CUT_COPPER_STAIRS,
				Items.EXPOSED_CUT_COPPER_STAIRS,
				Items.WEATHERED_CUT_COPPER_STAIRS,
				Items.OXIDIZED_CUT_COPPER_STAIRS), Items.COPPER_BLOCK, 0.1F, 200)
			.unlockedBy("has_exposed_copper", has(Items.EXPOSED_COPPER))
			.unlockedBy("has_weathered_copper", has(Items.WEATHERED_COPPER))
			.unlockedBy("has_oxidized_coppper", has(Items.OXIDIZED_COPPER))
			.unlockedBy("has_cut_coppper", has(Items.CUT_COPPER))
			.unlockedBy("has_exposed_cut_copper", has(Items.EXPOSED_CUT_COPPER))
			.unlockedBy("has_weathered_cut_copper", has(Items.WEATHERED_CUT_COPPER))
			.unlockedBy("has_oxidized_cut_coppper", has(Items.OXIDIZED_CUT_COPPER))
			.unlockedBy("has_cut_copper_stairs", has(Items.CUT_COPPER_STAIRS))
			.unlockedBy("has_exposed_cut_copper_stairs", has(Items.EXPOSED_CUT_COPPER_STAIRS))
			.unlockedBy("has_weathered_cut_copper_stairs", has(Items.WEATHERED_CUT_COPPER_STAIRS))
			.unlockedBy("has_oxidized_cut_copper_stairs", has(Items.OXIDIZED_CUT_COPPER_STAIRS))
			.save(consumer, modLoc(folder + "copper_block"));
		
		SimpleCookingRecipeBuilder.smelting(
				Ingredient.of(Tags.Items.STAINED_GLASS), Items.GLASS, 0.1F, 200)
				.unlockedBy("has_glass", has(Items.GLASS))
				.save(consumer, modLoc(folder + "glass"));
		
		//Sand layer blocks to glass pane
			SimpleCookingRecipeBuilder.smelting(
					Ingredient.of(
						BlockRegistry.SAND_LAYER_BLOCK.get()), Items.GLASS_PANE, 0.0125F, 200)
					.unlockedBy("has_glass", has(Items.GLASS))
					.save(consumer, modLoc(folder + "sand_layer"));
			SimpleCookingRecipeBuilder.smelting(
					Ingredient.of(
						BlockRegistry.RED_SAND_LAYER_BLOCK.get()), Items.GLASS_PANE, 0.0125F, 200)
					.unlockedBy("has_glass", has(Items.GLASS))
					.save(consumer, modLoc(folder + "red_sand_layer"));
			
	}
	
	private void addStonecuttingRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "stonecutting/";
		String slabs = "slabs/";
		String stairs = "stairs/";
		String walls = "walls/";

		stoneCuttingSlabs(Blocks.ACACIA_STAIRS, Blocks.ACACIA_SLAB, consumer);
		stoneCuttingSlabs(Blocks.ANDESITE_STAIRS, Blocks.ANDESITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.BIRCH_STAIRS, Blocks.BIRCH_SLAB, consumer);
		stoneCuttingSlabs(Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_SLAB, consumer);
		stoneCuttingSlabs(Blocks.CUT_COPPER_STAIRS, Blocks.CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.DARK_PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.DIORITE_STAIRS, Blocks.DIORITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.GRANITE_STAIRS, Blocks.GRANITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.MUD_BRICK_STAIRS, Blocks.MUD_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.OAK_STAIRS, Blocks.OAK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.PURPUR_STAIRS, Blocks.PURPUR_SLAB, consumer);
		stoneCuttingSlabs(Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_SLAB, consumer);
		stoneCuttingSlabs(Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_NETHER_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ_SLAB, consumer);
		stoneCuttingSlabs(Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, consumer);
		stoneCuttingSlabs(Blocks.STONE_STAIRS, Blocks.STONE_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WARPED_STAIRS, Blocks.WARPED_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.WAXED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, consumer);
		stoneCuttingSlabs(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_SLAB, consumer);

		stoneCuttingFolderModId(BlockRegistry.LIMESTONE.get(), BlockRegistry.LIMESTONE_SLAB.get(), 2, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE_BRICKS.get(), BlockRegistry.LIMESTONE_BRICKS_SLAB.get(), 2, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE.get(), BlockRegistry.POLISHED_LIMESTONE_SLAB.get(), 2, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get(), BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get(), 2, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.CUT_RED_SANDSTONE_STAIRS.get(), Blocks.CUT_RED_SANDSTONE_SLAB, 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.CUT_SANDSTONE_STAIRS.get(), Blocks.CUT_SANDSTONE_SLAB, 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.SMOOTH_STONE_STAIRS.get(), Blocks.SMOOTH_STONE_SLAB, 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE_STAIRS.get(), BlockRegistry.LIMESTONE_SLAB.get(), 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE_BRICKS_STAIRS.get(), BlockRegistry.LIMESTONE_BRICKS_SLAB.get(), 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE_STAIRS.get(), BlockRegistry.POLISHED_LIMESTONE_SLAB.get(), 1, consumer, slabs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE_BRICKS_STAIRS.get(), BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get(), 1, consumer, slabs, ModConstants.MOD_ID);
		
		stoneCuttingStairs(Blocks.OAK_PLANKS, Blocks.OAK_STAIRS, consumer);
		stoneCuttingStairs(Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS, consumer);
		stoneCuttingStairs(Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS, consumer);
		stoneCuttingStairs(Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS, consumer);
		stoneCuttingStairs(Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STAIRS, consumer);
		stoneCuttingStairs(Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS, consumer);
		stoneCuttingStairs(Blocks.ACACIA_PLANKS, Blocks.ACACIA_STAIRS, consumer);
		stoneCuttingStairs(Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS, consumer);
		stoneCuttingStairs(Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_STAIRS, consumer);
		
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE.get(), BlockRegistry.LIMESTONE_STAIRS.get(), 1, consumer, stairs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE_BRICKS.get(), BlockRegistry.LIMESTONE_BRICKS_STAIRS.get(), 1, consumer, stairs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE.get(), BlockRegistry.POLISHED_LIMESTONE_STAIRS.get(), 1, consumer, stairs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get(), BlockRegistry.POLISHED_LIMESTONE_BRICKS_STAIRS.get(), 1, consumer, stairs, ModConstants.MOD_ID);
		stoneCuttingFolderModId(Blocks.CUT_RED_SANDSTONE, BlockRegistry.CUT_RED_SANDSTONE_STAIRS.get(), 1, consumer, stairs, "minecraft");
		stoneCuttingFolderModId(Blocks.CUT_SANDSTONE, BlockRegistry.CUT_SANDSTONE_STAIRS.get(), 1, consumer, stairs, "minecraft");
		stoneCuttingFolderModId(Blocks.SMOOTH_STONE, BlockRegistry.SMOOTH_STONE_STAIRS.get(), 1, consumer, stairs, "minecraft");
		
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE.get(), BlockRegistry.LIMESTONE_WALL.get(), 1, consumer, walls, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.LIMESTONE_BRICKS.get(), BlockRegistry.LIMESTONE_BRICK_WALL.get(), 1, consumer, walls, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE.get(), BlockRegistry.POLISHED_LIMESTONE_WALL.get(), 1, consumer, walls, ModConstants.MOD_ID);
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get(), BlockRegistry.POLISHED_LIMESTONE_BRICK_WALL.get(), 1, consumer, walls, ModConstants.MOD_ID);
		stoneCuttingFolderModId(Blocks.CUT_RED_SANDSTONE, BlockRegistry.CUT_RED_SANDSTONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.CUT_SANDSTONE, BlockRegistry.CUT_SANDSTONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.DARK_PRISMARINE, BlockRegistry.DARK_PRISMARINE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.POLISHED_ANDESITE, BlockRegistry.POLISHED_ANDESITE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.POLISHED_DIORITE, BlockRegistry.POLISHED_DIORITE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.POLISHED_GRANITE, BlockRegistry.POLISHED_GRANITE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.POLISHED_BLACKSTONE, BlockRegistry.POLISHED_BLACKSTONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.PRISMARINE_BRICKS, BlockRegistry.PRISMARINE_BRICK_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.PURPUR_BLOCK, BlockRegistry.PURPUR_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.QUARTZ_BLOCK, BlockRegistry.QUARTZ_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.SMOOTH_QUARTZ, BlockRegistry.SMOOTH_QUARTZ_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.SMOOTH_RED_SANDSTONE, BlockRegistry.SMOOTH_RED_SANDSTONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.SMOOTH_SANDSTONE, BlockRegistry.SMOOTH_SANDSTONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.SMOOTH_STONE, BlockRegistry.SMOOTH_STONE_WALL.get(), 1, consumer, walls, "minecraft");
		stoneCuttingFolderModId(Blocks.STONE, BlockRegistry.STONE_WALL.get(), 1, consumer, walls, "minecraft");
		
		stoneCuttingFolderModId(BlockRegistry.POLISHED_LIMESTONE.get(), BlockRegistry.CHISELED_LIMESTONE.get(), 1, consumer, "blocks/", ModConstants.MOD_ID);
		stoneCuttingFolderModId(Blocks.PACKED_MUD, Blocks.MUD_BRICKS, 1, consumer, "blocks/", "minecraft");
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.OAK_PLANKS), Blocks.OAK_SLAB, 2).unlockedBy("has_oak_planks", has(Blocks.OAK_PLANKS)).save(consumer, modLoc(folder + slabs + "oak" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SPRUCE_PLANKS), Blocks.SPRUCE_SLAB, 2).unlockedBy("has_spruce_planks", has(Blocks.SPRUCE_PLANKS)).save(consumer, modLoc(folder + slabs + "spruce" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.BIRCH_PLANKS), Blocks.BIRCH_SLAB, 2).unlockedBy("has_birch_planks", has(Blocks.BIRCH_PLANKS)).save(consumer, modLoc(folder + slabs + "birch" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.JUNGLE_PLANKS), Blocks.JUNGLE_SLAB, 2).unlockedBy("has_jungle_planks", has(Blocks.JUNGLE_PLANKS)).save(consumer, modLoc(folder + slabs + "jungle" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRIMSON_PLANKS), Blocks.CRIMSON_SLAB, 2).unlockedBy("has_crimson_planks", has(Blocks.CRIMSON_PLANKS)).save(consumer, modLoc(folder + slabs + "crimson" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.WARPED_PLANKS), Blocks.WARPED_SLAB, 2).unlockedBy("has_warped_planks", has(Blocks.WARPED_PLANKS)).save(consumer, modLoc(folder + slabs + "warped" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.ACACIA_PLANKS), Blocks.ACACIA_SLAB, 2).unlockedBy("has_acacia_planks", has(Blocks.ACACIA_PLANKS)).save(consumer, modLoc(folder + slabs + "acacia" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.DARK_OAK_PLANKS), Blocks.DARK_OAK_SLAB, 2).unlockedBy("has_dark_oak_planks", has(Blocks.DARK_OAK_PLANKS)).save(consumer, modLoc(folder + slabs + "dark_oak" + "_slab"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.MANGROVE_PLANKS), Blocks.MANGROVE_SLAB, 2).unlockedBy("has_mangrove_planks", has(Blocks.MANGROVE_PLANKS)).save(consumer, modLoc(folder + slabs + "mangrove" + "_slab"));
		
		removeStoneCutting(consumer, "cut_copper_slab" + "_from_" + "copper_block" + "_stonecutting");
		removeStoneCutting(consumer, "cut_red_sandstone_slab" + "_from_" + "red_sandstone" + "_stonecutting");
		removeStoneCutting(consumer, "cut_sandstone_slab" + "_from_" + "sandstone" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick_slab" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick_slab" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_slab" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_slab" + "_from_" + "deepslate_bricks" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_slab" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "end_stone_brick_slab" + "_from_" + "end_stone" + "_stonecutting");
		removeStoneCutting(consumer, "exposed_cut_copper_slab" + "_from_" + "exposed_copper" + "_stonecutting");
		removeStoneCutting(consumer, "oxidized_cut_copper_slab" + "_from_" + "oxidized_copper" + "_stonecutting");
		removeStoneCutting(consumer, "polished_andesite_slab" + "_from_" + "andesite" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_slab" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_slab" + "_from_" + "polished_blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_slab" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_deepslate_slab" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "polished_diorite_slab" + "_from_" + "diorite" + "_stonecutting");
		removeStoneCutting(consumer, "polished_granite_slab" + "_from_" + "granite" + "_stonecutting");
		removeStoneCutting(consumer, "stone_brick_slab" + "_from_" + "stone" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_cut_copper_slab" + "_from_" + "waxed_copper_block" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_exposed_cut_copper_slab" + "_from_" + "waxed_exposed_copper" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_oxidized_cut_copper_slab" + "_from_" + "waxed_oxidized_copper" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_weathered_cut_copper_slab" + "_from_" + "waxed_weathered_copper" + "_stonecutting");
		removeStoneCutting(consumer, "weathered_cut_copper_slab" + "_from_" + "weathered_copper" + "_stonecutting");
		
		removeStoneCutting(consumer, "deepslate_brick_wall" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick_wall" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_wall" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_wall" + "_from_" + "deepslate_bricks" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_wall" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "end_stone_brick_wall" + "_from_" + "end_stone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_wall" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_wall" + "_from_" + "polished_blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_deepslate_wall" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_wall" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "stone_brick_walls" + "_from_" + "stone" + "_stonecutting");
		
		removeStoneCutting(consumer, "cut_copper_stairs" + "_from_" + "copper_block" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick_stairs" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick_stairs" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_stairs" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_stairs" + "_from_" + "deepslate_bricks" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_tile_stairs" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "end_stone_brick_stairs" + "_from_" + "end_stone" + "_stonecutting");
		removeStoneCutting(consumer, "exposed_cut_copper_stairs" + "_from_" + "exposed_copper" + "_stonecutting");
		removeStoneCutting(consumer, "oxidized_cut_copper_stairs" + "_from_" + "oxidized_copper" + "_stonecutting");
		removeStoneCutting(consumer, "polished_andesite_stairs" + "_from_" + "andesite" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_stairs" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_brick_stairs" + "_from_" + "polished_blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_stairs" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "polished_deepslate_stairs" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "polished_diorite_stairs" + "_from_" + "diorite" + "_stonecutting");
		removeStoneCutting(consumer, "polished_granite_stairs" + "_from_" + "granite" + "_stonecutting");
		removeStoneCutting(consumer, "stone_brick_stairs" + "_from_" + "stone" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_cut_copper_stairs" + "_from_" + "waxed_copper_block" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_exposed_cut_copper_stairs" + "_from_" + "waxed_exposed_copper" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_oxidized_cut_copper_stairs" + "_from_" + "waxed_oxidized_copper" + "_stonecutting");
		removeStoneCutting(consumer, "waxed_weathered_cut_copper_stairs" + "_from_" + "waxed_weathered_copper" + "_stonecutting");
		removeStoneCutting(consumer, "weathered_cut_copper_stairs" + "_from_" + "weathered_copper" + "_stonecutting");
		
		removeStoneCutting(consumer, "chiseled_polished_blackstone" + "_from_" + "blackstone" + "_stonecutting");
		removeStoneCutting(consumer, "chiseled_stone_bricks_stone" + "_from_" + "stonecutting");
		removeStoneCutting(consumer, "deepslate_brick" + "_from_" + "cobbled_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "deepslate_brick" + "_from_" + "polished_deepslate" + "_stonecutting");
		removeStoneCutting(consumer, "polished_blackstone_bricks" + "_from_" + "polished_blackstone" + "_stonecutting");
		
	}
	
	private static void removeStoneCutting(Consumer<FinishedRecipe> consumer, String string) {
		ConditionalRecipe.builder().addCondition(new ItemExistsCondition(ModConstants.MOD_ID + ":air")).addRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.STONE), Blocks.STONE).unlockedBy("has_stone", has(Blocks.STONE))::save).build(consumer, mcLoc(string));
	}
	
	private static void stoneCuttingFolderModId(Block pIngredientItem, Block pResultItem, int amount, Consumer<FinishedRecipe> consumer, String dir, String modId) {
	      String folder = "stonecutting/";
	      SingleItemRecipeBuilder.stonecutting(Ingredient.of(pIngredientItem), pResultItem, amount).unlockedBy("has_" + StringUtils.toLowerCase(pResultItem.defaultBlockState().getBlock().toString().replace("Block{" + modId + ":", "").replace("}", "")), has(pIngredientItem)).save(consumer, modLoc(folder + dir + StringUtils.toLowerCase(pIngredientItem.defaultBlockState().getBlock().toString().replace("Block{" + modId + ":", "").replace("}", ""))));
	}
	
	private static void stoneCuttingSlabs(Block pIngredientItem, Block pResultItem, Consumer<FinishedRecipe> consumer) {
	      String folder = "stonecutting/";
	      String slabs = "slabs/";
	      SingleItemRecipeBuilder.stonecutting(Ingredient.of(pIngredientItem), pResultItem).unlockedBy("has_" + StringUtils.toLowerCase(pResultItem.defaultBlockState().getBlock().toString().replace("Block{minecraft:", "").replace("}", "")), has(pIngredientItem)).save(consumer, modLoc(folder + slabs + StringUtils.toLowerCase(pIngredientItem.defaultBlockState().getBlock().toString().replace("Block{minecraft:", "").replace("}", ""))));
	}
	
	private static void stoneCuttingStairs(Block pIngredientItem, Block pResultItem, Consumer<FinishedRecipe> consumer) {
	      String folder = "stonecutting/";
	      String stairs = "stairs/";
	      SingleItemRecipeBuilder.stonecutting(Ingredient.of(pIngredientItem), pResultItem).unlockedBy("has_" + StringUtils.toLowerCase(pResultItem.defaultBlockState().getBlock().toString().replace("Block{minecraft:", "").replace("}", "")), has(pIngredientItem)).save(consumer, modLoc(folder + stairs + StringUtils.toLowerCase(pIngredientItem.defaultBlockState().getBlock().toString().replace("Block{minecraft:", "").replace("}", ""))));
	}
	
	private static void stoneSmithing(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Item pIngredientItem, Item pResultItem) {
	      UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), pResultItem).unlocks("has_cobblestone", has(ItemTags.STONE_TOOL_MATERIALS)).save(consumer, resourceLocation);
	}
	
	private static void goldSmithing(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Item pIngredientItem, Item pResultItem) {
	      UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(Items.GOLD_BLOCK), pResultItem).unlocks("has_gold_block", has(Items.GOLD_BLOCK)).save(consumer, resourceLocation);
	}
	
	private static void ironSmithing(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Item pIngredientItem, Item pResultItem) {
	      UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(Items.IRON_BLOCK), pResultItem).unlocks("has_iron_block", has(Items.IRON_BLOCK)).save(consumer, resourceLocation);
	}
	
	private static void diamondSmithing(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Item pIngredientItem, Item pResultItem) {
	      UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(Items.DIAMOND_BLOCK), pResultItem).unlocks("has_diamond_block", has(Items.DIAMOND_BLOCK)).save(consumer, resourceLocation);
	}
	
	private static void netheriteSmithing(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, Item pIngredientItem, Item pResultItem) {
	      UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(Items.NETHERITE_BLOCK), pResultItem).unlocks("has_netherite_block", has(Items.NETHERITE_BLOCK)).save(consumer, resourceLocation);
	}
	
	private static void createGlowingBlocks(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, TagKey<Item> glassFromTag, @NotNull Block block) {
	      ShapedRecipeBuilder.shaped(block, 8)
			.define('G', glassFromTag)
			.define('I', Items.GLOW_INK_SAC)
			.pattern("GGG")
			.pattern("GIG")
			.pattern("GGG")
			.unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
			.save(consumer, resourceLocation);
	}
	
	private static void glowingBlockToPane(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, @NotNull Block input, @NotNull Block output) {
	      ShapedRecipeBuilder.shaped(output, 48)
			.define('G', input)
			.pattern("GGG")
			.pattern("GGG")
			.unlockedBy("has_glass_pane", has(Items.GLASS_PANE))
			.save(consumer, resourceLocation);
	}
	
	
	protected static RecipeBuilder stairBuilder(ItemLike p_176711_, Ingredient p_176712_) {
		return ShapedRecipeBuilder.shaped(p_176711_, 6).define('#', p_176712_).pattern("#  ").pattern("## ").pattern("###");
	}
	
	private static final Map<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> shapeBuilders = ImmutableMap.<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder()
			.put(BlockFamily.Variant.STAIRS, (block, stairs) -> {
				return stairBuilder(block, Ingredient.of(stairs));
			}).build();
	
	private static final Map<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>> slabBuilders = ImmutableMap.<BlockFamily.Variant, BiFunction<ItemLike, ItemLike, RecipeBuilder>>builder()
			.put(BlockFamily.Variant.SLAB, (block, slab) -> {
				return fullBlockBuilder(block, Ingredient.of(slab));
			})
			.build();
		
	private static RecipeBuilder fullBlockBuilder(ItemLike slabs, Ingredient pMaterial) {
	      return ShapedRecipeBuilder.shaped(slabs)
	    		  .define('#', pMaterial)
	    		  .pattern("#")
	    		  .pattern("#");
	}

	protected static void generateRecipes(Consumer<FinishedRecipe> consumer, BlockFamily pFamily) {
		pFamily.getVariants().forEach((block, p_176530_) -> {
	         BiFunction<ItemLike, ItemLike, RecipeBuilder> bifunction = shapeBuilders.get(block);
	         ItemLike itemlike = getBaseBlock(pFamily, block);
	         if (bifunction != null) {
	            RecipeBuilder recipebuilder = bifunction.apply(p_176530_, itemlike);
	            pFamily.getRecipeGroupPrefix().ifPresent((p_176601_) -> {
	               recipebuilder.group(p_176601_ + (block == BlockFamily.Variant.CUT ? "" : "_" + block.getName()));
	            });
	            recipebuilder.unlockedBy(pFamily.getRecipeUnlockedBy().orElseGet(() -> {
	               return getHasName(itemlike);
	            }), has(itemlike));
	            recipebuilder.save(consumer);
	         }
	         if (block == BlockFamily.Variant.CRACKED) {
	            smeltingResultFromBase(consumer, p_176530_, itemlike);
	         }
	      });
		pFamily.getVariants()
	      .forEach((block, result) -> {
		         BiFunction<ItemLike, ItemLike, RecipeBuilder> bifunction = slabBuilders.get(block);
		         ItemLike itemlike = getBaseBlock(pFamily, block);
		         if (bifunction != null) {
	        		 RecipeBuilder recipebuilder = bifunction.apply(itemlike, result);
	        		 if(itemlike == Blocks.NETHER_BRICKS || itemlike == Blocks.COBBLED_DEEPSLATE || itemlike == Blocks.POLISHED_BLACKSTONE) {
		        		 return;
		        	 }
	        		 else {
			            pFamily.getRecipeGroupPrefix().ifPresent((p_176601_) -> {
			               recipebuilder.group(p_176601_ + (block == BlockFamily.Variant.CUT ? "" : "_" + block.getName()));
			            });
			            recipebuilder.unlockedBy(pFamily.getRecipeUnlockedBy().orElseGet(() -> {
			               return getHasName(itemlike);
			            }), has(itemlike));
			            recipebuilder.save(consumer, modLoc("shaped/full_blocks/" + StringUtils.toLowerCase(result.getDescriptionId().replace(result.getName() + ":", "").replace("block.minecraft.", ""))));
		        	 }
		         }
	    });
		
	   }
	
	private static ResourceLocation modLoc(String id) {
	    return new ResourceLocation(ModConstants.MOD_ID, id);
	}
	private static ResourceLocation mcLoc(String id) {
	    return new ResourceLocation("minecraft", id);
	}
	
}