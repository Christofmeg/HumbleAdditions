package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
				if(((Horse) entity).getArmor().getItem() == ItemRegistry.NETHERITE_HORSE_ARMOR.get()) {
					event.setStrength((event.getStrength() / 10) * (10 - Config.NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE.get()));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void horseHasArmorEquipped2(final LivingDamageEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof Horse) {
			if(((Horse) entity).isWearingArmor()) {
				Item item = ((Horse) entity).getArmor().getItem();
				if(item== ItemRegistry.NETHERITE_HORSE_ARMOR.get()) {
					if(item instanceof HorseArmorItem) {
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
}