package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class AngryGoatEvent {
	
	@SubscribeEvent
	public static void goatRamEvent(LivingHurtEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		DamageSource damageSource = event.getSource();
		Entity entityThatDealtDamage = damageSource.getDirectEntity();
		if(!level.isClientSide) {
			if(entity instanceof Goat) {
				if(damageSource.isProjectile()) {
					if(entityThatDealtDamage instanceof Snowball || entityThatDealtDamage instanceof ThrownEgg) {
						Brain<Goat> goat = ((Goat) entity).getBrain();
						if(goat.isActive(Activity.IDLE)) {
							int RAM_COOLDOWN_TICKS = goat.getMemory(MemoryModuleType.RAM_COOLDOWN_TICKS).get();
							if(RAM_COOLDOWN_TICKS > 50) {
								RandomSource random = RandomSource.create();
								int randomInt = random.nextIntBetweenInclusive(20, RAM_COOLDOWN_TICKS / 2);
								goat.setMemory(MemoryModuleType.RAM_COOLDOWN_TICKS, RAM_COOLDOWN_TICKS - randomInt);
							}
						}
					}
				}
			}
		}
    }

}


