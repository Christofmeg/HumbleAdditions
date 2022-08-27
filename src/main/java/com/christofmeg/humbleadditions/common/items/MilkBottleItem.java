package com.christofmeg.humbleadditions.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.setup.Config;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MilkBottleItem extends MilkBucketItem {

	public MilkBottleItem(Properties pProperties) {
		super(pProperties);

	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
		if (!pLevel.isClientSide) {
			pEntityLiving.curePotionEffects(pStack);
			
			if (Math.random() * 100 <= Config.MILK_BOTTLE_CURE_CHANCE.get()) {
				pEntityLiving.removeAllEffects();
			}
		}
		if (pEntityLiving instanceof ServerPlayer) {
			ServerPlayer serverplayer = (ServerPlayer)pEntityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
			serverplayer.awardStat(Stats.ITEM_USED.get(this));
		}
		if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild) {
			Player player = (Player) pEntityLiving;
			pStack.shrink(1);
			player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
			if (!pLevel.isClientSide) {
				
			}

		}

		return pStack;
	}	
	
	@Override
	public int getUseDuration(ItemStack pStack) {
	      return 12;
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(Component.literal("When Consumed:").withStyle(ChatFormatting.GRAY));
		pTooltipComponents.add(Component.literal(Config.MILK_BOTTLE_CURE_CHANCE.get() + "% chance to remove potion effects").withStyle(ChatFormatting.BLUE));
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}

}
