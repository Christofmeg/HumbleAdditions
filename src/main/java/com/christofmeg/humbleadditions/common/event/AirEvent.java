package com.christofmeg.humbleadditions.common.event;

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
public class AirEvent {
	
	@SubscribeEvent
	public static void goatRamEvent(BlockEvent event) {
		LevelAccessor level = event.getLevel();
		BlockState state = event.getState();
		Block block = state.getBlock();
		BlockPos pos = event.getPos();
		if(block == BlockRegistry.AIR.get()) {
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 0);
			ModConstants.log.info("placed AIR");
		}
	}

}


