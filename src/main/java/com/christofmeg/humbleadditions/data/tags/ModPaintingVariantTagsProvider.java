package com.christofmeg.humbleadditions.data.tags;

import com.christofmeg.humbleadditions.registry.PaintingVariantRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingVariantTagsProvider extends PaintingVariantTagsProvider  {
	
	public ModPaintingVariantTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, ModConstants.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Painting Variant Tags";
	}
	
	@Override
	public void addTags() {
		
		PaintingVariantRegistry.PAINTING_VARIANTS.getEntries().stream().map(RegistryObject::get)
		.filter(painting -> (painting instanceof PaintingVariant))
		.forEach(painting -> {
			this.tag(PaintingVariantTags.PLACEABLE).add(painting);
		});
	}
}