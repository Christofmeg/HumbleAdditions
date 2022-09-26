package com.christofmeg.humbleadditions.registry;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VanillaRegistry {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	
	public static final RegistryObject<BoatItem> OAK_CHEST_BOAT = ITEMS.register("oak_chest_boat", () -> new BoatItem(true, Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.OAK_BOAT)));
	public static final RegistryObject<BoatItem> SPRUCE_CHEST_BOAT = ITEMS.register("spruce_chest_boat", () -> new BoatItem(true, Boat.Type.SPRUCE, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.SPRUCE_BOAT)));
	public static final RegistryObject<BoatItem> BIRCH_CHEST_BOAT = ITEMS.register("birch_chest_boat", () -> new BoatItem(true, Boat.Type.BIRCH, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.BIRCH_BOAT)));
	public static final RegistryObject<BoatItem> JUNGLE_CHEST_BOAT = ITEMS.register("jungle_chest_boat", () -> new BoatItem(true, Boat.Type.JUNGLE, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.JUNGLE_BOAT)));
	public static final RegistryObject<BoatItem> ACACIA_CHEST_BOAT = ITEMS.register("acacia_chest_boat", () -> new BoatItem(true, Boat.Type.ACACIA, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.ACACIA_BOAT)));
	public static final RegistryObject<BoatItem> DARK_OAK_CHEST_BOAT = ITEMS.register("dark_oak_chest_boat", () -> new BoatItem(true, Boat.Type.DARK_OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.DARK_OAK_BOAT)));
	public static final RegistryObject<BoatItem> MANGROVE_CHEST_BOAT = ITEMS.register("mangrove_chest_boat", () -> new BoatItem(true, Boat.Type.MANGROVE, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.MANGROVE_BOAT)));
	public static final RegistryObject<MinecartItem> CHEST_MINECART = ITEMS.register("chest_minecart", () -> new MinecartItem(AbstractMinecart.Type.CHEST, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.MINECART)));
	public static final RegistryObject<MinecartItem> FURNACE_MINECART = ITEMS.register("furnace_minecart", () -> new MinecartItem(AbstractMinecart.Type.FURNACE, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.MINECART)));
	public static final RegistryObject<MinecartItem> TNT_MINECART = ITEMS.register("tnt_minecart", () -> new MinecartItem(AbstractMinecart.Type.TNT, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.MINECART)));
	public static final RegistryObject<MinecartItem> HOPPER_MINECART = ITEMS.register("hopper_minecart", () -> new MinecartItem(AbstractMinecart.Type.HOPPER, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION).craftRemainder(Items.MINECART)));
	
}
