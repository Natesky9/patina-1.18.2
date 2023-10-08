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
    public static class Items
    {
        public static final TagKey<Item> GRAIN = tag("grain");
        public static final TagKey<Item> MEAT = tag("meat");
        public static final TagKey<Item> FRUIT = tag("fruit");
        public static final TagKey<Item> VEGETABLE = tag("vegetable");
        public static final TagKey<Item> SWEET = tag("sweet");

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
