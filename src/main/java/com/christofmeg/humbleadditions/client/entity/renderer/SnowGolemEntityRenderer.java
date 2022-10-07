package com.christofmeg.humbleadditions.client.entity.renderer;

import com.christofmeg.humbleadditions.client.entity.renderer.layers.SnowGolemEntityHeadLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowGolemEntityRenderer extends net.minecraft.client.renderer.entity.SnowGolemRenderer {

	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("minecraft", "textures/entities/" + "snow_golem" + ".png");
	//TODO GLOWING HEAD
	public SnowGolemEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.addLayer(new SnowGolemEntityHeadLayer(this, context.getBlockRenderDispatcher(), context.getItemRenderer()));
	}

	public ResourceLocation getTextureLocation(Zombie pEntity) {
		return TEXTURE_LOCATION;
	}

}