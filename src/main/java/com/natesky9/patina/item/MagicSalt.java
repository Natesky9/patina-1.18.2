package com.natesky9.patina.item;

import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicSalt extends Item {
    public MagicSalt(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Potion effect = PotionUtils.getPotion(pStack);
        pTooltipComponents.add(new TextComponent(effect.getName("Effect: ")));
    }

    @Override
    public void fillItemCategory(CreativeModeTab Category, NonNullList<ItemStack> pItems) {
        if (this.allowdedIn(Category)) {
            for(Potion potion : Registry.POTION) {
                if (potion != Potions.EMPTY) {
                    pItems.add(PotionUtils.setPotion(new ItemStack(this), potion));
                }
            }
        }
    }
}
