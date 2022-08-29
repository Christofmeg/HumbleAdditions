package com.christofmeg.humbleadditions.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

	public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;
	
    public static ConfigValue<Float> SMOOTH_ICE_FRICTION;
    public static ConfigValue<Integer> MILK_BOTTLE_CURE_CHANCE;
    public static ConfigValue<Integer> NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE;
    
    public static ConfigValue<Integer> CHARGED_CREEPER_SPAWN_CHANCE;
    
 
    
    public static void init() {
    	initServer();
    	initClient();
    	
    	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }
    
    private static void initServer() {
    	ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    	
    	builder.comment("Block settings")
    		.push("smooth_ice");
    			SMOOTH_ICE_FRICTION = builder
	    			.comment("-----------------------------------------------------",
    					"How much friction Smooth Ice should have",
    					"default: friction = 1.0204082")
	    			.define("friction", (float) (1 / 0.98));
    	builder.comment("Item settings")
    		.push("milk_bottle");
		    	MILK_BOTTLE_CURE_CHANCE = builder
		    		.comment("-----------------------------------------------------",
						"What chance Bottle o' Milk should have to remove potion effets",
						"default: chance = 33")
					.defineInRange("chance", 33, 0, 100);
		builder.pop();
			builder
			.push("netherite_horse_armor");
			NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE = builder
	    		.comment("-----------------------------------------------------",
    				"How much knockback resistance Netherite Horse Armor should have",
    				"9 knockback resistance = 90 % resistance to knockback",
    				"Note: Only use whole numbers",
    				"default: knockback resistance = 9")
				.defineInRange("knockback resistance", 9, 1, 10);
		builder.pop();
		
		
		builder.comment("Entity settings")
			.push("charged_creeper_spawn_chance");
				CHARGED_CREEPER_SPAWN_CHANCE = builder
					.comment("-----------------------------------------------------",
						"% Chance for all creepers to spawn as charged",
						"default: chance = 1")
					.defineInRange("chance", 1, 0, 100);
		builder.pop();
    	
    	
    	
    	
		SERVER_CONFIG = builder.build();
    }
    
    private static void initClient() {
//    	ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    	
//    	CLIENT_CONFIG = builder.build();

    }
}
