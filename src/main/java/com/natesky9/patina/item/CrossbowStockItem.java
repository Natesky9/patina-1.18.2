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

public class CrossbowStockItem extends ToolItem{
    public CrossbowStockItem(Properties pProperties) {
        super(pProperties);
    }

    public static int getColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("wood color");
    }
    @Override
    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);
        if (tag.contains("wood"))
        {
            Item wood = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("wood")));
            return new TranslatableComponent("component.patina.crossbow_stock_name",wood.getName(
                    new ItemStack(wood)).getString()
                    .replace("Stripped ","")
                    .replace(" Log",""));
        }
        return super.getName(stack);
    }
    @Override
    RecipeType<? extends Recipe<SimpleContainer>> getRecipe() {
        return ModRecipeTypes.COMBINING_RECIPE_TYPE.get();
    }

    @Override
    SoundEvent getSound() {
        return SoundEvents.VILLAGER_WORK_TOOLSMITH;
    }

    @Override
    boolean shrinkThis() {
        return true;
    }

    @Override
    boolean shrinkThat() {
        return true;
    }
}
