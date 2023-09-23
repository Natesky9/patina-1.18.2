package com.natesky9.patina.Item.flasks;

import com.natesky9.patina.init.ModItems;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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
                List<MobEffectInstance> effects = PotionUtils.getMobEffects(stack);
                boolean flag = false;
                for (MobEffectInstance instance:effects)
                {
                    MobEffect effect = instance.getEffect();
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
