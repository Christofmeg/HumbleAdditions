package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.items.ModHorseArmorItem;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class HorseArmorEvent {

	@SubscribeEvent
	public static void horseHasArmorEquipped(final LivingKnockBackEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof Horse) {
			if(((Horse) entity).isWearingArmor()) {
				if(((Horse) entity).getArmor().getItem() instanceof ModHorseArmorItem) {
					event.setStrength((event.getStrength() / 10) * (10 - Config.HORSE_ARMOR_KNOCKBACK_RESISTANCE.get()));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void horseHasArmorEquipped2(final LivingDamageEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		if(!level.isClientSide) {
			if(entity instanceof Horse) {
				if(((Horse) entity).isWearingArmor()) {
					Item item = ((Horse) entity).getArmor().getItem();
					if(item instanceof ModHorseArmorItem) {
						float damage = event.getAmount();
						float armor = ((HorseArmorItem) item).getProtection();
						int toughness = Config.NETHERITE_HORSE_ARMOR_TOUGHNESS.get();
						float newDamage = damage * (1 - Math.min(20, Math.max(armor / 5, armor - damage / (2 + toughness  / 4))) / 25);
						event.setAmount(newDamage);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void horseHasArmorEquipped3(final LivingEquipmentChangeEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		Item newItem = event.getTo().getItem();
		Item oldItem = event.getFrom().getItem();
		if(!level.isClientSide) {
			if(entity instanceof Horse) {
				if(oldItem == ItemRegistry.ENDORIUM_HORSE_ARMOR.get() || oldItem == ItemRegistry.ENDORITE_HORSE_ARMOR.get()) {
					entity.setGlowingTag(false);
				}
				if(newItem == ItemRegistry.ENDORIUM_HORSE_ARMOR.get() || newItem == ItemRegistry.ENDORITE_HORSE_ARMOR.get()) {
					entity.setGlowingTag(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void horseHasArmorEquipped4(final LivingTickEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.getLevel();
		if(!level.isClientSide) {
			if(entity instanceof Horse) {
				ItemStack stack = ((Horse) entity).getArmor();
				Item item = stack.getItem();
				if(item == ItemRegistry.ENDORIUM_HORSE_ARMOR.get() || item == ItemRegistry.ENDORITE_HORSE_ARMOR.get()) {
					if(entity.getDeltaMovement().y < 0) {
						entity.setDeltaMovement(entity.getDeltaMovement().x, 0, entity.getDeltaMovement().z);
					}
				}
			}
		}
	}
	
}