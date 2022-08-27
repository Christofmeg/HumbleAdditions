package com.christofmeg.humbleadditions.common.blocks;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FallableLayerBlock extends AbstractLayerBlock implements Fallable, SimpleWaterloggedBlock {

//   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//   public static final IntegerProperty FLOWING_WATER = IntegerProperty.create("water_level", 1, 8);
	
	public FallableLayerBlock(BlockBehaviour.Properties p_56585_) {
	      super(p_56585_);
	      this.registerDefaultState(this.stateDefinition.any()
	    		  .setValue(LAYERS, Integer.valueOf(1))
//	    		  .setValue(WATERLOGGED, false)
	    		  );
	   }

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
		BlockState blockstate = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
		@SuppressWarnings("unused")
		FluidState fluidstate = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
//		boolean flag = fluidstate.getType() == Fluids.WATER || fluidstate.getType() == Fluids.FLOWING_WATER;
//		boolean is_flowing = fluidstate.getType() == Fluids.FLOWING_WATER;
		
		if (blockstate.is(this)) {
			int i = blockstate.getValue(LAYERS);
			return blockstate.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)))
//					.setValue(WATERLOGGED, flag)
//					.setValue(FLOWING_WATER, is_flowing ? fluidstate.getAmount() : 8)
					;
		} else {
			return super.getStateForPlacement(placeContext)
//					.setValue(WATERLOGGED, flag)
//					.setValue(FLOWING_WATER, is_flowing ? fluidstate.getAmount() : 8)
					;
		}
	}
	
	@Override
	   public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
		   return true;
	   }
	
	@Override
	public void onPlace(BlockState p_53233_, Level p_53234_, BlockPos p_53235_, BlockState p_53236_, boolean p_53237_) {
	      p_53234_.scheduleTick(p_53235_, this, this.getDelayAfterPlace());
	   }
		
	@Override
	public BlockState updateShape(BlockState stateSelf, Direction direction, BlockState stateNeighbour, LevelAccessor levelAccess, BlockPos posSelf, BlockPos posNeighbour) {
//		if (stateSelf.getValue(WATERLOGGED)) {
//			   levelAccess.scheduleTick(posSelf, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccess));
//		}
		levelAccess.scheduleTick(posSelf, this, this.getDelayAfterPlace());
		return super.updateShape(stateSelf, direction, stateNeighbour, levelAccess, posSelf, posNeighbour);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
		stateBuilder.add(LAYERS)
//		.add(WATERLOGGED)
//		.add(FLOWING_WATER)
		;
	}
	
	
