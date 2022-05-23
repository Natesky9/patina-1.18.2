package com.natesky9.patina.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.IForgeShearable;

import java.util.List;

public class ChargedShearsItems extends ShearsItem {
    public ChargedShearsItems(Properties p_43074_) {
        super(p_43074_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand)
    {
        if (entity instanceof IForgeShearable target)
        {
        java.util.Random random = new java.util.Random();
        float rotate = random.nextFloat()*360;
        //exit if client
        if (entity.level.isClientSide)
        {
            entity.setYRot(rotate);
            entity.yRotO = rotate;
            entity.yBodyRot = rotate;
            entity.yBodyRotO = rotate;
            entity.yHeadRot = rotate;
            entity.yHeadRotO = rotate;
            return InteractionResult.PASS;
        }


            //take the larger of the two?
            //for when the tools can be enchanted
            int fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE,stack);
            int looting = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING,stack);


            BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
            if (target.isShearable(stack, entity.level,pos))
            {
                double getx = entity.getX();
                double gety = entity.getY();
                double getz = entity.getZ();
                //if we can shear it
                List<ItemStack> drops = target.onSheared(playerIn, stack, entity.level,pos,
                        Math.max(fortune,looting));
                drops.forEach(d -> {
                    //randomly spawn each item with velocity
                    ItemEntity item = entity.spawnAtLocation(d, 1.0F);
                    item.setDeltaMovement(item.getDeltaMovement().add((double)((random.nextFloat() - random.nextFloat()) * 0.1F), (double)(random.nextFloat() * 0.05F), (double)((random.nextFloat() - random.nextFloat()) * 0.1F)));
                });
                if (target instanceof Sheep sheep)
                {
                    //sheep.goalSelector.removeAllGoals();
                    //make sheep be sheared multiple times
                    entity.setPos(getx,gety+.25,getz);
                    //rotate randomly
                    entity.setYRot(rotate);
                    entity.yRotO = rotate;
                    entity.yBodyRot = rotate;
                    entity.yBodyRotO = rotate;
                    entity.yHeadRot = rotate;
                    entity.yHeadRotO = rotate;

                    entity.resetFallDistance();
                    if (random.nextFloat() > .1F)
                    sheep.setSheared(false);
                    entity.hurtMarked = true;
                    //10% chance to shear
                }
            }
            else
            {
                //if the sheep has no wool, nick it
                entity.hurt(DamageSource.playerAttack(playerIn),1F);
            }
        }


        //nothing happened
        return InteractionResult.PASS;
    }
}
