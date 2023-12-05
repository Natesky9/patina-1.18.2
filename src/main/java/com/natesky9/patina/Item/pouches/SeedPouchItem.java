package com.natesky9.patina.Item.pouches;

import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

public class SeedPouchItem extends PouchItem{
    private final int WHEAT_SEED = Mth.color(50f/255f,205f/255f,50f/255f);
    private final int COCOA_SEED = Mth.color(150f/255f,75f/255f,0);
    private final int BEETROOT_SEED = Mth.color(245f/255f,245f/255f,220f/255f);
    private final int PUMPKIN_SEED = Mth.color(210f/255f,180f/255f,140f/255f);
    private final int MELON_SEED = Mth.color(97f/255f,64f/255f,81f/255f);
    private final int NETHERWART = Mth.color(128f/255f,0,32f/255f);
    public SeedPouchItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    boolean canAccept(ItemStack self, ItemStack other) {
        return other.is(Tags.Items.SEEDS);
    }

    @Override
    int maxCount() {
        return 640;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        Item item = getItem(stack);
        if (item == Items.WHEAT_SEEDS) return WHEAT_SEED;
        if (item == Items.COCOA_BEANS) return COCOA_SEED;
        if (item == Items.BEETROOT_SEEDS) return BEETROOT_SEED;
        if (item == Items.PUMPKIN_SEEDS) return PUMPKIN_SEED;
        if (item == Items.MELON_SEEDS) return MELON_SEED;
        if (item == Items.NETHER_WART) return NETHERWART;

        return Mth.color(1,1,1);
    }
}
