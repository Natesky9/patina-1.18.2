package com.natesky9.patina.Item;

import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import java.awt.*;
import java.util.Optional;

public class BeeShieldItem extends ShieldItem {
    public BeeShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        Optional<Entity> entity = DebugRenderer.getTargetedEntity(pPlayer,8);

        if (entity.isPresent())
        {
            if (entity.get() instanceof Bee bee)
            {
                ItemStack stack = pPlayer.getItemInHand(pHand);
                int stored = stack.get(DataComponents.DAMAGE);
                //int stored = stack.getOrCreateTag().getInt("stored");
                if (stored < 8) {
                    stack.set(DataComponents.DAMAGE,stored+1);
                    //stack.getOrCreateTag().putInt("stored",stored+1);
                    pLevel.playSound(null, bee.blockPosition(), SoundEvents.BEEHIVE_ENTER, SoundSource.PLAYERS);
                    bee.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        }
        return super.use(pLevel, pPlayer, pHand);
    }
    public static void trigger(ShieldBlockEvent event)
    {
        Player player = (Player)event.getEntity();
        if (!(player.level() instanceof ServerLevel level)) return;
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        Entity entity = event.getDamageSource().getEntity();
        if (entity == null) return;

        //int stored = stack.getOrCreateTag().getInt("stored");
        int stored = stack.get(DataComponents.DAMAGE);
        if (stored >= 1)
        {
            stack.set(DataComponents.DAMAGE,stored-1);
            //stack.getOrCreateTag().putInt("stored",stored-1);
            Bee bee = EntityType.BEE.spawn(level,player.blockPosition(), MobSpawnType.REINFORCEMENT);
            bee.setPersistentAngerTarget(entity.getUUID());
            bee.setAggressive(true);
            bee.startPersistentAngerTimer();
            bee.moveRelative(1,player.getLookAngle());
            //add a return to shield goal
        }
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        //int stored = pStack.getOrCreateTag().getInt("stored");
        //TODO: replace with BEEEEEEEEEEEEEEES
        if (!pStack.getComponents().has(DataComponents.DAMAGE)) return 0;
        int stored = pStack.get(DataComponents.DAMAGE);
        return stored*13/8;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Color.YELLOW.getRGB();
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }
}
