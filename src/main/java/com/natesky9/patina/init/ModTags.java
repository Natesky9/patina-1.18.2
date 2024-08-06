package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;


public interface ModTags {
    TagKey<Enchantment> CURSE_EXCLUSIVE = tagEnchantment("exclusive_set/curse");


    TagKey<Item> GRAIN = tag("grain");

    TagKey<Item> MEAT = tag("meat");

    TagKey<Item> FRUIT = tag("fruit");

    TagKey<Item> VEGETABLE = tag("vegetable");

    TagKey<Item> SWEET = tag("sweet");
    TagKey<Block> CALCIUM_BLOCK = tagBlock("calcium_block");
    TagKey<Item> CALCIUM = tag("calcium");
    //older code

    private static TagKey<Block> tagBlock(String name)
    {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Patina.MODID, name));
    }
    private static TagKey<Item> tag(String name)
    {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Patina.MODID, name));
    }
    private static TagKey<Enchantment> tagEnchantment(String name)
    {
        return EnchantmentTags.create(ResourceLocation.fromNamespaceAndPath(Patina.MODID, name));
    }
}
