package com.christofmeg.humbleadditions.integration.jeri;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;

import fr.alasdiablo.jerintegration.api.WorldGenIntegration;
import jeresources.api.IWorldGenRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.distributions.DistributionTriangular;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import jeresources.compatibility.api.JERAPI;
import net.minecraft.world.item.ItemStack;

public class ModWorldGen extends WorldGenIntegration {
	@Override
	public void registerWorldGen(IWorldGenRegistry registry) throws IllegalStateException, NoSuchFieldException, NoSuchMethodException, NoClassDefFoundError {
		registry.register(
				new ItemStack(BlockRegistry.ENDORIUM_ORE.get()),
				//veinCount, size, midY, range
				new DistributionTriangular(1, 1, 64, 64),
				new Restriction(DimensionRestriction.END),
				new LootDrop(new ItemStack(ItemRegistry.RAW_ENDORIUM.get()), 1, 4, Conditional.affectedByFortune)
				);
		//		registry.reg
		//	registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.COAL_ORE), new ItemStack(Blocks.DEEPSLATE_COAL_ORE), new DistributionTriangular(20, 16, 96, 96), new Restriction(DimensionRestriction.OVERWORLD), true, new LootDrop(new ItemStack(Items.COAL), 1, 4, Conditional.affectedByFortune)));

	}

	public static class CompatibilityHandler {
		public static void init() {
			var api = JERAPI.getInstance();
			try {
				new ModWorldGen().register(api);
			} catch (Exception e) {

			}
		}
	}


}


