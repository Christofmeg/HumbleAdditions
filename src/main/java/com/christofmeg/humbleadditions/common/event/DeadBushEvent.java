package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class DeadBushEvent {	
	
	@SubscribeEvent
	public static void useShearsEvent(final PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		Entity entity = event.getEntity();
		if(!level.isClientSide) {
			if(item instanceof ShearsItem) {
				int damage = stack.getEnchantmentLevel(Enchantments.UNBREAKING);
				if(block instanceof AzaleaBlock || block == Blocks.POTTED_AZALEA || block == Blocks.POTTED_FLOWERING_AZALEA) {
					if(block == Blocks.AZALEA || block == Blocks.POTTED_AZALEA) {
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.AZALEA_LEAVES)));
					}
					if(block == Blocks.FLOWERING_AZALEA || block == Blocks.POTTED_FLOWERING_AZALEA) {
						level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.FLOWERING_AZALEA_LEAVES)));
					}
					if(block == Blocks.POTTED_AZALEA || block == Blocks.POTTED_FLOWERING_AZALEA) {
						level.setBlock(pos, Blocks.POTTED_DEAD_BUSH.defaultBlockState(), 3);
						event.setCanceled(true);
					}
					if(block == Blocks.AZALEA || block == Blocks.FLOWERING_AZALEA) {
						level.setBlock(pos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
					}
					if(entity instanceof ServerPlayer) {
						if(!((ServerPlayer) entity).isCreative()) {
							if(Math.random() * 100 <= (100 / 1 + damage)) {
								stack.hurtAndBreak(1, (LivingEntity) event.getEntity(), (p_150686_) -> {
									p_150686_.broadcastBreakEvent(event.getHand());
								});
							}
						}
					}
				}	
			}
		}
	}




	
}


