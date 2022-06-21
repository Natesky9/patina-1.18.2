package com.natesky9.patina.item;

import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
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
        Potion potion = PotionUtils.getPotion(pStack);
        if (potion.getEffects().size() == 0) return;
        pTooltipComponents.add(new TextComponent(potion.getName("")));
    }

    @Override
    public void fillItemCategory(CreativeModeTab Category, NonNullList<ItemStack> pItems) {
        if (this.allowdedIn(Category)) {

            for(Potion potion : Registry.POTION) {
                if (potion.getEffects().isEmpty() || potion.getEffects().size() > 1) continue;
                if (potion.getEffects().get(0).getAmplifier() >= 1) continue;
                pItems.add(PotionUtils.setPotion(new ItemStack(this), potion));
            }
            /*
            for (Potion potion: Registry.POTION)
            {
                if (PotionUtils.getPotion(potion).getEffects().stream().anyMatch(potency -> PotionUtils.))
                    potion.getEffects().g
                PotionUtils.getPotion(PotionUtils.setPotion(new ItemStack(Items.POTION),potion));
            }
            */
        }
    }
}
