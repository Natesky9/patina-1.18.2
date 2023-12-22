package com.natesky9.patina.Item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class ClawItem extends Item {
    private final Multimap<Attribute,AttributeModifier> customModifiers;
    public static final UUID block_reach = UUID.fromString("3cecc7db-f678-4274-bf0b-4738a4b82a77");
    public ClawItem(Properties p_41383_) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute,AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.BLOCK_REACH.get(),new AttributeModifier(block_reach,"Block_reach",3, AttributeModifier.Operation.ADDITION));
        customModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.OFFHAND ? customModifiers : ImmutableMultimap.of();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND)
        {
            level.playSound(null,player.blockPosition(), SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.PLAYERS,1f,1f);
        }
        return super.use(level, player, hand);
    }
}
