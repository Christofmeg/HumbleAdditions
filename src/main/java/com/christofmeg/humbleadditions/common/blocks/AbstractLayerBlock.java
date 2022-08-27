package com.christofmeg.humbleadditions.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AbstractLayerBlock extends SnowLayerBlock {
   public static final int MAX_HEIGHT = 8;
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   protected static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[]{Shapes.empty(), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), 
		   Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

   public AbstractLayerBlock(BlockBehaviour.Properties p_56585_) {
      super(p_56585_);
      this.registerDefaultState(this.stateDefinition.any()
    		  .setValue(LAYERS, Integer.valueOf(1))
    		  );
     
   }

   @Override
   public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
	   return !levelReader.isEmptyBlock(pos.below());
   }
   
   @Override
   public boolean canBeReplaced(BlockState p_56589_, BlockPlaceContext p_56590_) {
	      int i = p_56589_.getValue(LAYERS);
	      if (p_56590_.getItemInHand().is(this.asItem()) && i < 8) {
	         if (p_56590_.replacingClickedOnBlock()) {
	            return p_56590_.getClickedFace() == Direction.UP;
	         } else {
	            return true;
	         }
	      } else {
	         return false;
	      }
	   }
   
   @Override
   public BlockState updateShape(BlockState p_56606_, Direction p_56607_, BlockState p_56608_, LevelAccessor p_56609_, BlockPos p_56610_, BlockPos p_56611_) {
	      return !p_56606_.canSurvive(p_56609_, p_56610_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_56606_, p_56607_, p_56608_, p_56609_, p_56610_, p_56611_);
	   }
   
   @Override
   public VoxelShape getCollisionShape(BlockState p_56625_, BlockGetter p_56626_, BlockPos p_56627_, CollisionContext p_56628_) {
	      return SHAPE_BY_LAYER[p_56625_.getValue(LAYERS)];
	   }
   

   
}