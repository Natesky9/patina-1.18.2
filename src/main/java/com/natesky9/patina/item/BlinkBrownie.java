package com.natesky9.patina.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BlinkBrownie extends Item {
    public BlinkBrownie(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        //put brownies on cooldown
        if (entity instanceof Player) {
            ((Player)entity).getCooldowns().addCooldown(this, 60);
        }

        HitResult result = entity.pick(16,0,false);
        //HitResult liquid = entity.pick(16,0,true);
        if (result.getType() == HitResult.Type.ENTITY)
        {
            //swap positions
            Entity entityHit = ((EntityHitResult)result).getEntity();
            double entityHitX = entityHit.getX();
            double entityHitY = entityHit.getY();
            double entityHitZ = entityHit.getZ();

            entityHit.teleportTo(entity.getX(),entity.getY(),entity.getZ());
            entity.teleportTo(entityHitX,entityHitY,entityHitZ);
            return super.finishUsingItem(itemStack, level, entity);
        }

        if (result.getType() == HitResult.Type.BLOCK)
        {
            Vec3 blockpos = result.getLocation();
            double blockX = blockpos.x;
            double blockY = blockpos.y;
            double blockZ = blockpos.z;
            entity.teleportTo(blockX,blockY,blockZ);
            return super.finishUsingItem(itemStack, level, entity);
        }
        //if nothing happened
        return super.finishUsingItem(itemStack, level, entity);
    }
}
