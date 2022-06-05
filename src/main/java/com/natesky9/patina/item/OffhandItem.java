package com.natesky9.patina.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.natesky9.patina.CustomAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class OffhandItem extends Item
{
    private final Multimap<Attribute,AttributeModifier> defaultModifiers;

    public OffhandItem(Attribute attribute, int amount, Properties pProperties) {
        super(pProperties);

        UUID uuid = CustomAttributes.makeUUID(attribute,EquipmentSlot.OFFHAND, AttributeModifier.Operation.ADDITION);
        Builder<Attribute,AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute,new AttributeModifier(uuid, "custom attribute", amount, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Nullable
    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.OFFHAND;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
    //@Override
    //public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
    //    if (slot== EquipmentSlot.OFFHAND) return defaultModifiers;
    //    else return super.getAttributeModifiers(slot,stack);
    //}
}
