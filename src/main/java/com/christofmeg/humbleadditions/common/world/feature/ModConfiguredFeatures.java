package com.christofmeg.humbleadditions.common.world.feature;

import java.util.List;
import java.util.Optional;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = 
		DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ModConstants.MOD_ID);
	public static final DeferredRegister<ConfiguredFeature<?, ?>> VANILLA_CONFIGURED_FEATURES = 
			DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, "minecraft"); 

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_FEATURES = Suppliers.memoize(() -> List.of(
		OreConfiguration.target(new BlockMatchTest(Blocks.STONE), BlockRegistry.LIMESTONE.get().defaultBlockState()),
		OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), BlockRegistry.LIMESTONE.get().defaultBlockState()),
		OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), BlockRegistry.LIMESTONE.get().defaultBlockState())	
		));
		public static final RegistryObject<ConfiguredFeature<?, ?>> OVERWORLD_BLOCK_FEATURE = CONFIGURED_FEATURES.register("overworld_blocks", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_FEATURES.get(), 32)));
	
	public static final Supplier<List<OreConfiguration.TargetBlockState>> QUICK_SAND_FEATURES = Suppliers.memoize(() -> List.of(
		OreConfiguration.target(new BlockMatchTest(Blocks.SAND), BlockRegistry.QUICK_SAND.get().defaultBlockState())
		));
		public static final RegistryObject<ConfiguredFeature<?, ?>> QUICK_SAND_BLOCK_FEATURE = CONFIGURED_FEATURES.register("quick_sand_blocks", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(QUICK_SAND_FEATURES.get(), 16)));
	
	public static final Supplier<List<OreConfiguration.TargetBlockState>> END_FEATURES = Suppliers.memoize(() -> List.of(
		OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), BlockRegistry.ENDORIUM_ORE.get().defaultBlockState())	
		));																																																			//VEIN SIZE
		public static final RegistryObject<ConfiguredFeature<?, ?>> END_BLOCK_FEATURE = CONFIGURED_FEATURES.register("end_blocks", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_FEATURES.get(), 4)));

	private static final BeehiveDecorator BEEHIVE_001 = new BeehiveDecorator(0.01F);
	@SuppressWarnings("deprecation")
	public static final RegistryObject<ConfiguredFeature<?, ?>> MANGROVE =
		VANILLA_CONFIGURED_FEATURES.register("mangrove", () ->
		new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(Blocks.MANGROVE_LOG), new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformInt.of(1, 4), 0.5F, UniformInt.of(0, 1), 
				Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), 
			BlockStateProvider.simple(Blocks.MANGROVE_LEAVES), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70), 
			Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(Blocks.MANGROVE_ROOTS), 
				Optional.of(new AboveRootPlacement(BlockStateProvider.simple(BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState()), 0.5F)), new MangroveRootPlacement(
					Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), 
					BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))), new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(new LeaveVineDecorator(0.125F), new AttachedToLeavesDecorator(0.14F, 1, 0, new RandomizedIntStateProvider(
						BlockStateProvider.simple(Blocks.MANGROVE_PROPAGULE.defaultBlockState().setValue(MangrovePropaguleBlock.HANGING, Boolean.valueOf(true))), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)), BEEHIVE_001)).ignoreVines().build()));
	
	@SuppressWarnings("deprecation")
	public static final RegistryObject<ConfiguredFeature<?, ?>> TALL_MANGROVE =
		VANILLA_CONFIGURED_FEATURES.register("tall_mangrove", () ->
		new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(Blocks.MANGROVE_LOG), new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformInt.of(1, 4), 0.5F, UniformInt.of(0, 1), 
				Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), 
			BlockStateProvider.simple(Blocks.MANGROVE_LEAVES), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70), 
			Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(Blocks.MANGROVE_ROOTS), 
				Optional.of(new AboveRootPlacement(BlockStateProvider.simple(BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState()), 0.5F)), new MangroveRootPlacement(
					Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), 
					BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))), new TwoLayersFeatureSize(2, 0, 2)).decorators(List.of(new LeaveVineDecorator(0.125F), new AttachedToLeavesDecorator(0.14F, 1, 0, new RandomizedIntStateProvider(
						BlockStateProvider.simple(Blocks.MANGROVE_PROPAGULE.defaultBlockState().setValue(MangrovePropaguleBlock.HANGING, Boolean.valueOf(true))), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)), BEEHIVE_001)).ignoreVines().build()));

	public static final RegistryObject<ConfiguredFeature<?, ?>> MANGROVE_VEGETATION =
		VANILLA_CONFIGURED_FEATURES.register("mangrove_vegetation", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
            new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                ModPlacedFeatures.MANGROVE_CHECKED.getHolder().get(),
                0.85F)), ModPlacedFeatures.MANGROVE_CHECKED.getHolder().get())));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> MOD_MOSS_VEGETATION =
		VANILLA_CONFIGURED_FEATURES.register("moss_vegetation", () -> new ConfiguredFeature<>(
			Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
				.add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
				.add(Blocks.AZALEA.defaultBlockState(), 7)
				.add(BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState(), 25)
				.add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10)
				))));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> MOD_MOSS_PATCH_BONEMEAL =
		VANILLA_CONFIGURED_FEATURES.register("moss_patch_bonemeal", () -> new ConfiguredFeature<>(
			Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(ModConfiguredFeatures.MOD_MOSS_VEGETATION.getHolder().get()), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F)	
				
				));

}
