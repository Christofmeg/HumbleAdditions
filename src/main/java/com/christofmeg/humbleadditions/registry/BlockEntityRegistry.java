package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModConstants.MODID);
	
//	public static final RegistryObject<BlockEntityType<...BlockEntity>> ... = BLOCK_ENTITY_TYPES.register("...", () -> BlockEntityType.Builder.of(...BlockEntity::new, BlockRegistry.....get()).build(null));
       
}
