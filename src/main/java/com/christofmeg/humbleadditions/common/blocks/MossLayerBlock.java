package com.christofmeg.humbleadditions.common.blocks;

import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class MossLayerBlock extends AbstractLayerBlock implements BonemealableBlock {

	public MossLayerBlock(BlockBehaviour.Properties p_56585_) {
	super(p_56585_);
	      this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, Integer.valueOf(1)));
	}

   public boolean isValidBonemealTarget(BlockGetter p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean p_153800_) {
	      return p_153797_.getBlockState(p_153798_.above()).isAir();
	}

	public boolean isBonemealSuccess(Level p_221538_, RandomSource p_221539_, BlockPos p_221540_, BlockState p_221541_) {
		return true;
	}

	public void performBonemeal(ServerLevel p_221533_, RandomSource p_221534_, BlockPos p_221535_, BlockState p_221536_) {
		MossLayerBlock.ModCaveFeatures.MOD_MOSS_PATCH_BONEMEAL.value().place(p_221533_, p_221533_.getChunkSource().getGenerator(), p_221534_, p_221535_.above());
	}
	
	public static final class ModCaveFeatures {
		public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> MOD_MOSS_VEGETATION = FeatureUtils.register("moss_vegetation", 
				Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
						.add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
						.add(Blocks.AZALEA.defaultBlockState(), 7)
						.add(BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState(), 25)
						.add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10))));
		
		public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> MOD_MOSS_PATCH_BONEMEAL = FeatureUtils.register("moss_patch_bonemeal", 
				Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), 
						PlacementUtils.inlinePlaced(MOD_MOSS_VEGETATION), 
						CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));
	}
	
}