package com.christofmeg.humbleadditions.data.loot;

import java.util.HashSet;
import java.util.Set;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModEntityLootTables extends net.minecraft.data.loot.EntityLoot {

	private final Set<EntityType<?>> knownEntities = new HashSet<>();

	protected static final EntityPredicate.Builder ENTITY_IS_ADULT = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(false).build());
	protected static final EntityPredicate.Builder ENTITY_IS_BABY = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true).build());
	
	@Override
	protected void add(EntityType<?> entity, LootTable.Builder builder) {
		super.add(entity, builder);
		knownEntities.add(entity);
	}
	
	@Override
	protected void add(ResourceLocation id, LootTable.Builder table) {
		super.add(id, table);
	}
	
	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return knownEntities;
	}
	
	@Override
    protected void addTables() {
		
		this.add(EntityType.HUSK, LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_ADULT))
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SAND)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
						.apply(SmeltItemFunction.smelted()
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
							.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
						.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
			.withPool(LootPool.lootPool()
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_BABY))
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(BlockRegistry.SAND_LAYER_BLOCK.get())
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 8.0F)))
							.apply(SmeltItemFunction.smelted()
								.when(LootItemKilledByPlayerCondition.killedByPlayer())
								.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
							.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
		);
		
		this.add(EntityRegistry.RED_HUSK.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.IRON_INGOT))
				.add(LootItem.lootTableItem(Items.CARROT))
				.add(LootItem.lootTableItem(Items.POTATO)
					.apply(SmeltItemFunction.smelted()
						.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F)))
			.withPool(LootPool.lootPool()
				.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_ADULT))
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.RED_SAND)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
						.apply(SmeltItemFunction.smelted()
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
							.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
						.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
			.withPool(LootPool.lootPool()
				.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_BABY))
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(BlockRegistry.RED_SAND_LAYER_BLOCK.get())
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 8.0F)))
						.apply(SmeltItemFunction.smelted()
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
							.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
						.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
		);
		
		this.add(EntityType.ENDER_DRAGON, LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(0.02F))
					.add(LootItem.lootTableItem(Items.DRAGON_HEAD))
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())))
					.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
		);
		
		this.add(EntityType.ZOMBIE_VILLAGER, LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(0.3F))
					.add(LootItem.lootTableItem(Items.EMERALD))
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
						.when(LootItemKilledByPlayerCondition.killedByPlayer()))
					.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
						.when(this.killedByWolf())))
					.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.025F, 1.0F)))
		);
		
		
    }
	
	private LootItemCondition.Builder killedByWolf() {
	      return DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().source(EntityPredicate.Builder.entity().of(EntityType.WOLF)));
	   }
	
	
}
