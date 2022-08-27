package com.christofmeg.humbleadditions.setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;

public class ModConstants {
	
	public static final String MOD_ID = "humbleadditions";
	public static final String MODID = "humbleadditions";
	public static final String MOD_NAME = "Humble Additions";
	
	public static final Logger log = LogManager.getLogger(ModConstants.MOD_ID);
	
	public static ResourceLocation getId(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

}

