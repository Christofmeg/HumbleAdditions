package com.christofmeg.humbleadditions.data.world;

import java.util.HashMap;

import com.christofmeg.humbleadditions.setup.ModConstants;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifierProvider {

	@SuppressWarnings("unused")
	public static JsonCodecProvider<BiomeModifier> create(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		RegistryAccess access = RegistryAccess.builtinCopy();
		Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
		HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();
		
//		addModifier(modifiers, "add_monster/red_husk", new AddSpawnsBiomeModifier(tag(biomeRegistry, TagRegistry.Biomes.RED_HUSK_BIOMES), List.of(new MobSpawnSettings.SpawnerData(EntityRegistry.RED_HUSK.get(), 80, 4, 4))));
		
		return JsonCodecProvider.forDatapackRegistry(gen, existingFileHelper, ModConstants.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, access), 
			ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
	}
	
	@SuppressWarnings("unused")
	private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
		return new HolderSet.Named<>(biomeRegistry, tagKey);
	}
	
	@SuppressWarnings("unused")
	private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
		modifiers.put(new ResourceLocation(ModConstants.MOD_ID, name), modifier);
	}
	
}
