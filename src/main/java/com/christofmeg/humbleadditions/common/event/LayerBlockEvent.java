package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.FallableLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.MossLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.entities.PublicFallingBlockEntity;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class LayerBlockEvent {
	
	@SubscribeEvent
	public static void layerblockPlaceEvent(final BlockEvent.EntityPlaceEvent event) {
		LevelAccessor levelAccess = event.getLevel();
		Level level = event.getEntity().getLevel();
		Block blockPlaced = event.getPlacedBlock().getBlock();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		BlockState stateBelow = levelAccess.getBlockState(pos.below());
		Block blockBelow = stateBelow.getBlock();
		int UPDATE_ALL =  3; // public static final int UPDATE_ALL = 3; from #Block.class
		if(blockPlaced instanceof AbstractLayerBlock) {
			if(state.isCollisionShapeFullBlock(levelAccess, pos)) {
				if(blockPlaced == BlockRegistry.MOSS_LAYER_BLOCK.get()) {
					levelAccess.setBlock(pos, Blocks.MOSS_BLOCK.defaultBlockState(), UPDATE_ALL);
				}
				if(blockPlaced == BlockRegistry.SAND_LAYER_BLOCK.get()) {
					levelAccess.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_ALL);
				}
				if(blockPlaced == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
					levelAccess.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), UPDATE_ALL);
				}
				if(blockPlaced == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
					levelAccess.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), UPDATE_ALL);
				}
			}
		}
		
		if(blockPlaced == BlockRegistry.SAND_LAYER_BLOCK.get() && blockBelow == BlockRegistry.SAND_LAYER_BLOCK.get()) {
			if(!level.isClientSide) {
				FallingBlockEntity fallingBlockEntity = new PublicFallingBlockEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, blockPlaced.defaultBlockState().hasProperty(BlockStateProperties.WATERLOGGED) ? blockPlaced.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)) : blockPlaced.defaultBlockState());	
				level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
				levelAccess.addFreshEntity(fallingBlockEntity);
			}
		}
		if(blockPlaced == BlockRegistry.RED_SAND_LAYER_BLOCK.get() && blockBelow == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
			if(!level.isClientSide) {
				FallingBlockEntity fallingBlockEntity = new PublicFallingBlockEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, blockPlaced.defaultBlockState().hasProperty(BlockStateProperties.WATERLOGGED) ? blockPlaced.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)) : blockPlaced.defaultBlockState());
				level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
				levelAccess.addFreshEntity(fallingBlockEntity);
			}
		}
		if(blockPlaced == BlockRegistry.GRAVEL_LAYER_BLOCK.get() && blockBelow == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
			if(!level.isClientSide) {
				FallingBlockEntity fallingBlockEntity = new PublicFallingBlockEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, blockPlaced.defaultBlockState().hasProperty(BlockStateProperties.WATERLOGGED) ? blockPlaced.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)) : blockPlaced.defaultBlockState());	
				level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
				levelAccess.addFreshEntity(fallingBlockEntity);
			}
		}
	}

	@SubscribeEvent
	public static void fullblockPlaceEvent(final PlayerInteractEvent.RightClickBlock event) {
		LevelAccessor level = event.getLevel();
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);
		Block blockHit = state.getBlock();
		ItemStack stack = event.getItemStack();
		if(blockHit == BlockRegistry.MOSS_LAYER_BLOCK.get()) {
			if(stack.getItem() == Items.MOSS_BLOCK) {
				int layers = state.getValue(AbstractLayerBlock.LAYERS);
				if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
						level.setBlock(pos, Blocks.MOSS_BLOCK.defaultBlockState(), 3);
						level.setBlock(pos.above(), BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers), 3);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
				}
			}
		}
		if(blockHit == BlockRegistry.SAND_LAYER_BLOCK.get()) {
			if(stack.getItem() == Items.SAND) {
				int layers = state.getValue(AbstractLayerBlock.LAYERS);
				if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
						level.setBlock(pos, Blocks.SAND.defaultBlockState(), 3);
						level.setBlock(pos.above(), BlockRegistry.SAND_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers), 3);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
				}
			}
		}
		if(blockHit == BlockRegistry.RED_SAND_LAYER_BLOCK.get()) {
			if(stack.getItem() == Items.RED_SAND) {
				int layers = state.getValue(AbstractLayerBlock.LAYERS);
				if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
						level.setBlock(pos, Blocks.RED_SAND.defaultBlockState(), 3);
						level.setBlock(pos.above(), BlockRegistry.RED_SAND_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers), 3);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
				}
			}
		}
		if(blockHit == BlockRegistry.GRAVEL_LAYER_BLOCK.get()) {
			if(stack.getItem() == Items.GRAVEL) {
				int layers = state.getValue(AbstractLayerBlock.LAYERS);
				if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
						level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), 3);
						level.setBlock(pos.above(), BlockRegistry.GRAVEL_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers), 3);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
				}
			}
		}
	}

	@SubscribeEvent
	public static void useToolBlockEvent(final PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		Entity entity = event.getEntity();
		if(!level.isClientSide) {
			if(item instanceof ShovelItem) {
				int damage = stack.getEnchantmentLevel(Enchantments.UNBREAKING);
				if(block instanceof FallableLayerBlock || block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.GRAVEL) {
					if(block instanceof FallableLayerBlock) {
						int layers = state.getValue(AbstractLayerBlock.LAYERS);
						if(layers >= 2) {
							level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers - 1), 3);
							level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block.asItem())));
						} 
						else if(layers == 1) {
							level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
							level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block.asItem())));
						}
					}
					if(block == Blocks.SAND) {
						level.setBlock(pos, BlockRegistry.SAND_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), 3);
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockRegistry.SAND_LAYER_BLOCK.get())));
					}
					if(block == Blocks.RED_SAND) {
						level.setBlock(pos, BlockRegistry.RED_SAND_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), 3);
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockRegistry.RED_SAND_LAYER_BLOCK.get())));
					}
					if(block == Blocks.GRAVEL) {
						level.setBlock(pos, BlockRegistry.GRAVEL_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), 3);
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockRegistry.GRAVEL_LAYER_BLOCK.get())));
					}
					
					if(entity instanceof ServerPlayer) {
						if(!((ServerPlayer) entity).isCreative()) {
							if(Math.random() * 100 <= (100 / 1 + damage)) {
								stack.hurtAndBreak(1, (LivingEntity) event.getEntity(), (p_150686_) -> {
									p_150686_.broadcastBreakEvent(event.getHand());
								});
							}
						}
					}
				}	
			}
			if(item instanceof ShearsItem) {
				int damage = stack.getEnchantmentLevel(Enchantments.UNBREAKING);
				if(block instanceof MossLayerBlock || block == Blocks.MOSS_BLOCK) {
					if(block instanceof MossLayerBlock) {
						int layers = state.getValue(AbstractLayerBlock.LAYERS);
						if(layers >= 2) {
							level.setBlock(pos, block.defaultBlockState().setValue(AbstractLayerBlock.LAYERS, layers - 1), 3);
							level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block.asItem())));
						} 
						else if(layers == 1) {
							level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
							level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block.asItem())));
						}
					}
					if(block == Blocks.MOSS_BLOCK) {
						level.setBlock(pos, BlockRegistry.MOSS_LAYER_BLOCK.get().defaultBlockState().setValue(AbstractLayerBlock.LAYERS, 7), 3);
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockRegistry.MOSS_LAYER_BLOCK.get())));
					}
					if(entity instanceof ServerPlayer) {
						if(!((ServerPlayer) entity).isCreative()) {
							if(Math.random() * 100 <= (100 / 1 + damage)) {
								stack.hurtAndBreak(1, (LivingEntity) event.getEntity(), (p_150686_) -> {
									p_150686_.broadcastBreakEvent(event.getHand());
								});
							}
						}
					}
				}	
			}
		}
	}
		


	
}


