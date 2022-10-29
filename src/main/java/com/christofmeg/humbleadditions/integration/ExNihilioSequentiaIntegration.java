package com.christofmeg.humbleadditions.integration;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class ExNihilioSequentiaIntegration {

	/*
	 * I went for this "hacky" solution, because I was unable to compile this mod at the time of coding
	 */

	private static String ID = "exnihilosequentia";
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);

	@ObjectHolder(registryName = "exnihilosequentia:dust", value = "exnihilosequentia:dust")
	public static final RegistryObject<Item> DUST = ITEMS.register("dust", () -> new Item(new Item.Properties()));


}
