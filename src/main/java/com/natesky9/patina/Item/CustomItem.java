package com.natesky9.patina.Item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

public class CustomItem extends Item {
    private final Multimap<Attribute,AttributeModifier> customModifiers;
    private final EquipmentSlot slot;

    public CustomItem(Properties p_41383_, EquipmentSlot equipmentSlot, Attribute attribute, AttributeModifier modifier) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute,modifier);
        customModifiers = builder.build();
        slot = equipmentSlot;
    }
    public CustomItem(Properties p_41383_, EquipmentSlot equipmentSlot, Attribute attribute1, AttributeModifier modifier1,
                      Attribute attribute2, AttributeModifier modifier2) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute1,modifier1);
        builder.put(attribute2,modifier2);
        customModifiers = builder.build();
        slot = equipmentSlot;
    }
    public CustomItem(Properties p_41383_, EquipmentSlot equipmentSlot, Attribute attribute1, AttributeModifier modifier1,
                      Attribute attribute2, AttributeModifier modifier2,
                      Attribute attribute3, AttributeModifier modifier3) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute1,modifier1);
        builder.put(attribute2,modifier2);
        builder.put(attribute3,modifier3);
        customModifiers = builder.build();
        slot = equipmentSlot;
    }

    //@Override
    //public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
    //    return slot == equipmentSlot ? customModifiers : super.getAttributeModifiers(slot, stack);
    //}
}
