package com.natesky9.patina.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

public class BlinkBrownie extends Item {
    int blinkDistance = 16;
    public BlinkBrownie(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        //put brownies on cooldown
        if (entity instanceof Player player)
        {
            level.playSound(null,player, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.AMBIENT,
                    1F,1F);
            player.getCooldowns().addCooldown(this, 20);
        }
        //hit entities and swap
        Vec3 start = entity.getEyePosition();
        Vec3 end = start.add(entity.getLookAngle().scale(blinkDistance));
        AABB aabb = entity.getBoundingBox().expandTowards(entity.getLookAngle().scale(blinkDistance)).inflate(1);
        EntityHitResult entityresult = ProjectileUtil.getEntityHitResult(entity,start,end,
                aabb,Entity::isAttackable,blinkDistance*blinkDistance);
        if (entityresult != null)
        {
            //swap positions
            Entity entityHit = entityresult.getEntity();
            System.out.println("stored position");
            Vec3 storePos = entity.position();
            System.out.println("swapped");
            entity.setPos(entityHit.position());
            entityHit.setPos(storePos);
            return super.finishUsingItem(itemStack, level, entity);
        }
        //TODO:fix this if broken
        HitResult blockResult = level.clip(new ClipContext(entity.getEyePosition(),entity.position().add(entity.getLookAngle().normalize().scale(blinkDistance)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,entity));

        if (blockResult.getType() == HitResult.Type.BLOCK)
        {
            Vec3 blockpos = blockResult.getLocation();
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
