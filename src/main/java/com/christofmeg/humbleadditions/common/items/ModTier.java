package com.christofmeg.humbleadditions.common.items;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public final class ModTier implements Tier
{
    private final int level;
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final String name;
    @NotNull
    private final TagKey<Block> tag;
    @NotNull
    private final Supplier<Ingredient> repairIngredient;

    public ModTier(String name, int level, int uses, float speed, float attackDamageBonus, int enchantmentValue, @NotNull TagKey<Block> tag, @NotNull Supplier<Ingredient> repairIngredient)
    {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.tag = tag;
        this.repairIngredient = repairIngredient;
        this.name = name;
    }

    @Override
    public int getUses()
    {
        return this.uses;
    }

    @Override
    public float getSpeed()
    {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus()
    {
        return this.attackDamageBonus;
    }

    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    @NotNull
    @Override
    public TagKey<Block> getTag()
    {
        return this.tag;
    }

    @NotNull
    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    @NotNull
    public String getName()
    {
        return this.name;
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    
}
