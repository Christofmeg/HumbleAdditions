package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class PlayerFallEvent {

	@SubscribeEvent
	public static void playerFallEvent(final LivingAttackEvent event) {
		LivingEntity entity = event.getEntity();
		Level level = entity.getLevel();
		if(!level.isClientSide) {
			if(entity instanceof Player) {
                if (!entity.isOnGround() && entity.getDeltaMovement().y < 0 && !entity.hasEffect(MobEffects.SLOW_FALLING)) {
        			if(event.getAmount() <= 1) {
        				event.setCanceled(true);
        			}
                }
	        }
		}
    }

}


