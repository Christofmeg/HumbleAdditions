package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class FuelBurnTimeEvent {

	@SubscribeEvent
    public static void fuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {

		burnTime(event, BlockRegistry.BLAZE_ROD_BLOCK.get(), 24000);
		burnTime(event, BlockRegistry.CHARCOAL_BLOCK.get(), 16000);
		burnTime(event, ItemRegistry.WOODEN_SHEARS.get(), 400);

	}

	private static void burnTime(FurnaceFuelBurnTimeEvent event, Block block, int burnTime) {
		if(event.getItemStack().getItem() == block.asItem()) {
			event.setBurnTime(burnTime);
		}
	}
	
	private static void burnTime(FurnaceFuelBurnTimeEvent event, Item item, int burnTime) {
		if(event.getItemStack().getItem() == item) {
			event.setBurnTime(burnTime);
		}
	}
	
}


