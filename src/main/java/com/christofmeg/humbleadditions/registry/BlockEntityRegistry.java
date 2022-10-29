package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.common.blocks.entities.AutoSmithingTableBlockEntity;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModConstants.MODID);

	//	public static final RegistryObject<BlockEntityType<...BlockEntity>> ... = BLOCK_ENTITY_TYPES.register("...", () -> BlockEntityType.Builder.of(...BlockEntity::new, BlockRegistry.....get()).build(null));

	public static final RegistryObject<BlockEntityType<AutoSmithingTableBlockEntity>> AUTO_SMITHING_TABLE =
			BLOCK_ENTITY_TYPES.register("auto_smithing_table", () -> BlockEntityType.Builder.of(AutoSmithingTableBlockEntity::new, BlockRegistry.AUTO_SMITHING_TABLE_BLOCK.get()).build(null));
}
