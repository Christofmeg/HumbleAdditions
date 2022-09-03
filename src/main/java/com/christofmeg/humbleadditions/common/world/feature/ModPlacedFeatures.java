package com.christofmeg.humbleadditions.common.world.feature;

import java.util.List;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
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
