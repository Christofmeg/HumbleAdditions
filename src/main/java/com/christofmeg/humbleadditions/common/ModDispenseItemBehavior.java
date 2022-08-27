package com.christofmeg.humbleadditions.common;

import com.christofmeg.humbleadditions.registry.ItemRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class ModDispenseItemBehavior {
	
	public static void init() {
		
		DefaultDispenseItemBehavior defaultdispenseitembehavior1 = new OptionalDispenseItemBehavior() {
	        @SuppressWarnings("resource")
			protected ItemStack execute(BlockSource p_123535_, ItemStack p_123536_) {
	            BlockPos blockpos = p_123535_.getPos().relative(p_123535_.getBlockState().getValue(DispenserBlock.FACING));

	            for(AbstractHorse abstracthorse : p_123535_.getLevel().getEntitiesOfClass(AbstractHorse.class, new AABB(blockpos), (p_123533_) -> {
	               return p_123533_.isAlive() && p_123533_.canWearArmor();
	            })) {
	               if (abstracthorse.isArmor(p_123536_) && !abstracthorse.isWearingArmor() && abstracthorse.isTamed()) {
	                  abstracthorse.getSlot(401).set(p_123536_.split(1));
	                  this.setSuccess(true);
	                  return p_123536_;
	               }
	            }

	            return super.execute(p_123535_, p_123536_);
	         }
	      };
		
		DispenserBlock.registerBehavior(ItemRegistry.NETHERITE_HORSE_ARMOR.get(), defaultdispenseitembehavior1);
	}

}
