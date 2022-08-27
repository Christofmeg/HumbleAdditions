package com.christofmeg.humbleadditions.common.event;



import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class FloatingItemEvent {
	
//TODO	@SubscribeEvent
	public static void itemFloatEvent(final EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		Level level = event.getLevel();
		if(!level.isClientSide) {
			if(entity instanceof ItemEntity) {
				ItemStack stack = ((ItemEntity) entity).getItem();
				Item item = stack.getItem();
				if(item == ItemRegistry.NETHERITE_HORSE_ARMOR.get()) {
					entity.setNoGravity(true);
					((ItemEntity) entity).setPickUpDelay(60);
					if(level.dimension().equals(LevelStem.OVERWORLD)) {
						if(entity.getOnPos().getY() < -64) {
							entity.setGlowingTag(true);
							entity.moveTo(new Vec3(entity.getOnPos().getX(), -63, entity.getOnPos().getZ()));
							
						}
					}
					if(level.dimension().equals(LevelStem.NETHER)) {
						if(entity.getOnPos().getY() < 0) {
							entity.setGlowingTag(true);
							entity.moveTo(new Vec3(entity.getOnPos().getX(), 1, entity.getOnPos().getZ()));
						}
					}
					if(level.dimension().equals(LevelStem.END)) {
						if(entity.getOnPos().getY() < 0) {
							entity.setGlowingTag(true);
							level.getBlockState(new BlockPos(entity.getOnPos().getX(), entity.getOnPos().getY(), entity.getOnPos().getZ()).above());
							entity.moveTo(new Vec3(entity.getOnPos().getX(), 1, entity.getOnPos().getZ()));
						}
					} else {
						entity.setDeltaMovement(entity.getDeltaMovement().x / 8, 0, entity.getDeltaMovement().z / 8);
					}
				}
			}
		}
    }

}


