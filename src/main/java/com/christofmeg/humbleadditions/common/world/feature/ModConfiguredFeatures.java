package com.christofmeg.humbleadditions.common.world.feature;

import java.util.List;
import java.util.function.Supplier;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.common.base.Suppliers;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = 
		DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ModConstants.MOD_ID); 

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_FEATURES = Suppliers.memoize(() -> List.of(
//		OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.LIMESTONE.get().defaultBlockState())
		OreConfiguration.target(new BlockMatchTest(Blocks.STONE), BlockRegistry.LIMESTONE.get().defaultBlockState()),
		OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), BlockRegistry.LIMESTONE.get().defaultBlockState()),
		OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), BlockRegistry.LIMESTONE.get().defaultBlockState())
			
		));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> OVERWORLD_BLOCK_FEATURE = CONFIGURED_FEATURES.register("overworld_blocks",
																									//VEIN SIZE
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_FEATURES.get(), 32)));
	
			
			
}
