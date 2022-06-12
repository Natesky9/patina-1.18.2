package com.natesky9.patina.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class FirePipeItem extends Item {
    public FirePipeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide()) return InteractionResultHolder.sidedSuccess(itemStack,true);
        SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL,pLevel)
        {
            @Override
            protected float getInertia() {
                return 1;
            }

            @Override
            protected void onHitEntity(EntityHitResult pResult) {
                super.onHitEntity(pResult);
            }
        };
        fireball.setPos(pPlayer.getX(),pPlayer.getEyeY(),pPlayer.getZ());
        fireball.shootFromRotation(pPlayer,pPlayer.getXRot(),pPlayer.getYRot(),0,1,1);
        pLevel.addFreshEntity(fireball);
        pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(),
                SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL,1,1);
        return InteractionResultHolder.success(itemStack);
    }
}
