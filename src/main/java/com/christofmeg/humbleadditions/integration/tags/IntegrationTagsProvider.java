package com.christofmeg.humbleadditions.integration.tags;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.integration.IronChestIntegration;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationTagsProvider extends ItemTagsProvider {

	public IntegrationTagsProvider(final DataGenerator gen, final BlockTagsProvider blockTags,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, ModConstants.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Integration Item Tags";
	}

	@Override
	protected void addTags() {


	}

	public static class BlockTags extends BlockTagsProvider {

		public BlockTags(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
			super(pGenerator, ModConstants.MOD_ID, existingFileHelper);
		}

		@Override
		public String getName() {
			return ModConstants.MOD_NAME + " - Integration Block Tags";
		}

		@Override
		protected void addTags() {
			this.tag(IronChestIntegration.IronChest.IRON_CHEST_TAG).add(IronChestIntegration.IronChest.IRON_CHEST.get());
			this.tag(IronChestIntegration.IronChest.GOLD_CHEST_TAG).add(IronChestIntegration.IronChest.GOLD_CHEST.get());
			this.tag(IronChestIntegration.IronChest.COPPPER_CHEST_TAG).add(IronChestIntegration.IronChest.COPPPER_CHEST.get());
			this.tag(IronChestIntegration.IronChest.DIAMOND_CHEST_TAG).add(IronChestIntegration.IronChest.DIAMOND_CHEST.get());
			this.tag(IronChestIntegration.IronChest.DIRT_CHEST_TAG).add(IronChestIntegration.IronChest.DIRT_CHEST.get());
			this.tag(IronChestIntegration.IronChest.OBSIDIAN_CHEST_TAG).add(IronChestIntegration.IronChest.OBSIDIAN_CHEST.get());

			this.tag(IronChestIntegration.IronChestRestocked.IRON_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.IRON_CHEST.get());
			this.tag(IronChestIntegration.IronChestRestocked.GOLD_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.GOLD_CHEST.get());
			this.tag(IronChestIntegration.IronChestRestocked.COPPPER_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.COPPPER_CHEST.get());
			this.tag(IronChestIntegration.IronChestRestocked.DIAMOND_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.DIAMOND_CHEST.get());
			this.tag(IronChestIntegration.IronChestRestocked.DIRT_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.DIRT_CHEST.get());
			this.tag(IronChestIntegration.IronChestRestocked.OBSIDIAN_CHEST_TAG).add(IronChestIntegration.IronChestRestocked.OBSIDIAN_CHEST.get());


		}

	}

}
