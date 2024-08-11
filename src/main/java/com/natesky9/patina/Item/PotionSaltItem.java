package com.natesky9.patina.Item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;

public class PotionSaltItem extends Item {
    public PotionSaltItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        PotionContents contents = pStack.get(DataComponents.POTION_CONTENTS);
        if (contents != null)
            pTooltipComponents.add(Component.literal(contents.potion().get().get().toString()));
        //pTooltipComponents.add(Component.literal(potion.getName("")));
    }
}
