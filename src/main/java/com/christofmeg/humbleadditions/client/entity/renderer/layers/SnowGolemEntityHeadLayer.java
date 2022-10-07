package com.christofmeg.humbleadditions.client.entity.renderer.layers;

import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowGolemEntityHeadLayer extends RenderLayer<SnowGolem, SnowGolemModel<SnowGolem>> {
	private final BlockRenderDispatcher blockRenderer;
	private final ItemRenderer itemRenderer;

	public SnowGolemEntityHeadLayer(RenderLayerParent<SnowGolem, SnowGolemModel<SnowGolem>> p_234871_, BlockRenderDispatcher p_234872_, ItemRenderer p_234873_) {
		super(p_234871_);
		this.blockRenderer = p_234872_;
		this.itemRenderer = p_234873_;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, SnowGolem entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		if(((SnowGolemEntity)entity).hasCarvedPumpkin()) {
			boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(entity) && entity.isInvisible();
			if (!entity.isInvisible() || flag) {
				pMatrixStack.pushPose();
				this.getParentModel().getHead().translateAndRotate(pMatrixStack);
				pMatrixStack.translate(0.0D, -0.34375D, 0.0D);
				pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
				pMatrixStack.scale(0.625F, -0.625F, -0.625F);
				ItemStack itemstack = new ItemStack(Items.CARVED_PUMPKIN);
				if (flag) {
					BlockState blockstate = Blocks.CARVED_PUMPKIN.defaultBlockState();
					BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
					int i = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
					pMatrixStack.translate(-0.5D, -0.5D, -0.5D);
					this.blockRenderer.getModelRenderer().renderModel(pMatrixStack.last(), pBuffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockstate, bakedmodel, 0.0F, 0.0F, 0.0F, pPackedLight, i);
				} else {
					this.itemRenderer.renderStatic(entity, itemstack, ItemTransforms.TransformType.HEAD, false, pMatrixStack, pBuffer, entity.level, pPackedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), entity.getId());
				}
				pMatrixStack.popPose();
			}
		}
		if(((SnowGolemEntity)entity).hasJackOPumpkin()) {
			boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(entity) && entity.isInvisible();
			if (!entity.isInvisible() || flag) {
				pMatrixStack.pushPose();
				this.getParentModel().getHead().translateAndRotate(pMatrixStack);
				pMatrixStack.translate(0.0D, -0.34375D, 0.0D);
				pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
				pMatrixStack.scale(0.625F, -0.625F, -0.625F);
				ItemStack itemstack = new ItemStack(Items.JACK_O_LANTERN);
				if (flag) {
					BlockState blockstate = Blocks.JACK_O_LANTERN.defaultBlockState();
					BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
					int i = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
					pMatrixStack.translate(-0.5D, -0.5D, -0.5D);
					this.blockRenderer.getModelRenderer().renderModel(pMatrixStack.last(), pBuffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockstate, bakedmodel, 0.0F, 0.0F, 0.0F, pPackedLight, i);
				} else {
					this.itemRenderer.renderStatic(entity, itemstack, ItemTransforms.TransformType.HEAD, false, pMatrixStack, pBuffer, entity.level, pPackedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), entity.getId());
				}
				pMatrixStack.popPose();
			}
		}
		if(((SnowGolemEntity)entity).hasJackOSoulPumpkin()) {
			boolean flag = Minecraft.getInstance().shouldEntityAppearGlowing(entity) && entity.isInvisible();
			if (!entity.isInvisible() || flag) {
				pMatrixStack.pushPose();
				this.getParentModel().getHead().translateAndRotate(pMatrixStack);
				pMatrixStack.translate(0.0D, -0.34375D, 0.0D);
				pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
				pMatrixStack.scale(0.625F, -0.625F, -0.625F);
				ItemStack itemstack = new ItemStack(BlockRegistry.JACK_O_SOUL_LANTERN.get());
				if (flag) {
					BlockState blockstate = BlockRegistry.JACK_O_SOUL_LANTERN.get().defaultBlockState();
					BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
					int i = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
					pMatrixStack.translate(-0.5D, -0.5D, -0.5D);
					this.blockRenderer.getModelRenderer().renderModel(pMatrixStack.last(), pBuffer.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockstate, bakedmodel, 0.0F, 0.0F, 0.0F, pPackedLight, i);
				} else {
					this.itemRenderer.renderStatic(entity, itemstack, ItemTransforms.TransformType.HEAD, false, pMatrixStack, pBuffer, entity.level, pPackedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), entity.getId());
				}
				pMatrixStack.popPose();
			}
		}
	}
}