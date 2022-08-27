package com.christofmeg.humbleadditions.registry;

import java.util.function.Supplier;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.CustomCarvedPumpkinBlock;
import com.christofmeg.humbleadditions.common.blocks.MossLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.SandLayerBlock;
import com.christofmeg.humbleadditions.common.blocks.SmoothIceBlock;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModConstants.MODID);
	
	public static final RegistryObject<Block> CHARCOAL_BLOCK = registerBlock("charcoal_block", 
		() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
			.strength(5.0F, 6.0F)
			.requiresCorrectToolForDrops()
			), props());
	
	public static final RegistryObject<Block> JACK_O_SOUL_LANTERN = registerBlock("jack_o_soul_lantern",
        () -> new CustomCarvedPumpkinBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_ORANGE).friction(0.5f).strength(1f, 5f).strength(1.0F).sound(SoundType.WOOD)
            .lightLevel((p_50870_) -> {return 15;})
            .isValidSpawn(BlockRegistry::always)
            ), props());
	
	public static final RegistryObject<Block> SMOOTH_ICE = registerBlock("smooth_ice",
    	() -> new SmoothIceBlock(BlockBehaviour.Properties.of(Material.ICE_SOLID)   			
    		.strength(4.1F)
    		.friction((float) (1 / 0.91))
            .sound(SoundType.GLASS)
            ), props());
	
	public static final RegistryObject<Block> MOSS_LAYER_BLOCK = registerBlock("moss_layer_block", 
		() -> new MossLayerBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN)
			.strength(0.1F)
			.sound(SoundType.MOSS)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), props());
	
	public static final RegistryObject<Block> SAND_LAYER_BLOCK = registerBlock("sand_layer_block", 
		() -> new SandLayerBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
			.strength(0.5F)
			.sound(SoundType.SAND)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), props());
	
	public static final RegistryObject<Block> RED_SAND_LAYER_BLOCK = registerBlock("red_sand_layer_block", 
		() -> new SandLayerBlock(11098145, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_ORANGE)
			.strength(0.5F)
			.sound(SoundType.SAND)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), props());

	public static final RegistryObject<Block> GRAVEL_LAYER_BLOCK = registerBlock("gravel_layer_block", 
		() -> new SandLayerBlock(-8356741, BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN)
			.strength(0.6F)
			.sound(SoundType.GRAVEL)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), props());
	
