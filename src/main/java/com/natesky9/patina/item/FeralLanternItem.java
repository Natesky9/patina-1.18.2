package com.natesky9.patina.item;

import com.natesky9.patina.Incursion;
import com.natesky9.patina.IncursionManager;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FeralLanternItem extends BlockItem {
    public FeralLanternItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
    public static void trigger(LivingDeathEvent event)
    {
        //if a player is holding the lantern in offhand, absorb a soul
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        ItemStack itemstack = player.getItemBySlot(EquipmentSlot.OFFHAND);
        if (!(itemstack.getItem() == ModBlocks.FERAL_LANTERN_ITEM.get())) return;
            absorbSoul(itemstack,player.getLevel(), player.blockPosition());
        Entity entity = event.getEntity();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        entity.getLevel().addParticle(ParticleTypes.POOF,x,y,z,x-player.getX(),y-player.getY(),z-player.getZ());
    }

    public static void absorbSoul(ItemStack itemstack, Level level, BlockPos pos)
    {
        level.playSound(null,pos,
                SoundEvents.PHANTOM_AMBIENT, SoundSource.PLAYERS,1,.1F);
        CompoundTag tag = itemstack.getOrCreateTag();
        if (tag.contains("count"))
        {
            int value = Mth.clamp(tag.getInt("count")+1,0,16);
            tag.putInt("count",value);
        }
        else
            tag.putInt("count",1);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (pStack.getOrCreateTag().contains("count"))
        {
            int count = pStack.getTag().getInt("count");
            pTooltip.add(new TextComponent("souls: " + count).withStyle(ChatFormatting.AQUA));
        }
        if (Screen.hasShiftDown())
        pTooltip.add(new TextComponent(
                "Equip in your offhand to absorb souls\n" +
                "Place under the light of the moon\n" +
                "Conquer the spirits trapped within\n" +
                "Only then can you remove the curse\n")
                .withStyle(ChatFormatting.AQUA));
        else
            pTooltip.add(new TextComponent("Hold shift to inspect"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag tag = stack.getOrCreateTag();
        int count = tag.contains("count") ? tag.getInt("count"):0;
        if (count < 16)
        {
            count++;
            level.playSound(null,pPlayer.blockPosition(),
                    SoundEvents.PHANTOM_AMBIENT, SoundSource.PLAYERS,1,.1F);
        }
        tag.putInt("count",count);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}
