package com.natesky9.patina.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CrossbowLimbItem extends Item {
    public CrossbowLimbItem(Properties pProperties) {
        super(pProperties);
    }

    public static int getColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("metal color");
    }

    @Override
    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);
        if (tag.contains("metal"))
        {
            Item metal = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("metal")));
            return new TranslatableComponent("component.patina.crossbow_limb_name",metal.getName(
                    new ItemStack(metal)).getString().replace("Block of ",""));
        }
        return super.getName(stack);
    }
}
