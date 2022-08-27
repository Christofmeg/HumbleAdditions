package com.christofmeg.humbleadditions.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SandLayerBlock extends FallableLayerBlock {
	private final int dustColor;

	public SandLayerBlock(int p_55967_, BlockBehaviour.Properties p_56585_) {
		super(p_56585_);
		this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, Integer.valueOf(1)));
		this.dustColor = p_55967_;
	}
   
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getDustColor(BlockState p_55970_, BlockGetter p_55971_, BlockPos p_55972_) {
		return this.dustColor;
	}

}