package com.christofmeg.humbleadditions.registry;

import java.util.ArrayList;
import java.util.List;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionRegistry {

	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ModConstants.MODID);
	
	public static final RegistryObject<Potion> CHORUS = POTIONS.register("chorus", () -> new Potion(ModConstants.MODID + "_chorus", new MobEffectInstance(PotionEffectRegistry.CHORUS.get(), 3600)));
	
	public static void setup() {
		final ItemStack waterBottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
		
		makePotion(waterBottle.copy(), PotionRegistry.CHORUS.get(), Items.CHORUS_FRUIT);
	}

	private static void makePotion(ItemStack inputPot, Potion outputPot, Item itemCatalyst) {
	    BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(inputPot), 
	    	Ingredient.of(itemCatalyst), PotionUtils.setPotion(new ItemStack(Items.POTION), outputPot)));
	}
	
//	humbleadditions:textures/mob_effect/chorus.png
	
	
	public static class PotionEffectRegistry {
		public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModConstants.MODID);
		public static final List<ModMobEffect> EFFECTS = new ArrayList<ModMobEffect>();
		public static final RegistryObject<MobEffect> CHORUS = MOB_EFFECTS.register("chorus", () -> new ChorusEffect(MobEffectCategory.NEUTRAL, 0x7E5D7D));
	}
	
	
	
	
	public static class ModMobEffect extends MobEffect {
		
		public ModMobEffect(MobEffectCategory typeIn, int liquidColorIn) {
			super(typeIn, liquidColorIn);
			PotionEffectRegistry.EFFECTS.add(this);
		}

		public void tick(LivingTickEvent event) {}
		public void onPotionAdded(MobEffectEvent.Added event) {}
		public void isPotionApplicable(MobEffectEvent.Applicable event) {}
		public void onPotionRemove(MobEffectEvent.Remove event) {}
		public void onPotionExpiry(MobEffectEvent.Expired event) {}
		public void useItem(LivingEntityUseItemEvent.Finish event) {}
		
	}
	
	@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
	public static class ChorusEffect extends ModMobEffect {

		public ChorusEffect(MobEffectCategory typeIn, int liquidColorIn) {
			super(typeIn, liquidColorIn);
		}
	}
	
}
