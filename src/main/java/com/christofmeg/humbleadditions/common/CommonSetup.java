package com.christofmeg.humbleadditions.common;

import com.christofmeg.humbleadditions.common.entities.RedHuskEntity;
import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {

	@SubscribeEvent
	public static void registerEntities(FMLCommonSetupEvent event) {
		SpawnPlacements.register(EntityRegistry.RED_HUSK.get(), SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RedHuskEntity::checkRedHuskSpawnRules);

	}

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityRegistry.RED_HUSK.get(), RedHuskEntity.createAttributes().build());
		event.put(EntityRegistry.SNOW_GOLEM.get(), SnowGolemEntity.createAttributes().build());

	}


}
