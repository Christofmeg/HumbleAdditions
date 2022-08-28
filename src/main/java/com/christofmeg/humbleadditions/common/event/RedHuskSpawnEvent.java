package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.entities.RedHuskEntity;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class RedHuskSpawnEvent {
	
	@SubscribeEvent
	public static void redHuskSpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		if(!level.isClientSide()) {
			if(entity instanceof Zombie && !(entity instanceof RedHuskEntity)) {
				BlockPos pos = entity.getOnPos();
				Holder<Biome> biome = level.getBiome(pos);
				if(event.getSpawnReason().equals(MobSpawnType.SPAWN_EGG) || event.getSpawnReason().equals(MobSpawnType.MOB_SUMMONED) || entity.isRemoved()) {
					return;
				}
				if(biome.toString().contains(Biomes.BADLANDS.toString()) || biome.toString().contains(Biomes.ERODED_BADLANDS.toString())  || biome.toString().contains(Biomes.WOODED_BADLANDS.toString()) ) {
					if ((Math.random() * 100) <= 80) {
						RedHuskEntity redHusk1 = new RedHuskEntity(EntityRegistry.RED_HUSK.get(), level);
						RedHuskEntity redHusk2 = new RedHuskEntity(EntityRegistry.RED_HUSK.get(), level);
						RedHuskEntity redHusk3 = new RedHuskEntity(EntityRegistry.RED_HUSK.get(), level);
						RedHuskEntity redHusk4 = new RedHuskEntity(EntityRegistry.RED_HUSK.get(), level);
						level.addFreshEntity(redHusk1);
						level.addFreshEntity(redHusk2);
						level.addFreshEntity(redHusk3);
						level.addFreshEntity(redHusk4);
						redHusk1.setPos(entity.position());
						redHusk2.setPos(entity.position());
						redHusk3.setPos(entity.position());
						redHusk4.setPos(entity.position());
						event.setCanceled(true);
						return;
					}
				}
			}
		}
    }
}


