package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class BedBlockEvent {
	
	@SubscribeEvent
	public static void bedIsTooFarAwayEvent(final PlayerInteractEvent.RightClickBlock event) {
		LevelAccessor levelAccess = event.getLevel();
		BlockPos pos = event.getPos();
		BlockState state = levelAccess.getBlockState(pos);
		Block blockHit = state.getBlock();
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		if(!level.isClientSide) {
			if(level.isNight()) {
				if(entity instanceof ServerPlayer) {
					if(blockHit instanceof BedBlock) {
						if(entity.isPassenger()) {
							entity.stopRiding();
						}
						Direction direction = state.getBedDirection(level, pos);
						if(state.getValue(BedBlock.PART) == BedPart.FOOT) {
							if(direction == Direction.NORTH) {
								entity.teleportTo(pos.getX(), pos.getY(), pos.getZ() - 1);
							}
							if(direction == Direction.SOUTH) {
								entity.teleportTo(pos.getX(), pos.getY(), pos.getZ() + 1);
							}
							if(direction == Direction.EAST) {
								entity.teleportTo(pos.getX() + 1, pos.getY(), pos.getZ());
							}
							if(direction == Direction.WEST) {
								entity.teleportTo(pos.getX() - 1, pos.getY(), pos.getZ());
							}
						} 
						else {
							entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
						}
					}
				}
			}
		}
	}

}