//TODO make glowing SAND, SANDSTONE BRICKS, SMOOTH SANDSTONE, SMELTING GLOWING SAND TO GLASS, CHISELED SANDSTONE, STAIRS, WALLS, SLABS	
/*	
 *
	public static final RegistryObject<Block> GLOWING_SAND_BLOCK = registerBlock("glowing_sand_block", () -> new SandBlock(14406560,  BlockBehaviour.Properties.copy(Blocks.SAND)
			.emissiveRendering((state, world, pos) -> true)
			.lightLevel((b) -> 1)
			), props());
	public static final RegistryObject<Block> GLOWING_RED_SAND_BLOCK = registerBlock("glowing_red_sand_block", () -> new SandBlock(11098145,  BlockBehaviour.Properties.copy(Blocks.RED_SAND)
			.emissiveRendering((state, world, pos) -> true)
			.lightLevel((b) -> 1)
			), props());
*/
	
	public static final RegistryObject<Block> GLOWING_GLASS = registerBlock("glowing_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1)
		.isValidSpawn(BlockRegistry::shouldAllowSpawn)
		.isRedstoneConductor(BlockRegistry::isNotSolid)
		.isSuffocating(BlockRegistry::isNotSolid)
		.isViewBlocking(BlockRegistry::isNotSolid)), props());
	
	public static final RegistryObject<Block> WHITE_STAINED_GLOWING_GLASS = registerBlock("white_stained_glowing_glass", () -> stainedGlass(DyeColor.WHITE), props());
	public static final RegistryObject<Block> ORANGE_STAINED_GLOWING_GLASS = registerBlock("orange_stained_glowing_glass", () -> stainedGlass(DyeColor.ORANGE), props());
	public static final RegistryObject<Block> MAGENTA_STAINED_GLOWING_GLASS = registerBlock("magenta_stained_glowing_glass", () -> stainedGlass(DyeColor.MAGENTA), props());
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLOWING_GLASS = registerBlock("light_blue_stained_glowing_glass", () -> stainedGlass(DyeColor.LIGHT_BLUE), props());
	public static final RegistryObject<Block> YELLOW_STAINED_GLOWING_GLASS = registerBlock("yellow_stained_glowing_glass", () -> stainedGlass(DyeColor.YELLOW), props());
	public static final RegistryObject<Block> LIME_STAINED_GLOWING_GLASS = registerBlock("lime_stained_glowing_glass", () -> stainedGlass(DyeColor.LIME), props());
	public static final RegistryObject<Block> PINK_STAINED_GLOWING_GLASS = registerBlock("pink_stained_glowing_glass", () -> stainedGlass(DyeColor.PINK), props());
	public static final RegistryObject<Block> GRAY_STAINED_GLOWING_GLASS = registerBlock("gray_stained_glowing_glass", () -> stainedGlass(DyeColor.GRAY), props());
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLOWING_GLASS = registerBlock("light_gray_stained_glowing_glass", () -> stainedGlass(DyeColor.LIGHT_GRAY), props());
	public static final RegistryObject<Block> CYAN_STAINED_GLOWING_GLASS = registerBlock("cyan_stained_glowing_glass", () -> stainedGlass(DyeColor.CYAN), props());
	public static final RegistryObject<Block> PURPLE_STAINED_GLOWING_GLASS = registerBlock("purple_stained_glowing_glass", () -> stainedGlass(DyeColor.PURPLE), props());
	public static final RegistryObject<Block> BLUE_STAINED_GLOWING_GLASS = registerBlock("blue_stained_glowing_glass", () -> stainedGlass(DyeColor.BLUE), props());
	public static final RegistryObject<Block> BROWN_STAINED_GLOWING_GLASS = registerBlock("brown_stained_glowing_glass", () -> stainedGlass(DyeColor.BROWN), props());
	public static final RegistryObject<Block> GREEN_STAINED_GLOWING_GLASS = registerBlock("green_stained_glowing_glass", () -> stainedGlass(DyeColor.GREEN), props());
	public static final RegistryObject<Block> RED_STAINED_GLOWING_GLASS = registerBlock("red_stained_glowing_glass", () -> stainedGlass(DyeColor.RED), props());
	public static final RegistryObject<Block> BLACK_STAINED_GLOWING_GLASS = registerBlock("black_stained_glowing_glass", () -> stainedGlass(DyeColor.BLACK), props());

	public static final RegistryObject<Block> GLOWING_GLASS_PANE = registerBlock("glowing_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS)
		.strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1)), props());
	
	public static final RegistryObject<Block> WHITE_STAINED_GLOWING_GLASS_PANE = registerBlock("white_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.WHITE), props());
	public static final RegistryObject<Block> ORANGE_STAINED_GLOWING_GLASS_PANE = registerBlock("orange_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.ORANGE), props());
	public static final RegistryObject<Block> MAGENTA_STAINED_GLOWING_GLASS_PANE = registerBlock("magenta_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.MAGENTA), props());
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE = registerBlock("light_blue_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIGHT_BLUE), props());
	public static final RegistryObject<Block> YELLOW_STAINED_GLOWING_GLASS_PANE = registerBlock("yellow_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.YELLOW), props());
	public static final RegistryObject<Block> LIME_STAINED_GLOWING_GLASS_PANE = registerBlock("lime_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIME), props());
	public static final RegistryObject<Block> PINK_STAINED_GLOWING_GLASS_PANE = registerBlock("pink_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.PINK), props());
	public static final RegistryObject<Block> GRAY_STAINED_GLOWING_GLASS_PANE = registerBlock("gray_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.GRAY), props());
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE = registerBlock("light_gray_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIGHT_GRAY), props());
	public static final RegistryObject<Block> CYAN_STAINED_GLOWING_GLASS_PANE = registerBlock("cyan_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.CYAN), props());
	public static final RegistryObject<Block> PURPLE_STAINED_GLOWING_GLASS_PANE = registerBlock("purple_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.PURPLE), props());
	public static final RegistryObject<Block> BLUE_STAINED_GLOWING_GLASS_PANE = registerBlock("blue_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BLUE), props());
	public static final RegistryObject<Block> BROWN_STAINED_GLOWING_GLASS_PANE = registerBlock("brown_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BROWN), props());
	public static final RegistryObject<Block> GREEN_STAINED_GLOWING_GLASS_PANE = registerBlock("green_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.GREEN), props());
	public static final RegistryObject<Block> RED_STAINED_GLOWING_GLASS_PANE = registerBlock("red_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.RED), props());
	public static final RegistryObject<Block> BLACK_STAINED_GLOWING_GLASS_PANE = registerBlock("black_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BLACK), props());
	
	public static final RegistryObject<Block> LIMESTONE = registerBlock("limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<Block> POLISHED_LIMESTONE = registerBlock("polished_limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<Block> POLISHED_LIMESTONE_BRICKS = registerBlock("polished_limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<Block> CHISELED_LIMESTONE = registerBlock("chiseled_limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), props());

	public static final RegistryObject<WallBlock> LIMESTONE_WALL = registerBlock("limestone" + "_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<WallBlock> LIMESTONE_BRICK_WALL = registerBlock("limestone_brick" + "_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<WallBlock> POLISHED_LIMESTONE_WALL = registerBlock("polished_limestone" + "_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<WallBlock> POLISHED_LIMESTONE_BRICK_WALL = registerBlock("polished_limestone_brick" + "_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	
	public static final RegistryObject<StairBlock> LIMESTONE_STAIRS = registerBlock("limestone" + "_stairs", () -> new StairBlock(() -> LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<StairBlock> LIMESTONE_BRICKS_STAIRS = registerBlock("limestone_bricks" + "_stairs", () -> new StairBlock(() -> LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<StairBlock> POLISHED_LIMESTONE_STAIRS = registerBlock("polished_limestone" + "_stairs", () -> new StairBlock(() -> LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<StairBlock> POLISHED_LIMESTONE_BRICKS_STAIRS = registerBlock("polished_limestone_bricks" + "_stairs", () -> new StairBlock(() -> LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	
	public static final RegistryObject<SlabBlock> LIMESTONE_SLAB = registerBlock("limestone" + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<SlabBlock> LIMESTONE_BRICKS_SLAB = registerBlock("limestone_bricks" + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<SlabBlock> POLISHED_LIMESTONE_SLAB = registerBlock("polished_limestone" + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());
	public static final RegistryObject<SlabBlock> POLISHED_LIMESTONE_BRICKS_SLAB = registerBlock("polished_limestone_bricks" + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)), props());

	private static StainedGlassBlock stainedGlass(DyeColor color) {
		return new StainedGlassBlock(color, BlockBehaviour.Properties.of(Material.GLASS, color).strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1)
			.isValidSpawn(BlockRegistry::shouldAllowSpawn)
			.isRedstoneConductor(BlockRegistry::isNotSolid)
			.isSuffocating(BlockRegistry::isNotSolid)
			.isViewBlocking(BlockRegistry::isNotSolid));
	}
	
	private static StainedGlassPaneBlock stainedGlassPane(DyeColor color) {
		return new StainedGlassPaneBlock(color, BlockBehaviour.Properties.of(Material.GLASS, color).strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1));
	}
	
	public static Boolean shouldAllowSpawn(BlockState state, BlockGetter blocGetter, BlockPos pos, EntityType<?> entity) {
		return false;
	}
   
	public static Boolean isNotSolid(BlockState state, BlockGetter blocGetter, BlockPos pos) {
		return false;
	}

	private static Boolean always(BlockState p_50810_, BlockGetter p_50811_, BlockPos p_50812_, EntityType<?> p_50813_) {
		return (boolean)true;
	}

	private static Item.Properties props() {
        return new Item.Properties().tab(ItemRegistry.TAB);
    }

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }
    
	@SuppressWarnings("unused")
	private static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        return block;
    }

 
}
