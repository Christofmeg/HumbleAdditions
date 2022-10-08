package com.christofmeg.humbleadditions.common.world;

import com.christofmeg.humbleadditions.common.world.feature.ModIcebergFeature;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ModConstants.MOD_ID);

	public static final RegistryObject<Feature<BlockStateConfiguration>> ICEBERG = FEATURES.register("iceberg", () -> new ModIcebergFeature(BlockStateConfiguration.CODEC));

}
