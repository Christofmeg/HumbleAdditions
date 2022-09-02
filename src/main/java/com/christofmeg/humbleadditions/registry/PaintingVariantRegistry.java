package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PaintingVariantRegistry {

	public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, ModConstants.MOD_ID);
	
	public static final RegistryObject<PaintingVariant> ACACIA_DOOR = PAINTING_VARIANTS.register("acacia_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> OAK_DOOR = PAINTING_VARIANTS.register("oak_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> BIRCH_DOOR = PAINTING_VARIANTS.register("birch_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> JUNGLE_DOOR = PAINTING_VARIANTS.register("jungle_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> SPRUCE_DOOR = PAINTING_VARIANTS.register("spruce_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> DARK_OAK_DOOR = PAINTING_VARIANTS.register("dark_oak_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> WARPED_DOOR = PAINTING_VARIANTS.register("warped_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> CRIMSON_DOOR = PAINTING_VARIANTS.register("crimson_door", () -> new PaintingVariant(16, 32));
	public static final RegistryObject<PaintingVariant> MANGROVE_DOOR = PAINTING_VARIANTS.register("mangrove_door", () -> new PaintingVariant(16, 32));
}
