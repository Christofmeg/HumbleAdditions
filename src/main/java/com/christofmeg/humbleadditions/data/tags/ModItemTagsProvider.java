package com.christofmeg.humbleadditions.data.tags;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
	
	public ModItemTagsProvider(final DataGenerator gen, final BlockTagsProvider blockTags,
            @Nullable ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, ModConstants.MOD_ID, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Item Tags";
	}
	
	@Override
	protected void addTags() {
		
		this.copy(Tags.Blocks.GLASS, Tags.Items.GLASS);
		this.copy(Tags.Blocks.STAINED_GLASS, Tags.Items.STAINED_GLASS);
		this.copy(Tags.Blocks.GLASS_COLORLESS, Tags.Items.GLASS_COLORLESS);
		this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
		this.copy(Tags.Blocks.STAINED_GLASS_PANES, Tags.Items.STAINED_GLASS_PANES);
		this.copy(Tags.Blocks.GLASS_SILICA, Tags.Items.GLASS_SILICA);
		
		this.copy(Tags.Blocks.GLASS_BLACK, Tags.Items.GLASS_BLACK);
		this.copy(Tags.Blocks.GLASS_BLUE, Tags.Items.GLASS_BLUE);
		this.copy(Tags.Blocks.GLASS_BROWN, Tags.Items.GLASS_BROWN);
		this.copy(Tags.Blocks.GLASS_CYAN, Tags.Items.GLASS_CYAN);
		this.copy(Tags.Blocks.GLASS_GRAY, Tags.Items.GLASS_GRAY);
		this.copy(Tags.Blocks.GLASS_GREEN, Tags.Items.GLASS_GREEN);
		this.copy(Tags.Blocks.GLASS_LIGHT_BLUE, Tags.Items.GLASS_LIGHT_BLUE);
		this.copy(Tags.Blocks.GLASS_LIGHT_GRAY, Tags.Items.GLASS_LIGHT_GRAY);
		this.copy(Tags.Blocks.GLASS_LIME, Tags.Items.GLASS_LIME);
		this.copy(Tags.Blocks.GLASS_MAGENTA, Tags.Items.GLASS_MAGENTA);
		this.copy(Tags.Blocks.GLASS_ORANGE, Tags.Items.GLASS_ORANGE);
		this.copy(Tags.Blocks.GLASS_PINK, Tags.Items.GLASS_PINK);
		this.copy(Tags.Blocks.GLASS_PURPLE, Tags.Items.GLASS_PURPLE);
		this.copy(Tags.Blocks.GLASS_RED, Tags.Items.GLASS_RED);
		this.copy(Tags.Blocks.GLASS_WHITE, Tags.Items.GLASS_WHITE);
		this.copy(Tags.Blocks.GLASS_YELLOW, Tags.Items.GLASS_YELLOW);
		
		
		
	}


}