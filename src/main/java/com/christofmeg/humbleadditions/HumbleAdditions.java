package com.christofmeg.humbleadditions;

import com.christofmeg.humbleadditions.common.ModDispenseItemBehavior;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.registry.VanillaItemRegistry;
import com.christofmeg.humbleadditions.setup.Config;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HumbleAdditions {
    public static final String MODID = "tutorialmod";

    public HumbleAdditions() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Config.init();
        
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        EntityRegistry.ENTITY_TYPES.register(bus);
        VanillaItemRegistry.ITEMS.register(bus);
        
        
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
			ComposterBlock.COMPOSTABLES.put(Items.ROTTEN_FLESH, 0.05F);
			ComposterBlock.COMPOSTABLES.put(Items.BAMBOO, 0.2F);
			ComposterBlock.COMPOSTABLES.put(Items.POISONOUS_POTATO, 0.65F);
			ComposterBlock.COMPOSTABLES.put(BlockRegistry.MOSS_LAYER_BLOCK.get().asItem(), 0.08125F);
			
			
			ModDispenseItemBehavior.init();
			
			
			
			//TODO FIX FALLING FULL BLOCKS STACKING
			
			
			//TODO FALLING LAYERS item drops correct amount when broken by falling on other blocks
			
			//TODO FALLING LAYERS remove itemdrops when block fell on self FallableLayerBlock$onBrokenAfterFall
			
			
			//TODO fix falling layers not stacking when gamerule doEntityDrops is set to false
			
			//Layerblocks waterloggable
			
			//Look at shovel right click and falling or breaking when layer on layer OR merging
			
			
			//https://www.curseforge.com/minecraft/mc-mods/aquatic-torches
			
			
			
			//TODO Custom snow men ENTITY heads with jack o lanterns
			//TODO Frogs that grow in size when eating things
			//TODO ZOMBIES THAT GROWS ON EACH HIT
			
			/*TODO LIMESTONE 
			 *  ADD WORLD GEN
			 *  ADD STONECUTTING RECIPES
			*/
			
			
		});
    }
    
    
    
    
}