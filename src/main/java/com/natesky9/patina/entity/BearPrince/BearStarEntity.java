package com.natesky9.patina.entity.BearPrince;

import com.natesky9.patina.init.ModEntityTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BearStarEntity extends Fireball {
    ItemStack star = new ItemStack(Items.NETHER_STAR);
    int lifetime;

    public BearStarEntity(EntityType<? extends BearStarEntity> p_37006_, Level p_37007_)
    {
        super(p_37006_, p_37007_);
        lifetime = 0;
    }


    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        Vec3 vec31 = vec3.normalize().scale((double)0.05F);
        this.setDeltaMovement(vec31.multiply(-1,-1,-1));
        //set to just before the collision
        this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.setOnGround(true);
        //play the plop sound
        this.level().playSound(null,this.blockPosition(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.WEATHER,1,1);

    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity entity = p_37259_.getEntity();
        entity.hurt(entity.damageSources().indirectMagic(this,entity),8);
        if (this.level() instanceof ServerLevel level)
            level.sendParticles(ParticleTypes.POOF,getX(),getY(),getZ(),1,0,0,0,0);
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return !this.ownedBy(entity) && entity.getType() != this.getType();
    }

    @Override
    protected float getInertia() {
        return 0.95f;
    }

    @Override
    public boolean hurt(DamageSource p_36839_, float p_36840_) {
        lifetime = 0;

        Entity entity = p_36839_.getEntity();
        if (entity != null) {
            if (!this.level().isClientSide) {
                Vec3 vec3 = entity.getLookAngle().multiply(.5, -0.1, .5);
                this.setDeltaMovement(vec3);
                //this.xPower = vec3.x * 0.1D;
                //this.yPower = vec3.y * 0.1D;
                //this.zPower = vec3.z * 0.1D;
                this.setOwner(entity);
            }
            this.setOwner(entity);
            return true;
        }
        return false;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return star;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.ELECTRIC_SPARK;
    }

    @Override
    public void tick() {
        super.tick();
        lifetime++;
        if (lifetime > 160)
            discard();
        //gravity
        if (!this.onGround())
            this.setDeltaMovement(this.getDeltaMovement().add(0,-0.02,0));
        if (lifetime % 5 == 0)
        {
            List<Player> entities = this.level().getEntitiesOfClass(Player.class,getBoundingBox());
            for (Player player:entities)
            {
                player.hurt(this.damageSources().inFire(),2);
            }
        }
    }
}
