package com.christofmeg.humbleadditions.data.loot;

import java.util.HashSet;
import java.util.Set;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.google.common.collect.ImmutableSet;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModEntityLootTables extends net.minecraft.data.loot.EntityLoot {

	private final Set<EntityType<?>> knownEntities = new HashSet<>();

	protected static final EntityPredicate.Builder ENTITY_IS_ADULT = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(false).build());
	protected static final EntityPredicate.Builder ENTITY_IS_BABY = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true).build());
	private static final Set<EntityType<?>> SPECIAL_LOOT_TABLE_TYPES = ImmutableSet.of(EntityRegistry.SNOW_GOLEM.get());

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
										.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
				.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
				.withPool(LootPool.lootPool()
						.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_BABY))
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(BlockRegistry.SAND_LAYER_BLOCK.get())
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 8.0F)))
								.apply(SmeltItemFunction.smelted()
										.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
				.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
				);

		this.add(EntityRegistry.RED_HUSK.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 5.0F)))))
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
										.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
				.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
				.withPool(LootPool.lootPool()
						.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_IS_BABY))
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(BlockRegistry.RED_SAND_LAYER_BLOCK.get())
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 8.0F)))
								.apply(SmeltItemFunction.smelted()
										.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))))
				.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
				);

		this.add(EntityType.ENDER_DRAGON, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.DRAGON_HEAD)
								.when(LootItemRandomChanceCondition.randomChance(0.01F))
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer())
										)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))
										.when(this.killedByWolf())
										)
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								)
						)
				);

		this.add(EntityType.ZOMBIE_VILLAGER, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.EMERALD)
								.when(LootItemRandomChanceCondition.randomChance(0.30F))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
										.when(this.killedByWolf()))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								)
						)
				);

		this.add(EntityType.STRAY, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.ICE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(this.killedByWolf()))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								.when(LootItemRandomChanceCondition.randomChance(0.40F))
								.setWeight(8)
								)
						.add(LootItem.lootTableItem(Items.PACKED_ICE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(this.killedByWolf()))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								.when(LootItemRandomChanceCondition.randomChance(0.20F))
								.setWeight(4)
								)
						.add(LootItem.lootTableItem(Items.BLUE_ICE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(this.killedByWolf()))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								.when(LootItemRandomChanceCondition.randomChance(0.10F))
								.setWeight(2)
								)
						.add(LootItem.lootTableItem(BlockRegistry.SMOOTH_ICE.get())
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(LootItemKilledByPlayerCondition.killedByPlayer()))
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))
										.when(this.killedByWolf()))
								.apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.1F, 0.3F)))
								.when(LootItemRandomChanceCondition.randomChance(0.05F))
								.setWeight(1)
								)
						)
				);
		this.add(EntityRegistry.SNOW_GOLEM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(Items.SNOWBALL)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 15.0F)))))
				);


	}

	private LootItemCondition.Builder killedByWolf() {
		return DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().source(EntityPredicate.Builder.entity().of(EntityType.WOLF)));
	}

	@Override
	protected boolean isNonLiving(EntityType<?> entitytype) {
		return !SPECIAL_LOOT_TABLE_TYPES.contains(entitytype) && entitytype.getCategory() == MobCategory.MISC;
	}


}
