package com.natesky9.patina.Item;

import com.natesky9.patina.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.awt.*;
import java.util.Optional;

public class PigWeaponItem extends SwordItem {
    public PigWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    private static final MobEffectInstance overwhelm = new MobEffectInstance(MobEffects.WEAKNESS,2400,1);

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return toolAction.equals(ToolActions.SWORD_DIG);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack item = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        //pPlayer.getCooldowns().addCooldown(item.getItem(),20);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    public static void trigger(LivingDamageEvent event)
    {
        Player player = (Player)event.getSource().getEntity();
        if (!player.isSprinting()) return;
        event.getEntity().stopRiding();
        event.getEntity().stopUsingItem();
        event.getEntity().addEffect(overwhelm);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (!(pLivingEntity instanceof Player player)) return;
        Optional<Entity> entity = DebugRenderer.getTargetedEntity(pLivingEntity,8);
        if (entity.isPresent())
        {
            Entity target = entity.get();
            player.attack(target);
            player.swing(player.getUsedItemHand());
            float knockup = Math.min(1,player.getTicksUsingItem()/60F);
            target.setDeltaMovement(0,.25+knockup,0);
            player.getCooldowns().addCooldown(ModItems.PIG_SWORD.get(), 40);
        }
        else
        {
            player.swing(player.getUsedItemHand());
            pLevel.playSound(null,player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS);
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player.getUseItem().sameItem(pStack))
        {
            float duration = Math.min(1,player.getTicksUsingItem()/60F);
            System.out.println("duration: " + duration);
            return (int)(duration*13);
        }
        return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {

        LocalPlayer player = Minecraft.getInstance().player;
        if (player.getUseItem().sameItem(pStack))
            return player.getTicksUsingItem() > 0;
        return false;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Color.RED.getRGB();
    }
}
