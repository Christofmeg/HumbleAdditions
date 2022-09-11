package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class CreativeTameEvent {
	
	@SubscribeEvent
	public static void creativeInstaTameEvent(final PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		Player player = event.getEntity();
		Level level = entity.getLevel();
		ItemStack stack = player.getUseItem();
		Item item = stack.getItem();
		if(!level.isClientSide) {
			if(player.isCreative()) {
				if(entity instanceof AbstractHorse) {
					if(!((AbstractHorse) entity).isTamed()) {
						((AbstractHorse) entity).tameWithName(player);
					}
				}
				
				//TODO insta tame Cats, Parrots and Wolves
				
				if(entity instanceof Cat) {
					if(item == Items.COD || item == Items.SALMON) {

					}
				}
				if(entity instanceof Parrot) {
					if(item == Items.BEETROOT_SEEDS || item == Items.MELON_SEEDS || item == Items.PUMPKIN_SEEDS || item == Items.WHEAT_SEEDS) {

					}
				}
				if(entity instanceof Wolf) {
					if(item == Items.BONE) {

					}
				}
			}
		}
    }

}


