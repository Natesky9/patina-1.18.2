package com.natesky9.patina.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import java.awt.*;
import java.util.List;

public class BeeShieldItem extends ShieldItem {
    public BeeShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        //Optional<Entity> entity = DebugRenderer.getTargetedEntity(pPlayer,8);
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        //clicking on a beehive fully repairs
        if (state.is(Blocks.BEE_NEST) || state.is(Blocks.BEEHIVE))
        {
            Player player = context.getPlayer();
            int value = state.getValue(BeehiveBlock.HONEY_LEVEL);
            if (value < 5) return super.useOn(context);
            //but only if it's full of honey
            ItemStack stack = context.getItemInHand();
            int damage = stack.getDamageValue();
            stack.setDamageValue(Math.max(damage-30,0));
            context.getLevel().setBlock(context.getClickedPos(),state.setValue(BeehiveBlock.HONEY_LEVEL, 0),3);
            state.setValue(BeehiveBlock.HONEY_LEVEL,0);
            //feed and boost the player
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION,20));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,9600));
            context.getLevel().playSound(player,context.getClickedPos(),
                    SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS,1,1);
        }
        return super.useOn(context);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack stack, Slot p_150894_, ClickAction click, Player p_150896_, SlotAccess p_150897_) {
        //early exit if not honeycomb or not damaged
        if (!stack.is(Items.HONEYCOMB) || !self.isDamaged())
            return super.overrideOtherStackedOnMe(self, stack, p_150894_, click, p_150896_, p_150897_);

        int damage = self.getDamageValue();
        while ((self.isDamaged() && !stack.isEmpty()) || click == ClickAction.SECONDARY)
            {//right click for 1, left click for all
                stack.shrink(1);
                stack.setDamageValue(Math.max(damage-10,0));
            }
        return super.overrideOtherStackedOnMe(self, stack, p_150894_, click, p_150896_, p_150897_);
    }

    public static void trigger(ShieldBlockEvent event)
    {
        event.setShieldTakesDamage(false);
        Player player = (Player)event.getEntity();
        if (!(player.level() instanceof ServerLevel level)) return;
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        Entity entity = event.getDamageSource().getEntity();
        if (entity == null) return;

        //int stored = stack.getOrCreateTag().getInt("stored");
        int stored = stack.getDamageValue();
        if (stored < stack.getMaxDamage())
        {
            stack.setDamageValue(Math.min(stored+1,stack.getMaxDamage()));
            List<LivingEntity> list = level.getNearbyEntities(LivingEntity.class,
                    TargetingConditions.forCombat(), player, player.getBoundingBox().inflate(4));
            //slow attackers
            for (LivingEntity enemy:list)
            {
                enemy.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,40,3));
                enemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,40));
            }
            aoe(level,player);
        }
        else
        {//we didn't have a charged shield to block with
            event.setBlockedDamage(event.getOriginalBlockedDamage()*.75f);
        }
    }
    static void aoe(ServerLevel level, Player player)
    {
        for (int x = -4; x < 4;x++)
        {
            for (int z = -4; z < 4; z++)
            {
                level.sendParticles(ParticleTypes.FALLING_HONEY,
                        player.getX()+level.random.nextFloat()+x,
                        player.getY()+.5f,
                        player.getZ()+level.random.nextFloat()+z,
                        1, 0.0, 0.0, 0.0, 0);
            }
        }
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext p_333547_, List<Component> components, TooltipFlag p_43097_) {
        int damage = stack.getDamageValue();
        if (damage >= stack.getMaxDamage())
        {
            components.add(Component.translatable("item.patina.bee_shield_broken").withStyle(ChatFormatting.YELLOW));
            return;
        }
        if (damage > stack.getMaxDamage()/2)
        {
            components.add(Component.translatable("item.patina.bee_shield_cracked").withStyle(ChatFormatting.YELLOW));
            return;
        }
        if (stack.isDamaged())
        {
            components.add(Component.translatable("item.patina.bee_shield_chipped").withStyle(ChatFormatting.YELLOW));
            return;
        }
        components.add(Component.translatable("item.patina.bee_shield_new").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Color.YELLOW.getRGB();
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return pStack.isDamaged();
    }
}
