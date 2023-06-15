package com.natesky9.patina.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    //dig level, uses, speed, damage, enchantment, repair
    public static final ForgeTier COPPER = new ForgeTier(1,200,5F,1.5F,12,
            BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier BOSS = new ForgeTier(3, 1000, 7.0F, 2.5F, 20,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.NETHER_STAR));
}
