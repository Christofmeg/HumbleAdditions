package com.christofmeg.humbleadditions.common.world.feature;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.gson.JsonElement;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {

	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
		DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ModConstants.MOD_ID);

	public static final RegistryObject<PlacedFeature> OVERWORLD_BLOCK_PLACED = PLACED_FEATURES.register("overworld_blocks_placed",
        () -> new PlacedFeature(ModConfiguredFeatures.OVERWORLD_BLOCK_FEATURE.getHolder().get(),
        	commonOrePlacement(8, // VeinsPerChunk
        		HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(64)))));
	
	public static final RegistryObject<PlacedFeature> QUICK_SAND_BLOCK_PLACED = PLACED_FEATURES.register("quick_sand_blocks_placed",
	        () -> new PlacedFeature(ModConfiguredFeatures.QUICK_SAND_BLOCK_FEATURE.getHolder().get(),
	        	commonOrePlacement(2, // VeinsPerChunk
	        		HeightRangePlacement.triangle(VerticalAnchor.absolute(62), VerticalAnchor.absolute(64)))));
	
	public static final RegistryObject<PlacedFeature> END_BLOCK_PLACED = PLACED_FEATURES.register("end_blocks_placed",
	        () -> new PlacedFeature(ModConfiguredFeatures.END_BLOCK_FEATURE.getHolder().get(),
	        	rareOrePlacement(1, // VeinsPerChunk
	        		HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128)))));

	private static final ResourceLocation ADD_END_BLOCKS = new ResourceLocation(ModConstants.MOD_ID, "add_end_blocks");
    private static final ResourceLocation ADD_OVERWORLD_BLOCKS = new ResourceLocation(ModConstants.MOD_ID, "add_overworld_blocks");
    private static final ResourceLocation ADD_QUICKS_SAND_BLOCKS = new ResourceLocation(ModConstants.MOD_ID, "add_quick_sand_blocks");
	
	public static void biome_modifierDatagen(GatherDataEvent event, DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper, RegistryOps<JsonElement> registryOps) {
		
		BiomeModifier endBlocksModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow().getOrCreateTag(BiomeTags.IS_END),
                HolderSet.direct(registryOps.registry(Registry.PLACED_FEATURE_REGISTRY).orElseThrow().getOrCreateHolderOrThrow(END_BLOCK_PLACED.getKey())),
                GenerationStep.Decoration.UNDERGROUND_ORES
        );
		BiomeModifier overworldBlocksModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow().getOrCreateTag(BiomeTags.IS_OCEAN),
                HolderSet.direct(registryOps.registry(Registry.PLACED_FEATURE_REGISTRY).orElseThrow().getOrCreateHolderOrThrow(OVERWORLD_BLOCK_PLACED.getKey())),
                GenerationStep.Decoration.UNDERGROUND_DECORATION
        );
		BiomeModifier quickSandModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow().getOrCreateTag(BiomeTags.IS_BEACH),
                HolderSet.direct(registryOps.registry(Registry.PLACED_FEATURE_REGISTRY).orElseThrow().getOrCreateHolderOrThrow(QUICK_SAND_BLOCK_PLACED.getKey())),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        );
		
		generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
                generator, existingFileHelper, ModConstants.MOD_ID, registryOps, ForgeRegistries.Keys.BIOME_MODIFIERS, Map.of(
                        ADD_END_BLOCKS, endBlocksModifier,
                        ADD_OVERWORLD_BLOCKS, overworldBlocksModifier,
                        ADD_QUICKS_SAND_BLOCKS, quickSandModifier
                )
        ));

	}

	public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier1, PlacementModifier placementModifier2) {
        return List.of(placementModifier1, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int value, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(value), placementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int value, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(value), placementModifier);
    }
	
}
