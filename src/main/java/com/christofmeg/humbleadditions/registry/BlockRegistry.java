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
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
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
			), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> JACK_O_SOUL_LANTERN = registerBlock("jack_o_soul_lantern",
        () -> new CustomCarvedPumpkinBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_ORANGE).friction(0.5f).strength(1f, 5f).strength(1.0F).sound(SoundType.WOOD)
            .lightLevel((p_50870_) -> {return 15;})
            .isValidSpawn(BlockRegistry::always)
            ), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> SMOOTH_ICE = registerBlock("smooth_ice",
    	() -> new SmoothIceBlock(BlockBehaviour.Properties.of(Material.ICE_SOLID)   			
    		.strength(4.1F)
    		.friction((float) (1 / 0.91))
            .sound(SoundType.GLASS)
            ), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> MOSS_LAYER_BLOCK = registerBlock("moss_layer_block", 
		() -> new MossLayerBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN)
			.strength(0.1F)
			.sound(SoundType.MOSS)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> SAND_LAYER_BLOCK = registerBlock("sand_layer_block", 
		() -> new SandLayerBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
			.strength(0.5F)
			.sound(SoundType.SAND)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> RED_SAND_LAYER_BLOCK = registerBlock("red_sand_layer_block", 
		() -> new SandLayerBlock(11098145, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_ORANGE)
			.strength(0.5F)
			.sound(SoundType.SAND)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), new Item.Properties().tab(ItemRegistry.TAB));

	public static final RegistryObject<Block> GRAVEL_LAYER_BLOCK = registerBlock("gravel_layer_block", 
		() -> new SandLayerBlock(-8356741, BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN)
			.strength(0.6F)
			.sound(SoundType.GRAVEL)
			.isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
			      return p_187417_.getValue(AbstractLayerBlock.LAYERS) >= 8;
			   })
			), new Item.Properties().tab(ItemRegistry.TAB));
	
