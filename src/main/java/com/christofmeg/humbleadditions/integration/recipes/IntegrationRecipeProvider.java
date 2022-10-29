package com.christofmeg.humbleadditions.integration.recipes;

import java.util.function.Consumer;

import com.christofmeg.humbleadditions.integration.ExNihilioSequentiaIntegration;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.simibubi.create.content.contraptions.components.crusher.CrushingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder;

import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.Mekanism;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

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
		this.addMekanismRecipes(consumer);
		this.addCreateRecipes(consumer);

	}

	private void addMekanismRecipes(Consumer<FinishedRecipe> consumer) {
		String crushing = "crushing/";
		String enriching = "enriching/";

		ConditionalRecipe.builder()
		.addCondition(new ModLoadedCondition("exnihilosequentia"))
		.addRecipe(
				ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(Tags.Items.SAND), new ItemStack(ExNihilioSequentiaIntegration.DUST.get()))::build
				)
		.build(consumer, Mekanism.rl(crushing + "sand_to_dust"));

		ConditionalRecipe.builder()
		.addCondition(new ModLoadedCondition("exnihilosequentia"))
		.addRecipe(
				ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(ExNihilioSequentiaIntegration.DUST.get()), new ItemStack(Items.SAND))::build
				)
		.build(consumer, Mekanism.rl(enriching + "dust_to_sand"));
	}

	private void addCreateRecipes(Consumer<FinishedRecipe> consumer) {
		crushingCreate(ExNihilioSequentiaIntegration.DUST.get(), Items.SAND, 150, consumer, "dust", "exnihilosequentia");
	}

	private void crushingCreate(ItemLike input, ItemLike result, int time, Consumer<FinishedRecipe> consumer, String recipeName, String modLoaded){
		ProcessingRecipeBuilder<CrushingRecipe> crushingRecipeBuilder =
				new ProcessingRecipeBuilder<>(CrushingRecipe::new, otherModLoc("create", "crushing/" + recipeName));

		ConditionalRecipe.builder()
		.addCondition(new ModLoadedCondition(modLoaded))
		.addRecipe(
				crushingRecipeBuilder.withItemIngredients(Ingredient.of(result))
				.output(input)
				.duration(time)::build
				)
		.build(consumer, otherModLoc("create", "crushing/" + recipeName));
	}


	@SuppressWarnings("unused")
	private void addIronChestRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "integration/ironchest/";
		//Iron Chests
		/*		ConditionalRecipe.builder()
		.addCondition(new ModLoadedCondition("ironchest"))
		.addCondition(new ModLoadedCondition("ironchests"))
		.addRecipe(
				ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChest.IRON_CHEST.get())
				.requires(IronChestIntegration.IronChestRestocked.IRON_CHEST.get())
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
		/*
		ShapelessRecipeBuilder.shapeless(IronChestIntegration.IronChestRestocked.IRON_CHEST)
		.requires(IronChestIntegration.IronChest.IRON_CHEST.asItem())
		.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
		.save(consumer);
		 */
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

	private static ResourceLocation otherModLoc(String mod, String id) {
		return new ResourceLocation(mod, id);
	}

}
