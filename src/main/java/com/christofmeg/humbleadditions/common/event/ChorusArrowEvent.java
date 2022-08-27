package com.christofmeg.humbleadditions.common.event;


import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class ChorusArrowEvent {
	
	@SubscribeEvent
	public static void teleportEntityHitEvent(LivingHurtEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		Vec3 vec3 = entity.position();
		DamageSource damageSource = event.getSource();
		Entity entityThatDealtDamage = damageSource.getDirectEntity();
		Entity attacker = damageSource.getEntity();
		if(!level.isClientSide) {
			if(entity instanceof LivingEntity) {
				if(entity instanceof Player && ((Player) entity).isCreative()) {
					return;
				}
				else if(damageSource.isProjectile() && entityThatDealtDamage instanceof Arrow) {
					double distance = entityThatDealtDamage.distanceTo(attacker);
					double d0 = vec3.x();
			        double d1 = vec3.y();
			        double d2 = vec3.z();

			        for(int i = 0; i < 16; ++i) {
			           double d3 = vec3.x() + (level.getRandom().nextDouble() - 0.5D) * 16.0D * distance;
			           double d4 = Mth.clamp(vec3.y() + (double)(level.getRandom().nextInt(16) - 8), (double)level.getMinBuildHeight(), (double)(level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
			           double d5 = vec3.z() + (level.getRandom().nextDouble() - 0.5D) * 16.0D * distance;
			           if (entity.isPassenger()) {
			        	   entity.stopRiding();
			           }
			              level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));
			           net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit teleportEvent = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport((LivingEntity) entity, d3, d4, d5);
			           if (event.isCanceled()) return;
			           if (((LivingEntity) entity).randomTeleport(teleportEvent.getTargetX(), teleportEvent.getTargetY(), teleportEvent.getTargetZ(), true)) {
			              SoundEvent soundevent = entity instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
			              level.playSound((Player)null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
			              entity.playSound(soundevent, 1.0F, 1.0F);
			              break;
			           }
			        }
			        
				}
			}
		}
    }

}


