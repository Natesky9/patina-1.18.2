package com.natesky9.patina.init;

import com.natesky9.patina.init.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier Custom = new ForgeTier(2, 1000,3f,2,8,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.CUSTOM_INGOT.get()));
}
