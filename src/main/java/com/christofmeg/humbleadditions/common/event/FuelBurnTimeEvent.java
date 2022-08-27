package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class FuelBurnTimeEvent {

	@SubscribeEvent
    public static void fuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {

        burnTime(event, BlockRegistry.CHARCOAL_BLOCK, 16000);

	}

	private static void burnTime(FurnaceFuelBurnTimeEvent event, RegistryObject<Block> block, int burnTime) {
		if(event.getItemStack().getItem() == block.get().asItem()) {
			event.setBurnTime(burnTime);
		}
	}
	
}


