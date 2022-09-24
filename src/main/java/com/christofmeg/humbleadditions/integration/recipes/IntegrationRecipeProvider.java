package com.christofmeg.humbleadditions.integration.recipes;

import java.util.function.Consumer;

import com.christofmeg.humbleadditions.integration.IronChestIntegration;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class IntegrationRecipeProvider extends RecipeProvider implements IConditionBuilder {
	
	public IntegrationRecipeProvider(DataGenerator pGenerator) {
		super(pGenerator);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Integration Recipes";
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
			
		this.addIronChestRecipes(consumer);
		this.addAE2Recipes(consumer);

	}
	
	@SuppressWarnings("unused")
	private void addIronChestRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "integration/ironchest/";
		//Iron Chests
/*			ConditionalRecipe.builder()
			.addCondition(new ModLoadedCondition("ironchest"))
			.addCondition(new ModLoadedCondition("ironchests"))
			.addRecipe(
			ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChest.IRON_CHEST_TAG)
			.requires(IronChestIntegration.IronChestRestocked.IRON_CHEST.asItem())
			.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))::save)
			.build(consumer, modLoc(folder + "iron_chest"));
*/

		/*
			ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChestRestocked.IRON_CHEST)
			.requires(IronChestIntegration.IronChest.IRON_CHEST.asItem())
			.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
			.save(consumer);
/*		
			ConditionalRecipe.builder()
			.addCondition(new ModLoadedCondition("ironchest"))
			.addCondition(new ModLoadedCondition("ironchests"))
			.addRecipe(
			ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChestRestocked.IRON_CHEST.asItem())
			.requires(IronChestIntegration.IronChest.IRON_CHEST.asItem())
			.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))::save)
			.build(consumer, modLoc(folder + "iron_chest_reworked"));
*/		
		
		
	}
	
	private void addAE2Recipes(Consumer<FinishedRecipe> consumer) {
		
		ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChestRestocked.IRON_CHEST)
		.requires(IronChestIntegration.IronChest.IRON_CHEST.asItem())
		.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
		.save(consumer);
	}
	
	@SuppressWarnings("unused")
	private void modLoadedShapeless(String modId, Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation, ItemLike output, TagKey<Item> input, String type, Item has) {
		ConditionalRecipe.builder()
		.addCondition(modLoaded(modId))
		.addRecipe(
				ShapelessRecipeBuilder.shapeless(output)
				.requires(input)
				.unlockedBy("has_" + type, has(has))::save
		)
		.build(consumer, resourceLocation);
		
	}
	
	
	@SuppressWarnings("unused")
	private static ResourceLocation modLoc(String id) {
	    return new ResourceLocation(ModConstants.MOD_ID, id);
	}
	
}
