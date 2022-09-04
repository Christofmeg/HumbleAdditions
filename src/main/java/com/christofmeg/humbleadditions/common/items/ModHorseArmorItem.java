package com.christofmeg.humbleadditions.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ModHorseArmorItem extends HorseArmorItem{
	
	private final int protection;
	
	public ModHorseArmorItem(int pProtection, int knockBackResistance, String string, Properties pProperties) {
		super(pProtection, new ResourceLocation(ModConstants.MOD_ID, "textures/entities/horse/armor/horse_armor_" + string + ".png"), pProperties);
		protection = pProtection;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(Component.literal("When on Horse:").withStyle(ChatFormatting.GRAY));
		pTooltipComponents.add(Component.literal("+" + Config.NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE.get() + " Knockback Resistance").withStyle(ChatFormatting.BLUE));
		pTooltipComponents.add(Component.literal("+" + this.getProtection() + " Protection").withStyle(ChatFormatting.BLUE));
		super.appendHoverText(stack, pLevel, pTooltipComponents, pIsAdvanced);
	}
	
	@Override
	public int getProtection() {
		return this.protection;
	}
	
	

}
