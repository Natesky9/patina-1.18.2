package com.natesky9.patina;

import com.natesky9.patina.init.ModItems;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.awt.*;
import java.util.Map;
import java.util.function.Predicate;

public class PatinaArchery {
    //TODO remove this in favor of the recipe json
    public static final Predicate<ItemStack> BOLTS = (item) -> {return item.is(ModItems.BOLTS.get());};
    public static final Predicate<ItemStack> HAS_FEATHER = (item) -> {return item.getOrCreateTag().contains("feather");};
    public static final Predicate<ItemStack> HAS_TIP = (item) -> {return item.getOrCreateTag().contains("gem");};
    public enum Limbs {
        WOOD,
        COPPER,
        IRON,
        GOLD,
        METAL1,
        NETHERITE,
        METAL2
    }
    public enum Stocks {
        BIRCH,
        OAK,
        DARK_OAK,
        SPRUCE,
        ACACIA,
        JUNGLE,
        CRIMSON,
        WARPED,
        MANGROVE
    }

    public class Tiers {
        public static final Map<Item, Limbs> limbs = Map.of
                (
                        Items.OAK_WOOD, Limbs.WOOD,
                        Items.IRON_INGOT, Limbs.IRON,
                        Items.GOLD_INGOT, Limbs.GOLD,
                        ModItems.INGOT_1.get(), Limbs.METAL1,
                        Items.NETHERITE_INGOT, Limbs.NETHERITE,
                        ModItems.INGOT_2.get(), Limbs.METAL2
                );
        public static final Map<Item, Stocks> stocks = Map.of
                (
                        Items.STRIPPED_BIRCH_LOG, Stocks.BIRCH,
                        Items.STRIPPED_OAK_LOG, Stocks.OAK,
                        Items.STRIPPED_DARK_OAK_LOG, Stocks.DARK_OAK,
                        Items.STRIPPED_SPRUCE_LOG, Stocks.SPRUCE,
                        Items.STRIPPED_ACACIA_LOG, Stocks.ACACIA,
                        Items.STRIPPED_JUNGLE_LOG, Stocks.JUNGLE,
                        Items.STRIPPED_CRIMSON_STEM, Stocks.CRIMSON,
                        Items.STRIPPED_WARPED_STEM, Stocks.WARPED
                );
        public static int getLimbTier(ItemStack item)
        {
            //change this to use the nbt of my item
            Limbs limb = limbs.get(item.getItem());
            return limb != null ? limb.ordinal():0;
        }
        public static int getStockTier(ItemStack item)
        {
            Stocks stock = stocks.get(item.getItem());
            return stock != null ? stock.ordinal():0;
        }
    }
    //colors
    public static int materialColor(Item getItem)
    {
        //TODO don't use awt color, use ___ instead
        if (getItem == Items.IRON_INGOT) return Color.gray.getRGB();
        if (getItem == Items.GOLD_INGOT) return Color.yellow.getRGB();
        if (getItem == Items.COPPER_INGOT) return Color.green.getRGB();
        if (getItem == ModItems.INGOT_1.get()) return Color.ORANGE.getRGB();
        if (getItem == ModItems.INGOT_2.get()) return Color.BLUE.getRGB();

        if (getItem == Items.EMERALD)
            return Color.green.getRGB();
        if (getItem == Items.DIAMOND)
            return Color.CYAN.getRGB();
        if (getItem == ModItems.RUBY.get())
            return Color.RED.getRGB();

        if (getItem == Items.FEATHER) return Color.WHITE.getRGB();

        //if it matches nothing at all
        return Color.MAGENTA.getRGB();
    }
}
