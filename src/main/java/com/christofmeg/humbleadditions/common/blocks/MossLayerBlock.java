package com.christofmeg.humbleadditions.common.blocks;

import com.christofmeg.humbleadditions.common.world.ModConfiguredFeatures;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MossLayerBlock extends AbstractLayerBlock implements BonemealableBlock {

	public MossLayerBlock(BlockBehaviour.Properties p_56585_) {
	super(p_56585_);
	      this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, Integer.valueOf(1)));
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean p_153800_) {
		return p_153797_.getBlockState(p_153798_.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level p_221538_, RandomSource p_221539_, BlockPos p_221540_, BlockState p_221541_) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel p_221533_, RandomSource p_221534_, BlockPos p_221535_, BlockState p_221536_) {
		ModConfiguredFeatures.MOD_MOSS_PATCH_BONEMEAL.getHolder().get().value().place(p_221533_, p_221533_.getChunkSource().getGenerator(), p_221534_, p_221535_.above());
	}
	
}