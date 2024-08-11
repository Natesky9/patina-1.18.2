package com.natesky9.patina.Item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;

public class PigCrossbowItem extends CrossbowItem {
    public PigCrossbowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        ChargedProjectiles projectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
        if (isCharged(itemstack)) {
            performShooting(pLevel, pPlayer, pHand, itemstack, getShootingPower(projectiles), 1.0F, null);
            //setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!pPlayer.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                //this.startSoundPlayed = false; this.midLoadSoundPlayed = false;
                pPlayer.startUsingItem(pHand);
            }
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    static float getShootingPower(ChargedProjectiles projectiles)
    {
        return projectiles.contains(Items.FIREWORK_ROCKET)? 2.4F : 3.8F;
        //return containsChargedProjectile(itemstack, Items.FIREWORK_ROCKET) ? 2.4F : 3.8F;
    }

}
