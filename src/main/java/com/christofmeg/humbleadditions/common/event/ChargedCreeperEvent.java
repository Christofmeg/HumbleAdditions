package com.christofmeg.humbleadditions.common.event;


import java.util.Random;
import java.util.UUID;

import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class ChargedCreeperEvent {

	@SubscribeEvent
	public static void chargedCreeperSpawnEvent(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();
		if(!level.isClientSide){
			if(entity instanceof Creeper && !event.loadedFromDisk()) {
				if(!((Creeper) entity).isPowered()){
					int i = Config.CHARGED_CREEPER_SPAWN_CHANCE.get();
					if ((Math.random() * 100) <= i) {
						chargeEntity(entity);
					}
				}
			}
		}
	}

	public static void chargeEntity(Entity entity) {
		Level world = entity.getCommandSenderWorld();
		Vec3 vec3 = entity.position();
		LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
		lightning.setPos(vec3.x(), vec3.y(), vec3.z());
		lightning.setUUID(new UUID(0, new Random().nextInt()*1000000));
		entity.thunderHit((ServerLevel)world, lightning);
		entity.clearFire();
	}

	@SubscribeEvent
	public static void golemKilledByChargedCreepetEvent(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		BlockPos pos = entity.getOnPos();
		Level level = entity.getLevel();
		Entity sourceEntity = event.getSource().getEntity();
		if(!level.isClientSide){
			if(sourceEntity instanceof Creeper) {
				if(((Creeper) sourceEntity).isPowered()) {
					if(entity instanceof IronGolem) {
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARVED_PUMPKIN)));
					}
					if(entity instanceof SnowGolem) {
						if(entity.getType() == EntityType.SNOW_GOLEM) {
							level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARVED_PUMPKIN)));
						}
						if(entity.getType() == EntityRegistry.SNOW_GOLEM.get()) {
							if(((SnowGolemEntity)entity).hasCarvedPumpkin()) {
								level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.CARVED_PUMPKIN)));
							}
							if(((SnowGolemEntity)entity).hasJackOPumpkin()) {
								level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.JACK_O_LANTERN)));
							}
							if(((SnowGolemEntity)entity).hasJackOSoulPumpkin()) {
								level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockRegistry.JACK_O_SOUL_LANTERN.get().asItem())));
							}
						}
					}
				}
			}
		}
	}

}


