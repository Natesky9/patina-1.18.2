package com.natesky9.patina.Item;

import com.natesky9.patina.init.ModItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Charms {
    public static void ambushCharm(LivingHurtEvent event)
    {
        if (!(event.getSource().getEntity() instanceof Player player)) return;


        float damage = event.getAmount();
        float height = player.fallDistance;
        if (height < 3) return;

        float chance = player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_AMBUSH.get()))
                ? 1.0F:0.25F;
        event.setAmount(damage + (height-3)*chance);
        //change the fall distance
        player.fallDistance = player.fallDistance*(1-chance);
        LivingEntity entity = event.getEntity();
        entity.level.playSound(null,entity.blockPosition(),SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.PLAYERS,1F,1F);


    }
    public static boolean isPresent(Player player, Item item)
    {

        return true;
    }
}
