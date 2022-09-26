package com.christofmeg.humbleadditions.data.tags;

import org.jetbrains.annotations.Nullable;

import com.christofmeg.humbleadditions.registry.TagRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBiomeTagsProvider extends BiomeTagsProvider  {
	
	public ModBiomeTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
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
		this.tag(TagRegistry.Biomes.IS_MANGROVE_SWAMP).add(Biomes.MANGROVE_SWAMP);
		
		
	}
	
}