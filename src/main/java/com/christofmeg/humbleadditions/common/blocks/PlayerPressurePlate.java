package com.christofmeg.humbleadditions.common.blocks;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

public class PlayerPressurePlate extends BasePressurePlateBlock {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public final PlayerPressurePlate.Sensitivity sensitivity;
	
	public PlayerPressurePlate(PlayerPressurePlate.Sensitivity pSensitivity, BlockBehaviour.Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)));
	    this.sensitivity = pSensitivity;
	}

	@Override
	protected int getSignalForState(BlockState pState) {
		return pState.getValue(POWERED) ? 15 : 0;
	}

	@Override
	protected BlockState setSignalForState(BlockState pState, int pStrength) {
		return pState.setValue(POWERED, Boolean.valueOf(pStrength > 0));
	}
	
	@Override
	protected void playOnSound(LevelAccessor pLevel, BlockPos pPos) {
		pLevel.playSound((Player)null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
	}

	@Override
	protected void playOffSound(LevelAccessor pLevel, BlockPos pPos) {
		pLevel.playSound((Player)null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
	}

	@Override
	protected int getSignalStrength(Level pLevel, BlockPos pPos) {
	      AABB aabb = TOUCH_AABB.move(pPos);
	      List<? extends Entity> list;
	      switch (this.sensitivity) {
	         case PLAYER:
	            list = pLevel.getEntitiesOfClass(Player.class, aabb);
	            break;
	         default:
	            return 0;
	      }

	      if (!list.isEmpty()) {
	         for(Entity entity : list) {
	            if (!entity.isIgnoringBlockTriggers()) {
	               return 15;
	            }
	         }
	      }

	      return 0;
	   }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
	      pBuilder.add(POWERED);
	   }
	
	public static enum Sensitivity {
	      PLAYER,
	}

}
