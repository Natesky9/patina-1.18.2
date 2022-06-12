package com.natesky9.patina.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.natesky9.patina.CustomAttributes;
import com.natesky9.patina.init.ModAttributes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class WitherScepterItem extends Item
{
    private ImmutableMultimap<Attribute,AttributeModifier> baseAttributes;
    public WitherScepterItem(Properties pProperties) {
        super(pProperties);
        this.baseAttributes = null;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (this.baseAttributes == null)
        {
        UUID uuid = CustomAttributes.makeUUID(ModAttributes.MAGIC_DAMAGE.get(),EquipmentSlot.MAINHAND, AttributeModifier.Operation.ADDITION);
        AttributeModifier attribute = new AttributeModifier(uuid,"magic damage",2, AttributeModifier.Operation.ADDITION);
        this.baseAttributes = ImmutableMultimap.of(ModAttributes.MAGIC_DAMAGE.get(),attribute);
        }
        return this.baseAttributes;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) return InteractionResultHolder.success(itemStack);
        WitherSkull skull = new WitherSkull(EntityType.WITHER_SKULL,pLevel)
        {
            @Override
            public boolean isDangerous() {
                return false;
            }

            @Override
            protected void onHit(HitResult pResult) {
                HitResult.Type resultType = pResult.getType();
                if (pResult.getType() == HitResult.Type.ENTITY) onHitEntity((EntityHitResult) pResult);
                if (pResult.getType() == HitResult.Type.BLOCK) onHitBlock((BlockHitResult) pResult);
                Vec3 location = pResult.getLocation();
                for (int i = 0; i < 8; i++)
                {
                    //TODO: figure out how to add particles here
                    pLevel.addParticle(ParticleTypes.SMOKE,location.x,location.y,location.z,0,0,0);
                            //Math.random() - .5, Math.random() - .5, Math.random() - .5);
                    System.out.println(location.x+":"+location.y+":"+location.z);
                }
                this.discard();
            }

            @Override
            protected float getInertia() {
                return 1f;
            }
        };
        skull.setPos(pPlayer.getX(),pPlayer.getEyeY(),pPlayer.getZ());
        skull.setYRot(pPlayer.getYRot());
        skull.shootFromRotation(pPlayer,pPlayer.getXRot(),pPlayer.getYRot(),0,1,1);
        pLevel.addFreshEntity(skull);
        pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(),
                SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS,1,1);
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPYGLASS;
    }
}
