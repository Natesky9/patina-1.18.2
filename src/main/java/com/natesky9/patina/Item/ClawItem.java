package com.natesky9.patina.Item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ClawItem extends CustomItem {
    public static final UUID block_reach = UUID.fromString("3cecc7db-f678-4274-bf0b-4738a4b82a77");
    public static final UUID entity_reach = UUID.fromString("3313e54d-5804-478b-b11f-a3b42247b526");
    public static final UUID strength = UUID.fromString("cb35b736-78b6-46bb-b0c8-bf9abf8f75eb");

    public ClawItem(Properties p_41383_, EquipmentSlot slot, Attribute attribute, AttributeModifier modifier) {
        //constructor for one attribute
        super(p_41383_,slot,attribute,modifier);
    }
    public ClawItem(Properties p_41383_, EquipmentSlot slot, Attribute attribute1, AttributeModifier modifier1,
                    Attribute attribute2, AttributeModifier modifier2) {
        //constructor for two attribute
        super(p_41383_,slot,attribute1,modifier1,attribute2,modifier2);
    }
    public ClawItem(Properties p_41383_, EquipmentSlot slot, Attribute attribute1, AttributeModifier modifier1,
                    Attribute attribute2, AttributeModifier modifier2,
                    Attribute attribute3, AttributeModifier modifier3) {
        //constructor for three attribute
        super(p_41383_,slot,attribute1,modifier1,attribute2,modifier2,attribute3,modifier3);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND)
        {
            level.playSound(null,player.blockPosition(), SoundEvents.PARROT_EAT, SoundSource.PLAYERS,1f,1f);
        }
        return super.use(level, player, hand);
    }
}
