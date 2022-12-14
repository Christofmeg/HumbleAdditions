package com.christofmeg.humbleadditions.data;

import com.christofmeg.humbleadditions.common.world.ModPlacedFeatures;
import com.christofmeg.humbleadditions.data.client.ModBlockStateProvider;
import com.christofmeg.humbleadditions.data.client.ModItemModelProvider;
import com.christofmeg.humbleadditions.data.lang.ModLanguageProvider;
import com.christofmeg.humbleadditions.data.lang.VanillaLanguageProvider;
import com.christofmeg.humbleadditions.data.loot.ModLootTableProvider;
import com.christofmeg.humbleadditions.data.recipe.ModRecipeProvider;
import com.christofmeg.humbleadditions.data.tags.ModBiomeTagsProvider;
import com.christofmeg.humbleadditions.data.tags.ModBlockTagsProvider;
import com.christofmeg.humbleadditions.data.tags.ModEntityTypeTagsProvider;
import com.christofmeg.humbleadditions.data.tags.ModItemTagsProvider;
import com.christofmeg.humbleadditions.data.tags.ModPaintingVariantTagsProvider;
import com.christofmeg.humbleadditions.data.world.ModBiomeModifierProvider;
import com.christofmeg.humbleadditions.integration.recipes.IntegrationRecipeProvider;
import com.christofmeg.humbleadditions.integration.tags.IntegrationTagsProvider;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
	private ModDataGenerators() {}
	private static final String[] LOCALE_CODES = new String[] {"en_us",};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		DataGenerator gen = event.getGenerator();

		if (event.includeClient())
		{
			gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper, null, null));
			gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper ));
		}
		if (event.includeServer()) {
			BlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
			gen.addProvider(true, new ModBlockTagsProvider(gen, existingFileHelper));
			gen.addProvider(true, new ModItemTagsProvider(gen, blockTags, existingFileHelper));
			gen.addProvider(true, new ModRecipeProvider(gen));
			gen.addProvider(true, new ModLootTableProvider(gen));
			gen.addProvider(true, ModBiomeModifierProvider.create(gen, existingFileHelper));
			gen.addProvider(true, new ModBiomeTagsProvider(gen, existingFileHelper));
			gen.addProvider(true, new ModPaintingVariantTagsProvider(gen, existingFileHelper));
			gen.addProvider(true, new ModEntityTypeTagsProvider(gen, existingFileHelper));

			gen.addProvider(true, new IntegrationRecipeProvider(gen));
			gen.addProvider(true, new IntegrationTagsProvider(gen, blockTags, existingFileHelper));
			//		gen.addProvider(true, new IntegrationTagsProvider.IntegrationBlockTagsProvider(gen, existingFileHelper));

		}
		for(String locale : LOCALE_CODES) {
			gen.addProvider(true, new ModLanguageProvider(gen, locale));
			gen.addProvider(true, new VanillaLanguageProvider(gen, locale));
		}

		RegistryAccess registryAccess = RegistryAccess.builtinCopy();
		RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, registryAccess);
		ModPlacedFeatures.biome_modifierDatagen(event, gen, existingFileHelper, registryOps);

	}

}