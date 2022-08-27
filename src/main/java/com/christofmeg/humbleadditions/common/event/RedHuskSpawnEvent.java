package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.entities.RedHuskEntity;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class RedHuskSpawnEvent {
	
	@SubscribeEvent
	public static void redHuskSpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
		Entity entity = event.getEntity();
		LevelAccessor level = event.getLevel();
		if(!level.isClientSide()) {
			if(entity instanceof Husk && !(entity instanceof RedHuskEntity)) {
				if ((Math.random() * 100) <= 50) {
					((Husk) entity).convertTo(EntityRegistry.RED_HUSK.get(), true);
				}
			}
		}
    }

	
   

}


