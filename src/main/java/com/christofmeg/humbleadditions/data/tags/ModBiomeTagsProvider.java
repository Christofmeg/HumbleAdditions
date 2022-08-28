package com.christofmeg.humbleadditions.data.tags;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBiomeTagsProvider extends BiomeTagsProvider  {
	
	public ModBiomeTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, ModConstants.MOD_ID, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - Biome Tags";
	}
	
	@Override
	public void addTags() {
		
/*		this.tag(TagRegistry.Biomes.RED_HUSK_BIOMES).add(
			net.minecraft.world.level.biome.Biomes.BADLANDS,
			Biomes.ERODED_BADLANDS,
			Biomes.WOODED_BADLANDS
			);
*/
	}
}