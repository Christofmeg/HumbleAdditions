package com.christofmeg.humbleadditions.data.client;

import java.util.function.Supplier;

import com.christofmeg.humbleadditions.common.blocks.AbstractLayerBlock;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
		withExistingParent("block_of_blaze_rods", modLoc("block/block_of_blaze_rods"));
		withExistingParent("smooth_ice", modLoc("block/smooth_ice"));
		withExistingParent("jack_o_soul_lantern", modLoc("block/jack_o_soul_lantern"));
		withExistingParent("cut_red_sandstone_stairs", mcLoc("block/cut_red_sandstone"));
		withExistingParent("cut_sandstone_stairs", mcLoc("block/cut_sandstone"));
		withExistingParent("smooth_stone_stairs", mcLoc("block/smooth_stone"));
		withExistingParent("packed_snow", modLoc("block/packed_snow"));
		withExistingParent("snow_bricks", modLoc("block/snow_bricks"));

		withExistingParent("limestone", modLoc("block/limestone"));
		withExistingParent("limestone_bricks", modLoc("block/limestone_bricks"));
		withExistingParent("polished_limestone", modLoc("block/polished_limestone"));
		withExistingParent("polished_limestone_bricks", modLoc("block/polished_limestone_bricks"));
		withExistingParent("chiseled_limestone", modLoc("block/chiseled_limestone"));

		withExistingParent("endorium_ore", modLoc("block/endorium_ore"));
		withExistingParent("raw_endorium_block", modLoc("block/raw_endorium_block"));
		withExistingParent("rose_gold_block", modLoc("block/rose_gold_block"));
		withExistingParent("endorium_block", modLoc("block/endorium_block"));
		withExistingParent("endorite_block", modLoc("block/endorite_block"));
		withExistingParent("raw_rose_gold_block", modLoc("block/raw_rose_gold_block"));

		withExistingParent("player_pressure_plate", modLoc("block/player_pressure_plate"));

		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof StairBlock || block instanceof SlabBlock))
		.forEach(block -> {
			withExistingParent(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""),
					modLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")));
		});

		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof WallBlock))
		.forEach(block -> {
			wallInventory(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""), modLoc("blocks/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "").replace("_wall", "").replace("brick", "bricks")));
		});

		BlockRegistry.VANILLA_TEXTURED_BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof WallBlock))
		.forEach(block -> {
			wallInventory(block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", ""),
					mcLoc("block/" + block.defaultBlockState().getBlock().toString().replace("Block{humbleadditions:", "").replace("}", "")
							.replace("brick", "bricks").replace("smooth_quartz_wall", "quartz_block_side").replace("quartz_wall", "quartz_block_top")
							.replace("smooth_red_sandstone", "red_sandstone_top").replace("smooth_sandstone", "sandstone_top").replace("_wall", "")
							));
		});

		BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
		.filter(block -> (block instanceof IronBarsBlock))
		.forEach(block -> {
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
		withExistingParent("jack_o_soul_snow_golem_spawn_egg", mcLoc("item/template_spawn_egg"));

		withExistingParent("illusioner_spawn_egg", mcLoc("item/template_spawn_egg"));
		withExistingParent("iron_golem_spawn_egg", mcLoc("item/template_spawn_egg"));
		withExistingParent("wither_spawn_egg", mcLoc("item/template_spawn_egg"));
		withExistingParent("ender_dragon_spawn_egg", mcLoc("item/template_spawn_egg"));
		withExistingParent("giant_spawn_egg", mcLoc("item/template_spawn_egg"));

		simpleItemWithOtherTexture(ItemRegistry.KILLER_BUNNY_SPAWN_EGG, modLoc("items/caerbannog_rabbit_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.PLAYFUL_PANDA_SPAWN_EGG, modLoc("items/panda_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.AGGRESSIVE_PANDA_SPAWN_EGG, modLoc("items/panda_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.WEAK_PANDA_SPAWN_EGG, modLoc("items/panda_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.WORRIED_PANDA_SPAWN_EGG, modLoc("items/panda_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.LAZY_PANDA_SPAWN_EGG, modLoc("items/panda_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.BABY_VILLAGER_SPAWN_EGG, modLoc("items/baby_villager_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.CHARGED_CREEPER_SPAWN_EGG, modLoc("items/charged_creeper_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.SHEARED_SHEEP_SPAWN_EGG, modLoc("items/sheared_sheep_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.SCREAMING_GOAT_SPAWN_EGG, modLoc("items/screaming_goat_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.SNOW_GOLEM_SPAWN_EGG, modLoc("items/snow_golem_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.JACK_O_SNOW_GOLEM_SPAWN_EGG, modLoc("items/snow_golem_spawn_egg"));

		simpleItemWithOtherTexture(ItemRegistry.BLUE_AXOLOTL_SPAWN_EGG, modLoc("items/blue_axolotl_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.CYAN_AXOLOTL_SPAWN_EGG, modLoc("items/cyan_axolotl_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.GOLD_AXOLOTL_SPAWN_EGG, modLoc("items/gold_axolotl_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.LUCY_AXOLOTL_SPAWN_EGG, modLoc("items/lucy_axolotl_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.WILD_AXOLOTL_SPAWN_EGG, modLoc("items/wild_axolotl_spawn_egg"));

		simpleItemWithOtherTexture(ItemRegistry.COLD_FROG_SPAWN_EGG, modLoc("items/cold_frog_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.TEMPERATE_FROG_SPAWN_EGG, modLoc("items/temperate_frog_spawn_egg"));
		simpleItemWithOtherTexture(ItemRegistry.WARM_FROG_SPAWN_EGG, modLoc("items/warm_frog_spawn_egg"));

		ItemRegistry.ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get)
		.filter(item -> (!(item instanceof ForgeSpawnEggItem || item.toString().contains("sword") || item.toString().contains("axe") || item.toString().contains("hoe") || item.toString().contains("shovel"))))
		.forEach(item -> {
			simpleItem(new ResourceLocation(ModConstants.MOD_ID + ":" + item));
		});

		ItemRegistry.ITEMS_AUTO_REGISTER.getEntries().stream().map(RegistryObject::get)
		.filter(item -> (!(item instanceof ForgeSpawnEggItem)) || item.toString().contains("sword") || item.toString().contains("axe") || item.toString().contains("hoe") || item.toString().contains("shovel"))
		.forEach(item -> {
			simpleHandHeldItem(new ResourceLocation(ModConstants.MOD_ID + ":" + item));
		});

	}


	private void simpleItemWithOtherTexture(Supplier<? extends Item> item, ResourceLocation string) {
		ResourceLocation location = new ResourceLocation(item.get().getDescriptionId().replace("Item{", "").replace("}", "").replace("item.humbleadditions.", ""));
		this.getBuilder(location.getPath())
		.parent(new ModelFile.UncheckedModelFile("item/generated"))
		.texture("layer0", string);
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

	public ItemModelBuilder simpleHandHeldItem(ResourceLocation item)
	{
		return getBuilder(item.toString())
				.parent(new ModelFile.UncheckedModelFile("item/handheld"))
				.texture("layer0", new ResourceLocation(item.getNamespace(), ITEM_FOLDER + "s/" + item.getPath()));
	}


}
