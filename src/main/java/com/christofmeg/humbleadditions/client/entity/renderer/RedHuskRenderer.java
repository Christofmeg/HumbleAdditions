package com.christofmeg.humbleadditions.client.entity.renderer;

import com.christofmeg.humbleadditions.setup.ModConstants;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedHuskRenderer extends ZombieRenderer {
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ModConstants.MOD_ID, "textures/entities/zombie/" + "red_husk" + ".png");

	public RedHuskRenderer(EntityRendererProvider.Context p_174180_) {
		super(p_174180_, ModelLayers.HUSK, ModelLayers.HUSK_INNER_ARMOR, ModelLayers.HUSK_OUTER_ARMOR);
	}

	protected void scale(Zombie pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		pMatrixStack.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(pLivingEntity, pMatrixStack, pPartialTickTime);
	}

   /**
    * Returns the location of an entity's texture.
    */
	public ResourceLocation getTextureLocation(Zombie pEntity) {
		return TEXTURE_LOCATION;
	}

}