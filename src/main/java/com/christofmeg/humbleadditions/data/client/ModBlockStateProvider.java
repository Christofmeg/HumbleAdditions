package com.christofmeg.humbleadditions.data.client;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.gson.JsonElement;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider<T> extends BlockStateProvider {

	final Consumer<BlockStateGenerator> blockStateOutput;
	final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;
	public static final ResourceLocation TRANSLUCENT = new ResourceLocation("minecraft:translucent");
	
	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper, Consumer<BlockStateGenerator> pBlockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> pModelOutput) {
		super(gen, ModConstants.MOD_ID, exFileHelper);
		this.blockStateOutput = pBlockStateOutput;
		this.modelOutput = pModelOutput;
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_ID + " - BlockModel & BlockState";
	}

	@Override
	protected void registerStatesAndModels() {
		
		simpleBlock(BlockRegistry.CHARCOAL_BLOCK.get(), models().cubeAll("charcoal_block", modLoc("blocks/charcoal_block")));
		simpleBlock(BlockRegistry.SMOOTH_ICE.get(), models().cubeAll("smooth_ice", modLoc("blocks/smooth_ice")));
		horizontalBlock(BlockRegistry.JACK_O_SOUL_LANTERN.get(), mcLoc("block/pumpkin_side"), modLoc("blocks/jack_o_soul_lantern"), mcLoc("block/pumpkin_top"));
		simpleBlock(BlockRegistry.QUICK_SAND.get(), models().singleTexture("quick_sand", modLoc("block/abstract_quick_sand"), modLoc("blocks/quick_sand")));
		
		simpleBlock(BlockRegistry.LIMESTONE.get(), models().cubeAll("limestone", modLoc("blocks/limestone")));
		simpleBlock(BlockRegistry.LIMESTONE_BRICKS.get(), models().cubeAll("limestone_bricks", modLoc("blocks/limestone_bricks")));
		simpleBlock(BlockRegistry.POLISHED_LIMESTONE.get(), models().cubeAll("polished_limestone", modLoc("blocks/polished_limestone")));
		simpleBlock(BlockRegistry.POLISHED_LIMESTONE_BRICKS.get(), models().cubeAll("polished_limestone_bricks", modLoc("blocks/polished_limestone_bricks")));
		simpleBlock(BlockRegistry.CHISELED_LIMESTONE.get(), models().cubeAll("chiseled_limestone", modLoc("blocks/chiseled_limestone")));
		
		wallBlock(BlockRegistry.LIMESTONE_WALL.get(), modLoc("blocks/limestone"));
		wallBlock(BlockRegistry.LIMESTONE_BRICK_WALL.get(), modLoc("blocks/limestone_bricks"));
		wallBlock(BlockRegistry.POLISHED_LIMESTONE_WALL.get(), modLoc("blocks/polished_limestone"));
		wallBlock(BlockRegistry.POLISHED_LIMESTONE_BRICK_WALL.get(), modLoc("blocks/polished_limestone_bricks"));
		wallBlock(BlockRegistry.QUARTZ_WALL.get(), mcLoc("block/quartz_block_top"));
		wallBlock(BlockRegistry.SMOOTH_QUARTZ_WALL.get(), mcLoc("block/quartz_block_side"));
		wallBlock(BlockRegistry.SMOOTH_RED_SANDSTONE_WALL.get(), mcLoc("block/red_sandstone_top"));
		wallBlock(BlockRegistry.SMOOTH_SANDSTONE_WALL.get(), mcLoc("block/sandstone_top"));
		
		stairsBlock(BlockRegistry.LIMESTONE_STAIRS.get(), modLoc("blocks/limestone"));
		stairsBlock(BlockRegistry.LIMESTONE_BRICKS_STAIRS.get(), modLoc("blocks/limestone_bricks"));
		stairsBlock(BlockRegistry.POLISHED_LIMESTONE_STAIRS.get(), modLoc("blocks/polished_limestone"));
		stairsBlock(BlockRegistry.POLISHED_LIMESTONE_BRICKS_STAIRS.get(), modLoc("blocks/polished_limestone_bricks"));
		stairsBlock(BlockRegistry.CUT_RED_SANDSTONE_STAIRS.get(), mcLoc("block/cut_red_sandstone"));
		stairsBlock(BlockRegistry.CUT_SANDSTONE_STAIRS.get(), mcLoc("block/cut_sandstone"));
		stairsBlock(BlockRegistry.SMOOTH_STONE_STAIRS.get(), mcLoc("block/smooth_stone"));

		slabBlock(BlockRegistry.LIMESTONE_SLAB.get(), modLoc("block/limestone"), modLoc("blocks/limestone"));
		slabBlock(BlockRegistry.LIMESTONE_BRICKS_SLAB.get(), modLoc("block/limestone_bricks"), modLoc("blocks/limestone_bricks"));
		slabBlock(BlockRegistry.POLISHED_LIMESTONE_SLAB.get(), modLoc("block/polished_limestone"), modLoc("blocks/polished_limestone"));
		slabBlock(BlockRegistry.POLISHED_LIMESTONE_BRICKS_SLAB.get(), modLoc("block/polished_limestone_bricks"), modLoc("blocks/polished_limestone_bricks"));
		
		BlockRegistry.VANILLA_TEXTURED_BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof WallBlock))
		.filter(block -> (!(block == BlockRegistry.QUARTZ_WALL.get() || block == BlockRegistry.SMOOTH_QUARTZ_WALL.get() ||
			block == BlockRegistry.SMOOTH_RED_SANDSTONE_WALL.get() || block == BlockRegistry.SMOOTH_SANDSTONE_WALL.get())))
		.forEach(block -> {
			wallBlock((WallBlock) block, mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")
				.replace("brick", "bricks").replace("_wall", "")
				));
		});
		
		layerBlocks(BlockRegistry.MOSS_LAYER_BLOCK.get(), mcLoc("block/moss_block"));
		layerBlocks(BlockRegistry.SAND_LAYER_BLOCK.get(), mcLoc("block/sand"));
		layerBlocks(BlockRegistry.RED_SAND_LAYER_BLOCK.get(), mcLoc("block/red_sand"));
		layerBlocks(BlockRegistry.GRAVEL_LAYER_BLOCK.get(), mcLoc("block/gravel"));
		
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof IronBarsBlock))
		.forEach(block -> {
			paneBlockWithRenderType((IronBarsBlock)(block), mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("glowing_", "").replace("_pane", "")), 
				mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("_pane", "").replace("glowing_glass", "glass_pane_top")), TRANSLUCENT);
		});
		
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof AbstractGlassBlock))
		.forEach(block -> {
			simpleBlock(block, models().cubeAll(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
				mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("glowing_", ""))).renderType(TRANSLUCENT));
		});
		
		/*
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof SandBlock))
		.forEach(block -> {
			simpleBlock(block, models().cubeAll(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
				mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("glowing_", "").replace("_block", ""))));
		});
		*/
		
	}
	
	public void layerBlocks(Block block, ResourceLocation textureFromBlock) {
		
		ConfiguredModel[]layerBlockBuilder1 = ConfiguredModel.builder() //name on models/block -> .json file				 parent block model
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height02", modLoc("block/template/height02"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder2 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height04", modLoc("block/template/height04"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder3 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height06", modLoc("block/template/height06"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder4 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height08", modLoc("block/template/height08"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder5 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height10", modLoc("block/template/height10"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder6 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height12", modLoc("block/template/height12"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder7 = ConfiguredModel.builder()
				.modelFile(models().singleTexture(((block.toString().replace("Block{", "")).replace("}", "")) + "_height14", modLoc("block/template/height14"), textureFromBlock)).build();
		ConfiguredModel[]layerBlockBuilder8 = ConfiguredModel.builder()
				.modelFile(models().cubeAll(((block.toString().replace("Block{", "")).replace("}", "")) + "_height16", textureFromBlock)).build();
		
		getVariantBuilder(block)
			.partialState().with(AbstractLayerBlock.LAYERS, 1).addModels(layerBlockBuilder1)
			.partialState().with(AbstractLayerBlock.LAYERS, 2).addModels(layerBlockBuilder2)
			.partialState().with(AbstractLayerBlock.LAYERS, 3).addModels(layerBlockBuilder3)
			.partialState().with(AbstractLayerBlock.LAYERS, 4).addModels(layerBlockBuilder4)
			.partialState().with(AbstractLayerBlock.LAYERS, 5).addModels(layerBlockBuilder5)
			.partialState().with(AbstractLayerBlock.LAYERS, 6).addModels(layerBlockBuilder6)
			.partialState().with(AbstractLayerBlock.LAYERS, 7).addModels(layerBlockBuilder7)
			.partialState().with(AbstractLayerBlock.LAYERS, 8).addModels(layerBlockBuilder8)
		;
		
	}

	
}
