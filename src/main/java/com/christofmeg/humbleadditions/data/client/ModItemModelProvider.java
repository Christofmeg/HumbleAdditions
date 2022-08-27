package com.christofmeg.humbleadditions.data.client;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

	public static final ResourceLocation TRANSLUCENT = new ResourceLocation("minecraft:translucent");
	
	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ModConstants.MOD_ID, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_ID + " - ItemModel";
	}

	@Override
	protected void registerModels() {

		withExistingParent("charcoal_block", modLoc("block/charcoal_block"));
		withExistingParent("smooth_ice", modLoc("block/smooth_ice"));
		withExistingParent("jack_o_soul_lantern", modLoc("block/jack_o_soul_lantern"));

		
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof IronBarsBlock))
		.forEach(block -> {
//			withExistingParent(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
//					modLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		
			simpleItemWithOtherTexture(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
				mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("glowing_", "").replace("_pane", "")), TRANSLUCENT);
		});
		
		
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof AbstractGlassBlock))
		.forEach(block -> {
			withExistingParent(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
					modLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		});
		
		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof AbstractLayerBlock))
		.forEach(block -> {
			withExistingParent(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), 
					modLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")) + "_height02");
		});


		withExistingParent("red_husk_spawn_egg", mcLoc("item/template_spawn_egg"));
		
		simpleItem(ItemRegistry.MILK_BOTTLE.getId());
		simpleItem(ItemRegistry.NETHERITE_HORSE_ARMOR.getId());
		
	}
	
	public ItemModelBuilder simpleItemWithOtherTexture(String item, ResourceLocation string, ResourceLocation renderType)
    {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", string)
                .renderType(renderType);
    }
	
	public ItemModelBuilder simpleItem(ResourceLocation item)
    {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), ITEM_FOLDER + "s/" + item.getPath()));
    }
	
}
