package com.christofmeg.humbleadditions.registry;

import org.jetbrains.annotations.NotNull;

import com.christofmeg.humbleadditions.common.items.MilkBottleItem;
import com.christofmeg.humbleadditions.common.items.ModHorseArmorItem;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MODID);
	
	public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle", () -> new MilkBottleItem(props().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	
	public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", 
			() -> new ModHorseArmorItem(13, 
					
					//Config.NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE.get(), 
					9, 
					
					"netherite", (props()).stacksTo(1).fireResistant()));
	
	public static final RegistryObject<ForgeSpawnEggItem> RED_HUSK_SPAWN_EGG = ITEMS.register("red_husk_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.RED_HUSK, 7958625, 11098145, props()));
    
	public static final RegistryObject<SolidBucketItem> QUICK_SAND_BUCKET = ITEMS.register("quick_sand_bucket", () -> new SolidBucketItem(BlockRegistry.QUICK_SAND.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, props().stacksTo(1)));
	
	private static Item.Properties props() {
        return new Item.Properties().tab(ItemRegistry.TAB);
    }
	
	public static final CreativeModeTab TAB = new CreativeModeTab(ModConstants.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return ItemRegistry.MILK_BOTTLE.get().getDefaultInstance();
        }
    };
	
}
