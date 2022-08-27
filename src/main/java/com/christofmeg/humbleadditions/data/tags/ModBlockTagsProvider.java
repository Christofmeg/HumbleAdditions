package com.christofmeg.humbleadditions.data.tags;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.TagRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockTagsProvider extends BlockTagsProvider {
	
	public ModBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, ModConstants.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Block Tags";
	}
	
	@Override
	protected void addTags() {
		
		this.tag(TagRegistry.Blocks.STORAGE_BLOCKS_CHARCOAL).add(
			BlockRegistry.CHARCOAL_BLOCK.get()
			);
		
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
			BlockRegistry.CHARCOAL_BLOCK.get(), 
			BlockRegistry.SMOOTH_ICE.get()
			);
		
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
			BlockRegistry.SAND_LAYER_BLOCK.get(), 
			BlockRegistry.RED_SAND_LAYER_BLOCK.get(),
			BlockRegistry.GRAVEL_LAYER_BLOCK.get()
			);

		this.tag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.AZALEA_GROWS_ON).add(
				BlockRegistry.MOSS_LAYER_BLOCK.get(),
				BlockRegistry.SAND_LAYER_BLOCK.get(),
				BlockRegistry.RED_SAND_LAYER_BLOCK.get()
				);
		this.tag(BlockTags.MOSS_REPLACEABLE).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.SCULK_REPLACEABLE).add(
				BlockRegistry.MOSS_LAYER_BLOCK.get(),
				BlockRegistry.SAND_LAYER_BLOCK.get(),
				BlockRegistry.RED_SAND_LAYER_BLOCK.get(),
				BlockRegistry.GRAVEL_LAYER_BLOCK.get()
				);
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.DRIPSTONE_REPLACEABLE).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.BIG_DRIPLEAF_PLACEABLE).add(BlockRegistry.MOSS_LAYER_BLOCK.get());
		this.tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(
				BlockRegistry.MOSS_LAYER_BLOCK.get(),
				BlockRegistry.SAND_LAYER_BLOCK.get(),
				BlockRegistry.RED_SAND_LAYER_BLOCK.get(),
				BlockRegistry.GRAVEL_LAYER_BLOCK.get()
				);
		this.tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(
				BlockRegistry.SAND_LAYER_BLOCK.get(),
				BlockRegistry.GRAVEL_LAYER_BLOCK.get()
				);
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof GlassBlock))
		.forEach(block -> {
			this.tag(BlockTags.IMPERMEABLE).add(block);
			this.tag(Tags.Blocks.GLASS_SILICA).add(block);
			this.tag(Tags.Blocks.GLASS).add(block);
			this.tag(Tags.Blocks.GLASS_COLORLESS).add(block);
		});
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof StainedGlassBlock))
		.forEach(block -> {
			this.tag(BlockTags.IMPERMEABLE).add(block);
			this.tag(Tags.Blocks.GLASS_SILICA).add(block);
			this.tag(Tags.Blocks.STAINED_GLASS).add(block);
		});
		this.tag(Tags.Blocks.GLASS_BLACK).add(BlockRegistry.BLACK_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_BLUE).add(BlockRegistry.BLUE_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_BROWN).add(BlockRegistry.BROWN_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_CYAN).add(BlockRegistry.CYAN_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_GRAY).add(BlockRegistry.GRAY_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_GREEN).add(BlockRegistry.GREEN_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_LIGHT_BLUE).add(BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_LIGHT_GRAY).add(BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_LIME).add(BlockRegistry.LIME_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_MAGENTA).add(BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_ORANGE).add(BlockRegistry.ORANGE_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_PINK).add(BlockRegistry.PINK_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_PURPLE).add(BlockRegistry.PURPLE_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_RED).add(BlockRegistry.RED_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_WHITE).add(BlockRegistry.WHITE_STAINED_GLOWING_GLASS.get());
		this.tag(Tags.Blocks.GLASS_YELLOW).add(BlockRegistry.YELLOW_STAINED_GLOWING_GLASS.get());
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof IronBarsBlock))
		.forEach(block -> {
			this.tag(Tags.Blocks.GLASS_PANES).add(block);
			this.tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(block);
		});
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof StainedGlassPaneBlock))
		.forEach(block -> {
			this.tag(Tags.Blocks.GLASS_PANES).add(block);
			this.tag(Tags.Blocks.STAINED_GLASS_PANES).add(block);
		});
		this.tag(Tags.Blocks.GLASS_PANES_BLACK).add(BlockRegistry.BLACK_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_BLUE).add(BlockRegistry.BLUE_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_BROWN).add(BlockRegistry.BROWN_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_CYAN).add(BlockRegistry.CYAN_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_GRAY).add(BlockRegistry.GRAY_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_GREEN).add(BlockRegistry.GREEN_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_LIGHT_BLUE).add(BlockRegistry.LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_LIGHT_GRAY).add(BlockRegistry.LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_LIME).add(BlockRegistry.LIME_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_MAGENTA).add(BlockRegistry.MAGENTA_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_ORANGE).add(BlockRegistry.ORANGE_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_PINK).add(BlockRegistry.PINK_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_PURPLE).add(BlockRegistry.PURPLE_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_RED).add(BlockRegistry.RED_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_WHITE).add(BlockRegistry.WHITE_STAINED_GLOWING_GLASS_PANE.get());
		this.tag(Tags.Blocks.GLASS_PANES_YELLOW).add(BlockRegistry.YELLOW_STAINED_GLOWING_GLASS_PANE.get());
		
		
		
	}


}