package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class SnowGolemEvent {
	
	@SubscribeEvent
	public static void snowGolemSurvivesEvent(final LivingAttackEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		DamageSource damageSource = event.getSource();
		if(!level.isClientSide) {
			if(entity instanceof SnowGolem) {
				if(entity.isInPowderSnow) {
					if(damageSource.isFire() || damageSource == DamageSource.DROWN) {
						event.setCanceled(true);
					}
				}
			}
		}
    }

}


