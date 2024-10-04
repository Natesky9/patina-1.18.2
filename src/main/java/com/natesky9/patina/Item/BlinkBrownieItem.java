package com.natesky9.patina.Item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BlinkBrownieItem extends FoodItem{
    public BlinkBrownieItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        //is further if crouching
        int distance = entity.isCrouching() ? 32:16;

        Vec3 start = entity.getEyePosition();
        Vec3 end = start.add(entity.getLookAngle().scale(distance));
        AABB aabb = entity.getBoundingBox().expandTowards(entity.getLookAngle().scale(distance)).inflate(1);
        EntityHitResult result = ProjectileUtil.getEntityHitResult(entity,start,end,aabb, Entity::isAttackable,distance*distance);
        if (result != null)
        {
            //swap positions
            Entity entityHit = result.getEntity();
            System.out.println("stored position");
            Vec3 storePos = entity.position();
            System.out.println("swapped");
            entity.setPos(entityHit.position());
            entityHit.setPos(storePos);
            return super.finishUsingItem(stack, level, entity);
        }
        HitResult blockResult = level.clip(new ClipContext(entity.getEyePosition(),entity.position()
                .add(entity.getLookAngle().normalize().scale(distance)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,entity));

        if (blockResult.getType() == HitResult.Type.BLOCK)
        {
            Vec3 blockpos = blockResult.getLocation();
            entity.teleportTo(blockpos.x,blockpos.y,blockpos.z);
            return super.finishUsingItem(stack, level, entity);
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
