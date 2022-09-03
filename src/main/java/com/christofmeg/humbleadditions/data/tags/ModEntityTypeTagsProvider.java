package com.christofmeg.humbleadditions.data.tags;

import com.christofmeg.humbleadditions.registry.TagRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider  {
	
	public ModEntityTypeTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, ModConstants.MOD_ID, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Entity Type Tags";
	}
	
	@Override
	public void addTags() {
		
		this.tag(TagRegistry.EntityTypes.QUICK_SAND_WALKABLE_MOBS).add(EntityType.RABBIT);
		
	}
}