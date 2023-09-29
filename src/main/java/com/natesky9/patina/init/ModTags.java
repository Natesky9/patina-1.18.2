package com.natesky9.patina.init;

import com.ibm.icu.impl.Pair;
import com.natesky9.patina.Patina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ModTags {

    public static final List<Pair<TagKey<Item>,TagKey<Item>>> FOOD_PAIRS = List.of(
            Pair.of(Items.GRAIN, Items.SUPERGRAIN),
            Pair.of(Items.MEAT, Items.SUPERMEAT ),
            Pair.of(Items.FRUIT, Items.SUPERFRUIT),
            Pair.of(Items.VEGETABLE, Items.SUPERVEGETABLE),
            Pair.of(Items.SWEET, Items.SUPERSWEET)
    );
    public static class Items
    {
        public static final TagKey<Item> GRAIN = tag("grain");
        public static final TagKey<Item> MEAT = tag("meat");
        public static final TagKey<Item> FRUIT = tag("fruit");
        public static final TagKey<Item> VEGETABLE = tag("vegetable");
        public static final TagKey<Item> SWEET = tag("sweet");

        public static final TagKey<Item> SUPERGRAIN = tag("super_grain");
        public static final TagKey<Item> SUPERMEAT = tag("super_meat");
        public static final TagKey<Item> SUPERFRUIT = tag("super_fruit");
        public static final TagKey<Item> SUPERVEGETABLE = tag("super_vegetable");
        public static final TagKey<Item> SUPERSWEET = tag("super_sweet");

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(Patina.MODID, name));
        }
        private static TagKey<Item> forgeTag(String name)
        {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
