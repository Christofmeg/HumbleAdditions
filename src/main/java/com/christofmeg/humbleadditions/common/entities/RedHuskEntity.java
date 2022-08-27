package com.christofmeg.humbleadditions.common.entities;

import com.christofmeg.humbleadditions.registry.ItemRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.extensions.IForgeEntity;

public class RedHuskEntity extends Husk implements IForgeEntity {
	
	public RedHuskEntity(EntityType<? extends Husk> p_32889_, Level p_32890_) {
	      super(p_32889_, p_32890_);
	   }
	
	public static boolean checkRedHuskSpawnRules(EntityType<? extends RedHuskEntity> entityType, ServerLevelAccessor levelAccess, MobSpawnType mobSpawnType, BlockPos pos, RandomSource randomSource) {
	      return checkMonsterSpawnRules(entityType, levelAccess, mobSpawnType, pos, randomSource) && (mobSpawnType == MobSpawnType.SPAWNER || levelAccess.canSeeSky(pos));
	}
	
	@Override
	public ItemStack getPickResult() {
		return new ItemStack(ItemRegistry.RED_HUSK_SPAWN_EGG.get());
	}

	
}
