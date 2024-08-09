package com.natesky9.patina.init;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    COPPER(BlockTags.INCORRECT_FOR_GOLD_TOOL,169,4F,1F,5, () -> Ingredient.of(Items.COPPER_INGOT)),
    GLASS(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,4000,9F, 4F, 15, () -> Ingredient.of(ModItems.PRIME_GLASS.get())),
    BRONZE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,9000,10F, 2.5F, 0, () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
    BOSS(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9001, 7F, 2.5F, 20, () -> Ingredient.of(Items.NETHER_STAR));
    //TODO tiers changed in 1.21
    ////dig level, uses, speed, damage, enchantment, repair
    //public static final ForgeTier COPPER = new ForgeTier(1,200,5F,1.5F,12,
    //        BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    //public static final ForgeTier BRONZE = new ForgeTier(3,999,10F,2.5F,0,
    //        BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT.get()));
    //public static final ForgeTier BOSS = new ForgeTier(3, 1000, 7.0F, 2.5F, 20,
    //        BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.NETHER_STAR));
    //public static final ForgeTier GLASS = new ForgeTier(3, 999, 9.0F, 4.0F, 15,
    //        BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.PRIME_GLASS.get()));

    private final TagKey<Block> blockTagKey;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private ModTiers(final TagKey<Block> blockTagKey, final int durability, final float speed,
                     final float damage, final int enchantmentValue, final Supplier<Ingredient> repairIngredient)
    {
        this.blockTagKey = blockTagKey;
        this.uses = durability;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }
    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.blockTagKey;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
