package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Items
    {
        public static final TagKey<Item> GRAIN = tag("grain");
        public static final TagKey<Item> MEAT = tag("meat");
        public static final TagKey<Item> FRUIT = tag("fruit");
        public static final TagKey<Item> VEGETABLE = tag("vegetable");
        public static final TagKey<Item> SWEET = tag("sweet");

        public static final TagKey<Item> CALCIUM = tag("calcium");

        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(Patina.MODID, name));
        }
    }
    public static class Blocks
    {
        public static final TagKey<Block> CALCIUM_BLOCK = tagBlock("calcium_block");
        
        private static TagKey<Block> tagBlock(String name)
        {
            return BlockTags.create(new ResourceLocation(Patina.MODID, name));
        }
    }
}
