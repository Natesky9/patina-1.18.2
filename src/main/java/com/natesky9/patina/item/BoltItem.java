package com.natesky9.patina.item;

import com.natesky9.patina.PatinaArchery;
import com.natesky9.patina.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class BoltItem extends ArrowItem {
    public BoltItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 100;
    }

    //should always have a base, duh
    public static int getBase(ItemStack stack)
    {
        int color = stack.getOrCreateTag().getInt("metal color");
        return color;
        //return PatinaArchery.materialColor(getItem);
    }
    public static int getTip(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("gem color");
    }
    public static int getFeather(ItemStack stack)
    {
        //replace this when new feathers are added
        return 16777215;
    }

    @Override
    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);
        //remove ingot from the name
        String base = tag.getString("metal");
        String gem = tag.getString("gem");

        Item baseItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(base));
        Item gemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(gem));


        if (tag.contains("gem"))
            return new TranslatableComponent("component.patina.tipped_bolt_name",
                gemItem.getName(new ItemStack(gemItem)),
                baseItem.getName(new ItemStack(baseItem)).getString().replace(" Ingot",""));
        return new TranslatableComponent("component.patina.bolt_name",
                baseItem.getName(new ItemStack(baseItem)).getString().replace(" Ingot",""));
    }
}
