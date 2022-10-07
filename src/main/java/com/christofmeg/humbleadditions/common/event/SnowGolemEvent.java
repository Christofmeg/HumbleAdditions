package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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

	@SubscribeEvent
	public static void snowGolemHeadEvent(final PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		Level level = entity.getLevel();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		if(!level.isClientSide) {
			if(entity instanceof SnowGolem) {
				if(entity.getType() == EntityType.SNOW_GOLEM && !((SnowGolem)entity).hasPumpkin()) {
					if(item == Items.CARVED_PUMPKIN) {
						((SnowGolem)entity).setPumpkin(true);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
					}
				}
				if(entity.getType() == EntityRegistry.SNOW_GOLEM.get() && !((SnowGolemEntity)entity).hasCarvedPumpkin()) {
					if(item == Items.CARVED_PUMPKIN) {
						((SnowGolemEntity)entity).setCarvedPumpkin(true);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
					}
				}
				if(entity.getType() == EntityRegistry.SNOW_GOLEM.get() && !((SnowGolemEntity)entity).hasJackOPumpkin()) {
					if(item == Items.JACK_O_LANTERN) {
						((SnowGolemEntity)entity).setJackOPumpkin(true);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
					}
				}
				if(entity.getType() == EntityRegistry.SNOW_GOLEM.get() && !((SnowGolemEntity)entity).hasJackOSoulPumpkin()) {
					if(item == BlockRegistry.JACK_O_SOUL_LANTERN.get().asItem()) {
						((SnowGolemEntity)entity).setJackOSoulPumpkin(true);
						if(!event.getEntity().isCreative()) {
							stack.shrink(1);
						}
					}
				}
			}
		}
	}

}


