package com.christofmeg.humbleadditions.data.recipe;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

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
			
			
		this.addShapedRecipes(consumer);	
//		this.addBlastingRecipes(consumer);
		this.addSmeltingRecipes(consumer);
//		this.addStonecuttingRecipes(consumer);
		
		this.addShapelessRecipes(consumer);
		this.addSmithingRecipes(consumer);
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
		
		stoneSmithing(consumer, modLocation(folder + stone + "axe"), Items.WOODEN_AXE, Items.STONE_AXE);
		stoneSmithing(consumer, modLocation(folder + stone + "hoe"), Items.WOODEN_HOE, Items.STONE_HOE);
		stoneSmithing(consumer, modLocation(folder + stone + "pickaxe"), Items.WOODEN_PICKAXE, Items.STONE_PICKAXE);
		stoneSmithing(consumer, modLocation(folder + stone + "shovel"), Items.WOODEN_SHOVEL, Items.STONE_SHOVEL);
		stoneSmithing(consumer, modLocation(folder + stone + "sword"), Items.WOODEN_SWORD, Items.STONE_SWORD);
		
		goldSmithing(consumer, modLocation(folder + gold + "axe"), Items.STONE_AXE, Items.GOLDEN_AXE);
		goldSmithing(consumer, modLocation(folder + gold + "hoe"), Items.STONE_HOE, Items.GOLDEN_HOE);
		goldSmithing(consumer, modLocation(folder + gold + "pickaxe"), Items.STONE_PICKAXE, Items.GOLDEN_PICKAXE);
		goldSmithing(consumer, modLocation(folder + gold + "shovel"), Items.STONE_SHOVEL, Items.GOLDEN_SHOVEL);
		goldSmithing(consumer, modLocation(folder + gold + "sword"), Items.STONE_SWORD, Items.GOLDEN_SWORD);
		
		ironSmithing(consumer, modLocation(folder + iron + "axe"), Items.GOLDEN_AXE, Items.IRON_AXE);
		ironSmithing(consumer, modLocation(folder + iron + "hoe"), Items.GOLDEN_HOE, Items.IRON_HOE);
		ironSmithing(consumer, modLocation(folder + iron + "pickaxe"), Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE);
		ironSmithing(consumer, modLocation(folder + iron + "shovel"), Items.GOLDEN_SHOVEL, Items.IRON_SHOVEL);
		ironSmithing(consumer, modLocation(folder + iron + "sword"), Items.GOLDEN_SWORD, Items.IRON_SWORD);
		
		diamondSmithing(consumer, modLocation(folder + diamond + "axe"), Items.IRON_AXE, Items.DIAMOND_AXE);
		diamondSmithing(consumer, modLocation(folder + diamond + "hoe"), Items.IRON_HOE, Items.DIAMOND_HOE);
		diamondSmithing(consumer, modLocation(folder + diamond + "pickaxe"), Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE);
		diamondSmithing(consumer, modLocation(folder + diamond + "shovel"), Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL);
		diamondSmithing(consumer, modLocation(folder + diamond + "sword"), Items.IRON_SWORD, Items.DIAMOND_SWORD);
		
		ironSmithing(consumer, modLocation(folder + chain + "helmet"), Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET);
		ironSmithing(consumer, modLocation(folder + chain + "chestplate"), Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE);
		ironSmithing(consumer, modLocation(folder + chain + "leggings"), Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS);
		ironSmithing(consumer, modLocation(folder + chain + "boots"), Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS);
		
		goldSmithing(consumer, modLocation(folder + gold + "helmet"), Items.CHAINMAIL_HELMET, Items.GOLDEN_HELMET);
		goldSmithing(consumer, modLocation(folder + gold + "chestplate"), Items.CHAINMAIL_CHESTPLATE, Items.GOLDEN_CHESTPLATE);
		goldSmithing(consumer, modLocation(folder + gold + "leggings"), Items.CHAINMAIL_LEGGINGS, Items.GOLDEN_LEGGINGS);
		goldSmithing(consumer, modLocation(folder + gold + "boots"), Items.CHAINMAIL_BOOTS, Items.GOLDEN_BOOTS);
		
		ironSmithing(consumer, modLocation(folder + iron + "helmet"), Items.GOLDEN_HELMET, Items.IRON_HELMET);
		ironSmithing(consumer, modLocation(folder + iron + "chestplate"), Items.GOLDEN_CHESTPLATE, Items.IRON_CHESTPLATE);
		ironSmithing(consumer, modLocation(folder + iron + "leggings"), Items.GOLDEN_LEGGINGS, Items.IRON_LEGGINGS);
		ironSmithing(consumer, modLocation(folder + iron + "boots"), Items.GOLDEN_BOOTS, Items.IRON_BOOTS);
		
		diamondSmithing(consumer, modLocation(folder + diamond + "helmet"), Items.IRON_HELMET, Items.DIAMOND_HELMET);
		diamondSmithing(consumer, modLocation(folder + diamond + "chestplate"), Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
		diamondSmithing(consumer, modLocation(folder + diamond + "leggings"), Items.IRON_LEGGINGS, Items.DIAMOND_LEGGINGS);
		diamondSmithing(consumer, modLocation(folder + diamond + "boots"), Items.IRON_BOOTS, Items.DIAMOND_BOOTS);
		
		ironSmithing(consumer, modLocation(folder + horse + iron), Items.LEATHER_HORSE_ARMOR, Items.IRON_HORSE_ARMOR);
		goldSmithing(consumer, modLocation(folder + horse + gold), Items.IRON_HORSE_ARMOR, Items.GOLDEN_HORSE_ARMOR);
		diamondSmithing(consumer, modLocation(folder + horse + diamond), Items.GOLDEN_HORSE_ARMOR, Items.DIAMOND_HORSE_ARMOR);
		netheriteSmithing(consumer, modLocation(folder + horse + netherite), Items.DIAMOND_HORSE_ARMOR, ItemRegistry.NETHERITE_HORSE_ARMOR.get());
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
	
	private static ResourceLocation modLocation(String id) {
	    return new ResourceLocation(ModConstants.MOD_ID, id);
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
			.save(consumer, modLocation(folder + "smooth_ice"));
		
		//JACK O'SOUL LANTERN
			ShapedRecipeBuilder.shaped(BlockRegistry.JACK_O_SOUL_LANTERN.get())
			.define('S', Items.SOUL_TORCH)
			.define('C', Items.CARVED_PUMPKIN)
			.pattern("C")
			.pattern("S")
			.unlockedBy("has_soul_torch", has(Items.SOUL_TORCH))
			.save(consumer, modLocation(folder + "jack_o_soul_lantern"));
		
		//CHARCOAL BLOCK FROM ITEM
			ShapedRecipeBuilder.shaped(BlockRegistry.CHARCOAL_BLOCK.get())
			.define('C', Items.CHARCOAL)
			.pattern("CCC")
			.pattern("CCC")
			.pattern("CCC")
			.unlockedBy("has_charcoal", has(Items.CHARCOAL))
			.save(consumer, modLocation(folder + "charcoal_block"));
		
		//MOSS BLOCK && LAYER BLOCKS
			ShapedRecipeBuilder.shaped(Items.MOSSY_COBBLESTONE)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.define('S', Tags.Items.COBBLESTONE)
			.pattern("CCC")
			.pattern("CSC")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLocation(folder + "mossy_cobblestone"));
			
			ShapedRecipeBuilder.shaped(Items.MOSSY_STONE_BRICKS)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.define('S', Items.STONE_BRICKS)
			.pattern("CCC")
			.pattern("CSC")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLocation(folder + "mossy_stone_bricks"));
			
			ShapedRecipeBuilder.shaped(Items.MOSS_BLOCK)
			.define('C', BlockRegistry.MOSS_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLocation(folder + "moss_block"));
			
			ShapedRecipeBuilder.shaped(Items.SAND)
			.define('C', BlockRegistry.SAND_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_sand", has(Items.SAND))
			.save(consumer, modLocation(folder + "sand"));
			
			ShapedRecipeBuilder.shaped(Items.RED_SAND)
			.define('C', BlockRegistry.RED_SAND_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_sand", has(Items.RED_SAND))
			.save(consumer, modLocation(folder + "red_sand"));
			
			ShapedRecipeBuilder.shaped(Items.GRAVEL)
			.define('C', BlockRegistry.GRAVEL_LAYER_BLOCK.get())
			.pattern("CCC")
			.pattern("C C")
			.pattern("CCC")
			.unlockedBy("has_gravel", has(Items.GRAVEL))
			.save(consumer, modLocation(folder + "gravel"));
			
		//GLASS TO GLOWING GLASS	
			createGlowingBlocks(consumer, modLocation(folder + "glowing_glass"), Tags.Items.GLASS_COLORLESS, BlockRegistry.GLOWING_GLASS.get());
			
			createGlowingBlocks(consumer, modLocation(folder + "black" + "stained_glowing_glass"), Tags.Items.GLASS_BLACK, BlockRegistry.BLACK_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "blue" + "stained_glowing_glass"), Tags.Items.GLASS_BLUE, BlockRegistry.BLUE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "brown" + "stained_glowing_glass"), Tags.Items.GLASS_BROWN, BlockRegistry.BROWN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "cyan" + "stained_glowing_glass"), Tags.Items.GLASS_CYAN, BlockRegistry.CYAN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "gray" + "stained_glowing_glass"), Tags.Items.GLASS_GRAY, BlockRegistry.GRAY_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "green" + "stained_glowing_glass"), Tags.Items.GLASS_GREEN, BlockRegistry.GREEN_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "light_blue" + "stained_glowing_glass"), Tags.Items.GLASS_LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "light_gray" + "stained_glowing_glass"), Tags.Items.GLASS_LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "lime" + "stained_glowing_glass"), Tags.Items.GLASS_LIME, BlockRegistry.LIME_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "magenta" + "stained_glowing_glass"), Tags.Items.GLASS_MAGENTA, BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "orange" + "stained_glowing_glass"), Tags.Items.GLASS_ORANGE, BlockRegistry.ORANGE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "pink" + "stained_glowing_glass"), Tags.Items.GLASS_PINK, BlockRegistry.PINK_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "purple" + "stained_glowing_glass"), Tags.Items.GLASS_PURPLE, BlockRegistry.PURPLE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "red" + "stained_glowing_glass"), Tags.Items.GLASS_RED, BlockRegistry.RED_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "white" + "stained_glowing_glass"), Tags.Items.GLASS_WHITE, BlockRegistry.WHITE_STAINED_GLOWING_GLASS.get());
			createGlowingBlocks(consumer, modLocation(folder + "yellow" + "stained_glowing_glass"), Tags.Items.GLASS_YELLOW, BlockRegistry.YELLOW_STAINED_GLOWING_GLASS.get());
		
			createGlowingBlocks(consumer, modLocation(folder + "glowing_glass_pane"), Tags.Items.GLASS_PANES_COLORLESS, BlockRegistry.GLOWING_GLASS_PANE.get());

			createGlowingBlocks(consumer, modLocation(folder + "black" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BLACK, BlockRegistry.BLACK_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "blue" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BLUE, BlockRegistry.BLUE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "brown" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_BROWN, BlockRegistry.BROWN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "cyan" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_CYAN, BlockRegistry.CYAN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "gray" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_GRAY, BlockRegistry.GRAY_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "green" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_GREEN, BlockRegistry.GREEN_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "light_blue" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "light_gray" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "lime" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_LIME, BlockRegistry.LIME_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "magenta" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_MAGENTA, BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "orange" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_ORANGE, BlockRegistry.ORANGE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "pink" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_PINK, BlockRegistry.PINK_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "purple" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_PURPLE, BlockRegistry.PURPLE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "red" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_RED, BlockRegistry.RED_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "white" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_WHITE, BlockRegistry.WHITE_STAINED_GLOWING_GLASS_PANE.get());
			createGlowingBlocks(consumer, modLocation(folder + "yellow" + "stained_glowing_glass_pane"), Tags.Items.GLASS_PANES_YELLOW, BlockRegistry.YELLOW_STAINED_GLOWING_GLASS_PANE.get());
		
			glowingBlockToPane(consumer, modLocation(folder + "glowing_glass_pane_from_block"), BlockRegistry.GLOWING_GLASS.get(), BlockRegistry.GLOWING_GLASS_PANE.get());

			glowingBlockToPane(consumer, modLocation(folder + "black" + "stained_glowing_glass_pane_from_block"), BlockRegistry.BLACK_STAINED_GLOWING_GLASS.get(), BlockRegistry.BLACK_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "blue" + "stained_glowing_glass_pane_from_block"), BlockRegistry.BLUE_STAINED_GLOWING_GLASS.get(), BlockRegistry.BLUE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "brown" + "stained_glowing_glass_pane_from_block"), BlockRegistry.BROWN_STAINED_GLOWING_GLASS.get(), BlockRegistry.BROWN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "cyan" + "stained_glowing_glass_pane_from_block"), BlockRegistry.CYAN_STAINED_GLOWING_GLASS.get(), BlockRegistry.CYAN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "gray" + "stained_glowing_glass_pane_from_block"), BlockRegistry.GRAY_STAINED_GLOWING_GLASS.get(), BlockRegistry.GRAY_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "green" + "stained_glowing_glass_pane_from_block"), BlockRegistry.GREEN_STAINED_GLOWING_GLASS.get(), BlockRegistry.GREEN_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "light_blue" + "stained_glowing_glass_pane_from_block"), BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "light_gray" + "stained_glowing_glass_pane_from_block"), BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "lime" + "stained_glowing_glass_pane_from_block"), BlockRegistry.LIME_STAINED_GLOWING_GLASS.get(), BlockRegistry.LIME_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "magenta" + "stained_glowing_glass_pane_from_block"), BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS.get(), BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "orange" + "stained_glowing_glass_pane_from_block"), BlockRegistry.ORANGE_STAINED_GLOWING_GLASS.get(), BlockRegistry.ORANGE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "pink" + "stained_glowing_glass_pane_from_block"), BlockRegistry.PINK_STAINED_GLOWING_GLASS.get(), BlockRegistry.PINK_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "purple" + "stained_glowing_glass_pane_from_block"), BlockRegistry.PURPLE_STAINED_GLOWING_GLASS.get(), BlockRegistry.PURPLE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "red" + "stained_glowing_glass_pane_from_block"), BlockRegistry.RED_STAINED_GLOWING_GLASS.get(), BlockRegistry.RED_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "white" + "stained_glowing_glass_pane_from_block"), BlockRegistry.WHITE_STAINED_GLOWING_GLASS.get(), BlockRegistry.WHITE_STAINED_GLOWING_GLASS_PANE.get());
			glowingBlockToPane(consumer, modLocation(folder + "yellow" + "stained_glowing_glass_pane_from_block"), BlockRegistry.YELLOW_STAINED_GLOWING_GLASS.get(), BlockRegistry.YELLOW_STAINED_GLOWING_GLASS_PANE.get());
			
			
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
	

	private void addShapelessRecipes(Consumer<FinishedRecipe> consumer) {
		
		String folder = "shapeless/";

		//CHARCOAL FROM BLOCK
			ShapelessRecipeBuilder.shapeless(Items.CHARCOAL, 9)
			.requires(BlockRegistry.CHARCOAL_BLOCK.get())
			.unlockedBy("has_charcoal_block", has(BlockRegistry.CHARCOAL_BLOCK.get()))
			.save(consumer, modLocation(folder + "charcoal"));
		
		//MILK BUCKET & MILK BOTTLE
			ShapelessRecipeBuilder.shapeless(Items.MILK_BUCKET)
			.requires(Items.BUCKET)
			.requires(ItemRegistry.MILK_BOTTLE.get(), 3)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(consumer, modLocation(folder + "milk_bucket"));
			
			ShapelessRecipeBuilder.shapeless(ItemRegistry.MILK_BOTTLE.get(), 3)
			.requires(Items.MILK_BUCKET)
			.requires(Items.GLASS_BOTTLE, 3)
			.unlockedBy("has_bucket", has(Items.BUCKET))
			.save(consumer, modLocation(folder + "milk_bottle"));
		
		//MOSS LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.MOSS_LAYER_BLOCK.get(), 8)
			.requires(Items.MOSS_BLOCK)
			.unlockedBy("has_moss_block", has(Items.MOSS_BLOCK))
			.save(consumer, modLocation(folder + "moss_layer_block"));
		//SAND LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.SAND_LAYER_BLOCK.get(), 8)
			.requires(Items.SAND)
			.unlockedBy("has_sand", has(Items.SAND))
			.save(consumer, modLocation(folder + "sand_layer_block"));
		//RED_SAND LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.RED_SAND_LAYER_BLOCK.get(), 8)
			.requires(Items.RED_SAND)
			.unlockedBy("has_sand", has(Items.RED_SAND))
			.save(consumer, modLocation(folder + "red_sand_layer_block"));
		//GRAVEL LAYER BLOCK
			ShapelessRecipeBuilder.shapeless(BlockRegistry.GRAVEL_LAYER_BLOCK.get(), 8)
			.requires(Items.GRAVEL)
			.unlockedBy("has_gravel", has(Items.GRAVEL))
			.save(consumer, modLocation(folder + "gravel_layer_block"));
			
	//TODO USE CUSTOM RECIPE SERIALIZER TO AVOID OVERRIDING VANILLA REGISTRY OF BOATS/MINECARTS
		//CRAFT REMAINDER BOATS
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.OAK_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "oak" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.SPRUCE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "spruce" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.BIRCH_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "birch" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.JUNGLE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "jungle" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.ACACIA_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "acacia" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.DARK_OAK_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "dark_oak" + "_chest_boat"));
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.MANGROVE_CHEST_BOAT)
			.unlockedBy("has_chest_boat", has(ItemTags.CHEST_BOATS))
			.save(consumer, modLocation(folder + "mangrove" + "_chest_boat"));
			
		//CRAFT REMAINDER MINECARTS
			ShapelessRecipeBuilder.shapeless(Items.CHEST)
			.requires(Items.CHEST_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLocation(folder + "chest" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.FURNACE)
			.requires(Items.FURNACE_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLocation(folder + "furnace" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.TNT)
			.requires(Items.TNT_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLocation(folder + "tnt" + "_minecart"));
			ShapelessRecipeBuilder.shapeless(Items.HOPPER)
			.requires(Items.HOPPER_MINECART)
			.unlockedBy("has_minecart", has(Items.MINECART))
			.save(consumer, modLocation(folder + "hopper" + "_minecart"));

			
			
			
			
			
	}
	
	private void addSmeltingRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "smelting/";
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.COBBLESTONE_STAIRS), Items.STONE_STAIRS, 0.1F, 200)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_stairs", has(Items.COBBLESTONE_STAIRS))
			.save(consumer, modLocation(folder + "stone_stairs"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.COBBLESTONE_SLAB), Items.STONE_SLAB, 0.1F, 200)
			.group(ModConstants.MOD_ID)
			.unlockedBy("has_cobblestone_slab", has(Items.COBBLESTONE_SLAB))
			.save(consumer, modLocation(folder + "stone_slab"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_IRON_BLOCK), Items.IRON_BLOCK, 6.3F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
			.save(consumer, modLocation(folder + "raw_iron"));
			
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_COPPER_BLOCK), Items.COPPER_BLOCK, 6.3F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
			.save(consumer, modLocation(folder + "raw_copper"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.RAW_GOLD_BLOCK), Items.GOLD_BLOCK, 9.0F, 1800)
			.group(ModConstants.MOD_ID)
			.unlockedBy("raw_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_GOLD))
			.save(consumer, modLocation(folder + "raw_gold"));

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
			.save(consumer, modLocation(folder + "iron_nugget"));

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
			.save(consumer, modLocation(folder + "iron_ingot"));

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.ANVIL), Items.IRON_BLOCK, 0.1F, 200)
			.unlockedBy("has_anvil", has(Items.ANVIL))
			.save(consumer, modLocation(folder + "iron_block"));
		
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), Items.GOLD_INGOT, 0.1F, 200)
			.unlockedBy("has_light_weighted_pressure_plate", has(Items.LIGHT_WEIGHTED_PRESSURE_PLATE))
			.save(consumer, modLocation(folder + "gold_ingot"));
		
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
			.save(consumer, modLocation(folder + "copper_ingot"));
		
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
			.save(consumer, modLocation(folder + "copper_block"));
		
		SimpleCookingRecipeBuilder.smelting(
				Ingredient.of(
					Tags.Items.STAINED_GLASS), Items.GLASS, 0.1F, 200)
				.unlockedBy("has_glass", has(Items.GLASS))
				.save(consumer, modLocation(folder + "glass"));
		
		//Sand layer blocks to glass pane
			SimpleCookingRecipeBuilder.smelting(
					Ingredient.of(
						BlockRegistry.SAND_LAYER_BLOCK.get()), Items.GLASS_PANE, 0.0125F, 200)
					.unlockedBy("has_glass", has(Items.GLASS))
					.save(consumer, modLocation(folder + "sand_layer"));
			SimpleCookingRecipeBuilder.smelting(
					Ingredient.of(
						BlockRegistry.RED_SAND_LAYER_BLOCK.get()), Items.GLASS_PANE, 0.0125F, 200)
					.unlockedBy("has_glass", has(Items.GLASS))
					.save(consumer, modLocation(folder + "red_sand_layer"));
			
	}
	
}


