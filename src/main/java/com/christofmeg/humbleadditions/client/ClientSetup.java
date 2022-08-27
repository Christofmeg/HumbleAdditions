package com.christofmeg.humbleadditions.client;

import com.christofmeg.humbleadditions.client.entity.renderer.RedHuskRenderer;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModConstants.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.RED_HUSK.get(), RedHuskRenderer::new);
    }
	
	
}
