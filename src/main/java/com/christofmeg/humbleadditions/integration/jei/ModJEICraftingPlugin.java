package com.christofmeg.humbleadditions.integration.jei;

import com.christofmeg.humbleadditions.setup.ModConstants;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class ModJEICraftingPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(ModConstants.MOD_ID, "jei_plugin");
	}




	/*
	public static final ResourceLocation MOB = new ResourceLocation("jeresources", "mob");
	public static final RecipeType<MobWrapper> MOB_TYPE = new RecipeType<>(MOB, MobWrapper.class);

	public static final Map<ResourceLocation, RecipeType<?>> TYPES = new HashMap<>();
	static {
		TYPES.put(MOB, MOB_TYPE);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		//        registration.addRecipes(DUNGEON_TYPE, asRecipes(DungeonRegistry.getInstance().getDungeons(), DungeonWrapper::new));
		//       registration.addRecipes(ENCHANTMENT_TYPE, EnchantmentMaker.createRecipes(registration.getIngredientManager().getAllIngredients(VanillaTypes.ITEM_STACK)));
		//		registration.addRecipes(MOB_TYPE, asRecipes(MobRegistry.getInstance().getMobs(), MobWrapper::new));
		registration.addRecipes(MOB_TYPE, asRecipes(MobRegistry.getInstance().getMobs(), ModMobWrapper::new));
		//       registration.addRecipes(PLANT_TYPE, asRecipes(PlantRegistry.getInstance().getAllPlants(), PlantWrapper::new));
		//      registration.addRecipes(VILLAGER_TYPE, asRecipes(VillagerRegistry.getInstance().getVillagers(), VillagerWrapper::new));
		//     registration.addRecipes(WORLD_GEN_TYPE, asRecipes(WorldGenRegistry.getInstance().getWorldGen(), WorldGenWrapper::new));
	}
	 */



	/*
	//Use to show what blocks crafting could be done in
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		//		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.SPRUCE_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
		//TODO		registration.addRecipeCatalyst(new ItemStack(ItemRegistry.RED_HUSK_SPAWN_EGG.get()), JEIConfig.MOB_TYPE);

		ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).filter(egg -> (egg instanceof SpawnEggItem)).forEach(egg -> {
			registration.addRecipeCatalyst(new ItemStack(egg), JEIConfig.MOB_TYPE);
		});
		ItemRegistry.ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get).filter(egg -> (egg instanceof SpawnEggItem)).forEach(egg -> {
			registration.addRecipeCatalyst(new ItemStack(egg), JEIConfig.MOB_TYPE);
		});

	}
	 */
	//	@Override
	//	public void registerRecipes(IRecipeRegistration registration) {
	//registration.addRecipes(JEIConfig.MOB_TYPE, asRecipes(MobRegistry.getInstance().getMobs(), MobWrapper::new));

	//	}




}
