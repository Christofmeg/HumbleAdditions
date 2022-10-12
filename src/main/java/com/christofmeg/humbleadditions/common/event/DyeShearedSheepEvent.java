package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class DyeShearedSheepEvent {

	@SubscribeEvent
	public static void dyeShearedSheepEvent(final PlayerInteractEvent.EntityInteract event) {
		Entity sheep = event.getTarget();
		Player player = event.getEntity();
		Level level = sheep.getLevel();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		if(!level.isClientSide) {
			if(sheep instanceof Sheep) {
				if(item instanceof DyeItem) {
					DyeColor dyeColor = ((DyeItem) item).getDyeColor();
					if (sheep.isAlive() && ((Sheep) sheep).isSheared() && ((Sheep) sheep).getColor() != dyeColor) {
						((Sheep) sheep).setColor(dyeColor);
						sheep.level.playSound(player, sheep, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
						if(!player.isCreative()) {
							stack.shrink(1);
						}
					}
				}
			}
		}
	}


}