//	@Override
//	public boolean placeLiquid(LevelAccessor levelAccess, BlockPos pos, BlockState state, FluidState fluidState) {
//		return state.getValue(FLOWING_WATER) != 8 ? placeLayeredLiquid(levelAccess, pos, state, fluidState) : false;
//	   }
	
	@SuppressWarnings("unused")
	private static boolean placeLayeredLiquid(LevelAccessor levelAccess, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER && fluidState.getAmount() != 8) {
			if (!levelAccess.isClientSide()) {
				levelAccess.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				levelAccess.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(levelAccess));
			}
			return true;    
		}
		else {
	         return false;
		}
	}

	/*
	
	   public boolean canPlaceLiquid(BlockGetter p_56363_, BlockPos p_56364_, BlockState state, Fluid p_56366_) {
	      return state.getValue(FLOWING_WATER) != 8 ? SimpleWaterloggedBlock.super.canPlaceLiquid(p_56363_, p_56364_, state, p_56366_) : false;
	   }
	
	
	
	@Override
	public FluidState getFluidState(BlockState state) {
		if (state.getValue(WATERLOGGED) && state.getValue(FLOWING_WATER) == 8) {
			return Fluids.WATER.getSource(false);
		} 
		else if (state.getValue(WATERLOGGED) && state.getValue(FLOWING_WATER) != 8) {
			return Fluids.WATER.getFlowing(state.getValue(FLOWING_WATER), false);
		}
		return Fluids.EMPTY.defaultFluidState();
	}
*/	
	@Override
	   public boolean isPathfindable(BlockState state, BlockGetter p_56377_, BlockPos p_56378_, PathComputationType p_56379_) {
		      switch (p_56379_) {
		         case LAND:
		        	 return state.getValue(LAYERS) < 5;
		         case WATER:
		            return p_56377_.getFluidState(p_56378_).is(FluidTags.WATER);
		         case AIR:
		            return false;
		         default:
		            return false;
		      }
		   }
	
	
	@Override
	public void tick(BlockState p_221124_, ServerLevel p_221125_, BlockPos p_221126_, RandomSource p_221127_) {
		if (isFree(p_221125_.getBlockState(p_221126_.below())) && p_221126_.getY() >= p_221125_.getMinBuildHeight()) {
			FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_221125_, p_221126_, p_221124_);
			fallingblockentity.setUUID(Mth.createInsecureUUID());
			this.falling(fallingblockentity);
		}
	}

	protected void falling(FallingBlockEntity p_53206_) {}

	   protected int getDelayAfterPlace() {
	      return 2;
	   }
	
	   public static boolean isFree(BlockState p_53242_) {
	      Material material = p_53242_.getMaterial();
	      return p_53242_.isAir() || p_53242_.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
	   }
	   
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (random.nextInt(16) == 0) {
			BlockPos blockpos = pos.below();
			if (isFree(level.getBlockState(blockpos))) {
				double d0 = (double)pos.getX() + random.nextDouble();
				double d1 = (double)pos.getY() - 0.05D;
	            double d2 = (double)pos.getZ() + random.nextDouble();
	            level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            level.addParticle(ParticleTypes.UNDERWATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@OnlyIn(Dist.CLIENT)
	   public int getDustColor(BlockState p_53238_, BlockGetter p_53239_, BlockPos p_53240_) {
	      return -16777216;
	   }
	
	@Override
	public void onLand(Level level, BlockPos pos, BlockState blockThatLands, BlockState p_153223_, FallingBlockEntity entity) {
		if(entity instanceof FallingBlockEntity) {
			int fallingLayers = entity.getBlockState().getValue(AbstractLayerBlock.LAYERS);
			if((fallingLayers) == 8) {
				if(blockThatLands.getBlock() == BlockRegistry.SAND_LAYER_BLOCK.get()) {
					level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
				}
				else if(blockThatLands.getBlock() == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
					level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
				}
				else if(blockThatLands.getBlock() == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
					level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
				}
			}
		}
	}

	
	@Override
	public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		if(entity instanceof FallingBlockEntity) {
			int fallingLayers = entity.getBlockState().getValue(AbstractLayerBlock.LAYERS);
			if(entity.getBlockState().getBlock() == BlockRegistry.SAND_LAYER_BLOCK.get()) {
				if(state.getBlock() == BlockRegistry.SAND_LAYER_BLOCK.get()) {
					int layers = state.getValue(AbstractLayerBlock.LAYERS);
					if((fallingLayers + layers) == 15) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 14) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 13) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 12) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 11) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 10) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 9) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 1), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 8) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 7) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 6) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 5) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 4) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 3) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 2) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
				}
				else if(fallingLayers > 1){
					level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.getBlockState().getBlock().asItem(), fallingLayers - 1)));
				}
			}
			
			if(entity.getBlockState().getBlock() == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
				if(state.getBlock() == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
					int layers = state.getValue(AbstractLayerBlock.LAYERS);
					if((fallingLayers + layers) == 15) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 14) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 13) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 12) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 11) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 10) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 9) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 1), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 8) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 7) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 6) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 5) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 4) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 3) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					else if((fallingLayers + layers) == 2) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
				}
				else if(fallingLayers > 1) {
					level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.getBlockState().getBlock().asItem(), fallingLayers - 1)));
				}
			}
			
			if(entity.getBlockState().getBlock() == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
				if(state.getBlock() == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
					int layers = state.getValue(AbstractLayerBlock.LAYERS);
					if((fallingLayers + layers) == 15) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 14) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 13) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 12) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 11) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 10) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 9) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
						level.setBlock(pos.above(), block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 1), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 8) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 7) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 6) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 6), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 5) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 5), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 4) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 4), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 3) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 3), UPDATE_ALL);
					}
					if((fallingLayers + layers) == 2) {
						level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 2), UPDATE_ALL);
					}
				}
				else if(fallingLayers > 1) {
					level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.getBlockState().getBlock().asItem(), fallingLayers - 1)));
				}
			}
			
		}
	}
	
}