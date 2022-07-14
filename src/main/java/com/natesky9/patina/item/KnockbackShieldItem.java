package com.natesky9.patina.item;

import com.natesky9.patina.Patina;
import com.natesky9.patina.entity.MiscModels.BEWLR;
import com.natesky9.patina.entity.MiscModels.KnockbackShield;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class KnockbackShieldItem extends ShieldItem {
    public KnockbackShieldItem(Properties p_43089_) {
        super(p_43089_);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return Patina.bewlr;
            }
        });
    }

    public static void activate(Entity blocker, ItemStack itemstack, DamageSource damageSource)
    {
        if (damageSource instanceof EntityDamageSource)
        {
            //the attacker, the one doing damage
            Entity attackerEntity = damageSource.getEntity();
            if (damageSource instanceof IndirectEntityDamageSource)
            {
                //if this is a projectile
                Projectile projectileEntity = (Projectile) damageSource.getDirectEntity();
                //bounce it where the player is looking
                projectileEntity.shootFromRotation(blocker,blocker.getYRot(),blocker.getXRot(),0,3,1);
                projectileEntity.setOwner(blocker);
                //projectileEntity.setDeltaMovement(blocker.getLookAngle().scale(2));
                System.out.println("We reflected an arrow");
                return;
            }
            //if it's an entity, knock it back instead

            System.out.println("We reflected a mob");
            attackerEntity.setDeltaMovement(blocker.getLookAngle().scale(2));
            blocker.playSound(SoundEvents.PISTON_EXTEND,1F,.5F+(float)Math.random()/2F);
        }
        //do normal block stuff
    }

}
