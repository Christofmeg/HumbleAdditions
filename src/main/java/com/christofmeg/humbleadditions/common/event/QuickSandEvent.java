package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.blocks.QuickSandBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class QuickSandEvent {
	
	@SubscribeEvent
	public static void quickSandEvent(BlockEvent.EntityPlaceEvent event) {
		LevelAccessor levelAccess = event.getLevel();
		BlockPos pos = event.getPos();
		BlockState state = levelAccess.getBlockState(pos);
		Block block = state.getBlock();
		
		if(!levelAccess.isClientSide()) {
			if(block instanceof QuickSandBlock) {
				if(levelAccess.getBlockState(pos.below()).getBlock() instanceof QuickSandBlock) {
					BlockPos posBelow = pos.below();
					BlockState stateNorth = levelAccess.getBlockState(posBelow.north());
					BlockState stateSouth = levelAccess.getBlockState(posBelow.south());
					BlockState stateEast = levelAccess.getBlockState(posBelow.east());
					BlockState stateWest = levelAccess.getBlockState(posBelow.west());
					BlockState stateNorthWest = levelAccess.getBlockState(posBelow.north().west());
					BlockState stateNorthEast = levelAccess.getBlockState(posBelow.north().east());
					BlockState stateSouthWest = levelAccess.getBlockState(posBelow.south().west());
					BlockState stateSouthEast = levelAccess.getBlockState(posBelow.south().east());
					
					if(stateNorth.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.north(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					} 
					else if(stateSouth.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.south(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					else if(stateEast.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.east(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					else if(stateWest.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.west(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					
					else if(stateNorthWest.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.north().west(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					else if(stateNorthEast.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.north().east(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					else if(stateSouthWest.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.south().west(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					else if(stateSouthEast.getBlock() == Blocks.AIR) {
						levelAccess.setBlock(posBelow.south().east(), BlockRegistry.QUICK_SAND.get().defaultBlockState(), 3);
						levelAccess.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}
					
					else {
						return;
					}
					
				}
			}
		}
		
    }

}


