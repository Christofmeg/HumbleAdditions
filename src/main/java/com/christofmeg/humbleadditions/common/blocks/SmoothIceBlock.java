package com.christofmeg.humbleadditions.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SmoothIceBlock extends HalfTransparentBlock {

	public SmoothIceBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if(!level.isClientSide){
			if(entity instanceof Player && !((Player) entity).isCreative()) {
					((Player) entity).getFoodData().setFoodLevel((int) (((Player) entity).getFoodData().getFoodLevel() + 0.01));
					((Player) entity).getFoodData().setSaturation(((Player) entity).getFoodData().getSaturationLevel() + 0.01F);
			}
		}
		
		super.stepOn(level, pos, state, entity);
	}
	
	@Override
	public float getFriction(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity)
    {
        if(entity instanceof LivingEntity) {
        	return (float) (1 / 0.91);
        }
        else if(entity instanceof ItemEntity) {
        	return (float) (1 / 0.98);
        }
        else if(entity instanceof FishingHook) {
        	return (float) (1 / 0.92);
        }
		
		return this.getFriction();
    }


}
