package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.common.entities.RedHuskEntity;
import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModConstants.MODID);
	
	public static final RegistryObject<EntityType<RedHuskEntity>> RED_HUSK = ENTITY_TYPES.register("red_husk", () -> EntityType.Builder.of(RedHuskEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(ModConstants.MODID, "red_husk").toString()));
	public static final RegistryObject<EntityType<SnowGolemEntity>> SNOW_GOLEM = ENTITY_TYPES.register("snow_golem", () -> EntityType.Builder.of(SnowGolemEntity::new, MobCategory.MISC).sized(0.7F, 1.9F).clientTrackingRange(8).immuneTo(Blocks.POWDER_SNOW).build(new ResourceLocation(ModConstants.MODID, "red_husk").toString()));
    
    
       
}
