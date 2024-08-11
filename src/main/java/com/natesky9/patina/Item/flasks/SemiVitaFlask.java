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

public class SemiVitaFlask extends PotionFlaskItem{
    public SemiVitaFlask(Properties pProperties) {
        super(pProperties);
    }
    public static void trigger(LivingDamageEvent event)
    {

        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.getHealth() <= player.getMaxHealth())) return;
        if (player.getInventory().hasAnyMatching(itemstack -> itemstack.is(ModItems.VITA_FLASK.get())
                && getUses(itemstack) > 0))
        {
            for (ItemStack stack:player.getInventory().items)
            {
                if (!stack.is(ModItems.VITA_FLASK.get())) continue;
                if (getUses(stack) <= 0) continue;
                Iterable<MobEffectInstance> effects = stack.get(DataComponents.POTION_CONTENTS).getAllEffects();
                boolean has = false;
                boolean instant = false;
                for (MobEffectInstance instance:effects)
                {
                    Holder<MobEffect> effect = instance.getEffect();
                    if (player.hasEffect(effect)) {
                        has = true;
                    }
                    if (effect.get().isInstantenous()) {
                        instant = true;
                    }
                }
                //applies if it doesn't have effect but also allows instants
                if (!has || instant) {
                    //apply all flasks if effect doesn't exist
                    apply(player, stack);
                    setUses(stack, getUses(stack) - 1);

                    player.awardStat(Stats.ITEM_USED.get(ModItems.VITA_FLASK.get()));

                    return;
                }
            }
        }
    }
}
