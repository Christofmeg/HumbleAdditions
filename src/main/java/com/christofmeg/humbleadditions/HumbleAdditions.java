package com.christofmeg.humbleadditions;

import com.christofmeg.humbleadditions.common.ModDispenseItemBehavior;
import com.christofmeg.humbleadditions.common.world.ModConfiguredFeatures;
import com.christofmeg.humbleadditions.common.world.ModFeatures;
import com.christofmeg.humbleadditions.common.world.ModPlacedFeatures;
import com.christofmeg.humbleadditions.integration.ExNihilioSequentiaIntegration;
import com.christofmeg.humbleadditions.integration.jeri.ModWorldGen.CompatibilityHandler;
import com.christofmeg.humbleadditions.registry.BlockEntityRegistry;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.registry.MenuTypesRegistry;
import com.christofmeg.humbleadditions.registry.PaintingVariantRegistry;
import com.christofmeg.humbleadditions.registry.PotionRegistry;
import com.christofmeg.humbleadditions.registry.PotionRegistry.PotionEffectRegistry;
import com.christofmeg.humbleadditions.registry.VanillaRegistry;
import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HumbleAdditions {

	public HumbleAdditions() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		Config.init();

		BlockRegistry.BLOCKS.register(bus);
		BlockRegistry.VANILLA_TEXTURED_BLOCKS.register(bus);
		ItemRegistry.ITEMS.register(bus);
		ItemRegistry.ITEMS_AUTO_REGISTER.register(bus);
		ItemRegistry.BLOCK_ITEMS_AUTO_REGISTER.register(bus);
		EntityRegistry.ENTITY_TYPES.register(bus);

		VanillaRegistry.ITEMS.register(bus);
		VanillaRegistry.BLOCKS.register(bus);
		ModConfiguredFeatures.VANILLA_CONFIGURED_FEATURES.register(bus);
		ModFeatures.FEATURES.register(bus);

		ModConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
		ModPlacedFeatures.PLACED_FEATURES.register(bus);
		PaintingVariantRegistry.PAINTING_VARIANTS.register(bus);
		PotionRegistry.POTIONS.register(bus);
		PotionEffectRegistry.MOB_EFFECTS.register(bus);
		BlockEntityRegistry.BLOCK_ENTITY_TYPES.register(bus);
		MenuTypesRegistry.MENUS.register(bus);


		//		IronChestIntegration.IronChest.BLOCKS.register(bus);
		//		IronChestIntegration.IronChestRestocked.BLOCKS.register(bus);
		ExNihilioSequentiaIntegration.ITEMS.register(bus);

	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ComposterBlock.COMPOSTABLES.put(Items.ROTTEN_FLESH, 0.05F);
			ComposterBlock.COMPOSTABLES.put(Items.BAMBOO, 0.2F);
			ComposterBlock.COMPOSTABLES.put(Items.POISONOUS_POTATO, 0.65F);
			ComposterBlock.COMPOSTABLES.put(BlockRegistry.MOSS_LAYER_BLOCK.get().asItem(), 0.08125F);

			ModDispenseItemBehavior.init();
			PotionRegistry.setup();

			if(ModList.get().isLoaded("jeresources")) {
				CompatibilityHandler.init();
			}

			// FIX FALLING FULL BLOCKS STACKING

			// FALLING LAYERS item drops correct amount when broken by falling on other blocks

			// FALLING LAYERS remove itemdrops when block fell on self FallableLayerBlock$onBrokenAfterFall

			// fix falling layers not stacking when gamerule doEntityDrops is set to false

			// Layerblocks waterloggable

			// Look at shovel right click and falling or breaking when layer on layer OR merging


			/* IDEAS
			 * Frogs that grow in size when eating things
			 * ZOMBIES THAT GROWS ON EACH HIT
			 * REPAIRABLE ANVILS
			 */

			//TODO ADD SMELTING TO TOOLS

		});
	}




}