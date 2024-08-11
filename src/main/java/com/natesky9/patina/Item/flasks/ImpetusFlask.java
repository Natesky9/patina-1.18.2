package com.natesky9.patina.Item.flasks;

import com.natesky9.patina.init.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ImpetusFlask extends PotionFlaskItem{
    public ImpetusFlask(Properties pProperties) {
        super(pProperties);
    }
    public static void trigger(LivingDamageEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        boolean has = player.getInventory().hasAnyMatching(itemstack -> itemstack.is(ModItems.IMPETUS_FLASK.get()));
        if (player.getInventory().hasAnyMatching(itemstack ->
                itemstack.is(ModItems.IMPETUS_FLASK.get()) && PotionFlaskItem.getUses(itemstack) > 0))
        {
            for (ItemStack stack:player.getInventory().items)
            {
                if (!stack.is(ModItems.IMPETUS_FLASK.get())) continue;
                if (getUses(stack) <=0) continue;
                Iterable<MobEffectInstance> effects = stack.get(DataComponents.POTION_CONTENTS).getAllEffects();
                boolean flag = false;
                for (MobEffectInstance instance:effects)
                {
                    Holder<MobEffect> effect = instance.getEffect();
                    if (player.hasEffect(effect))
                        flag = true;
                }
                if (flag) continue;
                //apply all flasks if effect doesn't exist
                apply(player,stack);
                setUses(stack,getUses(stack)-1);

                player.awardStat(Stats.ITEM_USED.get(ModItems.IMPETUS_FLASK.get()));

                return;
            }
        }
    }
}
