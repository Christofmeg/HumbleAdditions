package com.christofmeg.humbleadditions.data.tags;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.registry.TagRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
		
		this.copy(TagRegistry.Blocks.ORES_ENDORIUM, TagRegistry.Items.ORES_ENDORIUM);
		
		this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_CHARCOAL).add(BlockRegistry.CHARCOAL_BLOCK.get().asItem());
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_ROSE_GOLD).add(BlockRegistry.ROSE_GOLD_BLOCK.get().asItem());
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_RAW_ENDORIUM).add(BlockRegistry.RAW_ENDORIUM_BLOCK.get().asItem());
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_ENDORIUM).add(BlockRegistry.ENDORIUM_BLOCK.get().asItem());
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_ENDORITE).add(BlockRegistry.ENDORITE_BLOCK.get().asItem());
		this.tag(TagRegistry.Items.STORAGE_BLOCKS_RAW_ROSE_GOLD).add(BlockRegistry.RAW_ROSE_GOLD_BLOCK.get().asItem());
		
		this.tag(TagRegistry.Items.RAW_ENDORIUM).add(ItemRegistry.RAW_ENDORIUM.get());
		this.tag(TagRegistry.Items.ENDORIUM_INGOT).add(ItemRegistry.ENDORIUM_INGOT.get());
		this.tag(TagRegistry.Items.ENDORITE_INGOT).add(ItemRegistry.ENDORITE_INGOT.get());
		this.tag(TagRegistry.Items.ROSE_GOLD_INGOT).add(ItemRegistry.ROSE_GOLD_INGOT.get());
		this.tag(TagRegistry.Items.RAW_ROSE_GOLD).add(ItemRegistry.RAW_ROSE_GOLD.get());
		
		this.tag(Tags.Items.RAW_MATERIALS).addTag(TagRegistry.Items.RAW_ENDORIUM);
		this.tag(Tags.Items.RAW_MATERIALS).addTag(TagRegistry.Items.RAW_ROSE_GOLD);
		this.tag(Tags.Items.INGOTS).addTag(TagRegistry.Items.ENDORIUM_INGOT);
		this.tag(Tags.Items.INGOTS).addTag(TagRegistry.Items.ENDORITE_INGOT);
		this.tag(Tags.Items.INGOTS).addTag(TagRegistry.Items.ROSE_GOLD_INGOT);
		
		this.tag(Tags.Items.TOOLS_AXES).add(ItemRegistry.ENDORIUM_AXE.get());
		this.tag(Tags.Items.TOOLS_HOES).add(ItemRegistry.ENDORIUM_HOE.get());
		this.tag(Tags.Items.TOOLS_PICKAXES).add(ItemRegistry.ENDORIUM_PICKAXE.get());
		this.tag(Tags.Items.TOOLS_SHOVELS).add(ItemRegistry.ENDORIUM_SHOVEL.get());
		this.tag(Tags.Items.TOOLS_SWORDS).add(ItemRegistry.ENDORIUM_SWORD.get());
		
		ItemRegistry.ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get)
		.filter(item -> (item instanceof ShearsItem))
		.forEach(item -> {
			this.tag(Tags.Items.SHEARS).add(item);
		});
		
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
		
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		
		this.copy(Tags.Blocks.STONE, Tags.Items.STONE);
		
		this.tag(Tags.Items.RODS_BLAZE).add(net.minecraft.world.item.Items.BLAZE_ROD);
		
	}
	
}