package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.items.FloatingBlockItem;
import com.christofmeg.humbleadditions.common.items.FloatingItem;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class FloatingItemEvent {

	@SubscribeEvent
	public static void itemFloatEvent(final EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();
		if(!level.isClientSide) {
			if(entity instanceof ItemEntity) {
				ItemStack stack = ((ItemEntity) entity).getItem();
				Item item = stack.getItem();
				if(item instanceof FloatingItem || item instanceof FloatingBlockItem || item.toString().contains("endori")) {
					entity.setNoGravity(true);
					entity.setGlowingTag(true);
					if(entity.getOnPos().getY() < level.getMinBuildHeight()) {
						entity.moveTo(new Vec3(entity.getOnPos().getX(), level.getMinBuildHeight() + 1, entity.getOnPos().getZ()));
						entity.setDeltaMovement(entity.getDeltaMovement().x / 8, 0, entity.getDeltaMovement().z / 8);
						((ItemEntity) entity).setPickUpDelay(60);
					}
					else {
						entity.setDeltaMovement(entity.getDeltaMovement().x / 8, 0, entity.getDeltaMovement().z / 8);
					}
					entity.setInvulnerable(true);

				}
			}
		}
	}

}


