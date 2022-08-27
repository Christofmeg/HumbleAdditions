package com.christofmeg.humbleadditions.data.loot;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.christofmeg.humbleadditions.setup.ModConstants;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTableProvider extends LootTableProvider  {

	public ModLootTableProvider(DataGenerator gen) {
		super(gen);
	}
	
	@Override
	public String getName() {
		return ModConstants.MOD_NAME + " - LootTables";
	}
	
	@Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
        	Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK),
      //    Pair.of(ModChestLootTables::new, LootContextParamSets.CHEST),
        	Pair.of(ModEntityLootTables::new, LootContextParamSets.ENTITY)
               
  //        Pair.of(ModGiftLootTables::new, LootContextParamSets.GIFT)
        );
    }

	@Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((id, table) -> LootTables.validate(validationtracker, id, table));
    }

}
