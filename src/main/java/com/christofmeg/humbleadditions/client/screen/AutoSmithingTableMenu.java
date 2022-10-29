package com.christofmeg.humbleadditions.client.screen;

import java.util.List;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AutoSmithingTableMenu extends SmithingMenu {

	private final Level level;
	@Nullable
	private final List<UpgradeRecipe> recipes;

	private final ContainerLevelAccess access;
	private final Block block;

	public AutoSmithingTableMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess access, Block block) {
		super(pContainerId, pPlayerInventory);
		this.level = pPlayerInventory.player.level;
		this.recipes = this.level.getRecipeManager().getAllRecipesFor(RecipeType.SMITHING);
		this.access = access;
		this.block = block;
	}

	@Override
	protected boolean isValidBlock(BlockState pState) {
		return pState.is(BlockRegistry.AUTO_SMITHING_TABLE_BLOCK.get());
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.access, player, block);
	}



}
