package com.natesky9.patina.item;

import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltTipItem extends ToolItem {
    public BoltTipItem(Properties pProperties) {
        super(pProperties);
    }
    //----------//
    @Override
    public Component getName(ItemStack stack) {
        //replace the name with what's in the nbt
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);

        Item gem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("gem")));
        return new TranslatableComponent("component.patina.bolt_tips_name",
                gem.getName(new ItemStack(gem)).getString());
    }

    public static int getColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("gem color");
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 5;
    }

    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.COMBINING_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.CROSSBOW_SHOOT;
    }

    @Override
    boolean shrinkThis() {
        return true;
    }

    @Override
    boolean shrinkThat() {
        return true;
    }
    //----------//
}
