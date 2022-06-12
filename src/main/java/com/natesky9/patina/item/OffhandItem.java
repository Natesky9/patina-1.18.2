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
import java.util.function.Supplier;

public class OffhandItem extends Item
{
    private Supplier<Attribute> attributeSupplier;
    private int attributeValue;
    private Multimap<Attribute,AttributeModifier> baseAttributes;

    public OffhandItem(Supplier<Attribute> attribute, int amount, Properties pProperties) {
        super(pProperties);
        this.attributeSupplier = attribute;
        this.attributeValue = amount;
        //delay building the modifiers until the attributes are initialized
        baseAttributes = null;
    }

    public OffhandItem(Attribute attribute, int amount, Properties properties) {
        super(properties);
        UUID uuid = CustomAttributes.makeUUID(attribute,EquipmentSlot.OFFHAND, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifier = new AttributeModifier(uuid,"attribute",amount, AttributeModifier.Operation.ADDITION);
        baseAttributes = ImmutableMultimap.of(attribute,attributeModifier);
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

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot != EquipmentSlot.OFFHAND) return ImmutableMultimap.of();
        if (baseAttributes == null)
        {
            UUID uuid = CustomAttributes.makeUUID(attributeSupplier.get(), EquipmentSlot.OFFHAND, AttributeModifier.Operation.ADDITION);
            Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(attributeSupplier.get(), new AttributeModifier(uuid, "custom attribute", attributeValue, AttributeModifier.Operation.ADDITION));
            baseAttributes = builder.build();
        }
        return baseAttributes;
    }


}
