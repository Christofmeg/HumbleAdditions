package com.christofmeg.humbleadditions.common.event;

import java.util.List;

import com.christofmeg.humbleadditions.common.items.ModHorseArmorItem;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class TooltipEvent {
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void tooltipEvent(final ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		List<Component> pTooltipComponents = event.getToolTip();
		if(item instanceof HorseArmorItem && !(item instanceof ModHorseArmorItem)) {
			pTooltipComponents.add(Component.literal("When on Horse:").withStyle(ChatFormatting.GRAY));
			pTooltipComponents.add(Component.literal("+" + ((HorseArmorItem) item).getProtection() + " Armor").withStyle(ChatFormatting.BLUE));
		}
		if(item instanceof PickaxeItem) {
			if(Screen.hasShiftDown()) {
				pTooltipComponents.add(Component.literal("Harvest level: " + ((PickaxeItem)item).getTier() + " (" + ((PickaxeItem)item).getTier().getLevel() + ")").withStyle(ChatFormatting.GRAY));
			}
		}
	}

}