//TODO make glowing SAND, SANDSTONE BRICKS, SMOOTH SANDSTONE, SMELTING GLOWING SAND TO GLASS, CHISELED SANDSTONE, STAIRS, WALLS, SLABS	
/*	
 *
	public static final RegistryObject<Block> GLOWING_SAND_BLOCK = registerBlock("glowing_sand_block", () -> new SandBlock(14406560,  BlockBehaviour.Properties.copy(Blocks.SAND)
			.emissiveRendering((state, world, pos) -> true)
			.lightLevel((b) -> 1)
			), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> GLOWING_RED_SAND_BLOCK = registerBlock("glowing_red_sand_block", () -> new SandBlock(11098145,  BlockBehaviour.Properties.copy(Blocks.RED_SAND)
			.emissiveRendering((state, world, pos) -> true)
			.lightLevel((b) -> 1)
			), new Item.Properties().tab(ItemRegistry.TAB));
*/
	
	public static final RegistryObject<Block> GLOWING_GLASS = registerBlock("glowing_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1)
		.isValidSpawn(BlockRegistry::shouldAllowSpawn)
		.isRedstoneConductor(BlockRegistry::isNotSolid)
		.isSuffocating(BlockRegistry::isNotSolid)
		.isViewBlocking(BlockRegistry::isNotSolid)), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> WHITE_STAINED_GLOWING_GLASS = registerBlock("white_stained_glowing_glass", () -> stainedGlass(DyeColor.WHITE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> ORANGE_STAINED_GLOWING_GLASS = registerBlock("orange_stained_glowing_glass", () -> stainedGlass(DyeColor.ORANGE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> MAGENTA_STAINED_GLOWING_GLASS = registerBlock("magenta_stained_glowing_glass", () -> stainedGlass(DyeColor.MAGENTA), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLOWING_GLASS = registerBlock("light_blue_stained_glowing_glass", () -> stainedGlass(DyeColor.LIGHT_BLUE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> YELLOW_STAINED_GLOWING_GLASS = registerBlock("yellow_stained_glowing_glass", () -> stainedGlass(DyeColor.YELLOW), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIME_STAINED_GLOWING_GLASS = registerBlock("lime_stained_glowing_glass", () -> stainedGlass(DyeColor.LIME), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> PINK_STAINED_GLOWING_GLASS = registerBlock("pink_stained_glowing_glass", () -> stainedGlass(DyeColor.PINK), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> GRAY_STAINED_GLOWING_GLASS = registerBlock("gray_stained_glowing_glass", () -> stainedGlass(DyeColor.GRAY), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLOWING_GLASS = registerBlock("light_gray_stained_glowing_glass", () -> stainedGlass(DyeColor.LIGHT_GRAY), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> CYAN_STAINED_GLOWING_GLASS = registerBlock("cyan_stained_glowing_glass", () -> stainedGlass(DyeColor.CYAN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> PURPLE_STAINED_GLOWING_GLASS = registerBlock("purple_stained_glowing_glass", () -> stainedGlass(DyeColor.PURPLE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BLUE_STAINED_GLOWING_GLASS = registerBlock("blue_stained_glowing_glass", () -> stainedGlass(DyeColor.BLUE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BROWN_STAINED_GLOWING_GLASS = registerBlock("brown_stained_glowing_glass", () -> stainedGlass(DyeColor.BROWN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> GREEN_STAINED_GLOWING_GLASS = registerBlock("green_stained_glowing_glass", () -> stainedGlass(DyeColor.GREEN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> RED_STAINED_GLOWING_GLASS = registerBlock("red_stained_glowing_glass", () -> stainedGlass(DyeColor.RED), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BLACK_STAINED_GLOWING_GLASS = registerBlock("black_stained_glowing_glass", () -> stainedGlass(DyeColor.BLACK), new Item.Properties().tab(ItemRegistry.TAB));

	public static final RegistryObject<Block> GLOWING_GLASS_PANE = registerBlock("glowing_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS)
		.strength(0.3F).sound(SoundType.GLASS).noOcclusion().emissiveRendering((state, world, pos) -> true).lightLevel((b) -> 1)), new Item.Properties().tab(ItemRegistry.TAB));
	
	public static final RegistryObject<Block> WHITE_STAINED_GLOWING_GLASS_PANE = registerBlock("white_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.WHITE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> ORANGE_STAINED_GLOWING_GLASS_PANE = registerBlock("orange_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.ORANGE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> MAGENTA_STAINED_GLOWING_GLASS_PANE = registerBlock("magenta_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.MAGENTA), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLOWING_GLASS_PANE = registerBlock("light_blue_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIGHT_BLUE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> YELLOW_STAINED_GLOWING_GLASS_PANE = registerBlock("yellow_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.YELLOW), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIME_STAINED_GLOWING_GLASS_PANE = registerBlock("lime_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIME), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> PINK_STAINED_GLOWING_GLASS_PANE = registerBlock("pink_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.PINK), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> GRAY_STAINED_GLOWING_GLASS_PANE = registerBlock("gray_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.GRAY), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLOWING_GLASS_PANE = registerBlock("light_gray_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.LIGHT_GRAY), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> CYAN_STAINED_GLOWING_GLASS_PANE = registerBlock("cyan_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.CYAN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> PURPLE_STAINED_GLOWING_GLASS_PANE = registerBlock("purple_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.PURPLE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BLUE_STAINED_GLOWING_GLASS_PANE = registerBlock("blue_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BLUE), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BROWN_STAINED_GLOWING_GLASS_PANE = registerBlock("brown_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BROWN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> GREEN_STAINED_GLOWING_GLASS_PANE = registerBlock("green_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.GREEN), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> RED_STAINED_GLOWING_GLASS_PANE = registerBlock("red_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.RED), new Item.Properties().tab(ItemRegistry.TAB));
	public static final RegistryObject<Block> BLACK_STAINED_GLOWING_GLASS_PANE = registerBlock("black_stained_glowing_glass_pane", () -> stainedGlassPane(DyeColor.BLACK), new Item.Properties().tab(ItemRegistry.TAB));
	
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
