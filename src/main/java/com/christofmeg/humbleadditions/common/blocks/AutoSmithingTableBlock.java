package com.christofmeg.humbleadditions.common.blocks;

import org.jetbrains.annotations.Nullable;

import com.christofmeg.humbleadditions.common.blocks.entities.AutoSmithingTableBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AutoSmithingTableBlock extends Block implements EntityBlock {

	public AutoSmithingTableBlock(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AutoSmithingTableBlockEntity(pos, state);
	}

	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		return super.use(state, level, pos, player, hand, hitResult);
	}

}

//TODO https://github.com/LexManos/CobbleForDays/blob/master/src/main/java/net/minecraftforge/lex/cfd/CobbleGenBlock.java