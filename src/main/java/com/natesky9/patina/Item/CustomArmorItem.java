package com.natesky9.patina.Item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CustomArmorItem extends ArmorItem {
    private final Multimap<Attribute,AttributeModifier> customModifiers;

    public static final UUID swiftness = UUID.fromString("65497140-f35b-4f21-8fc2-fa6fa7b67ad8");

    public static final UUID gravity_head = UUID.fromString("473bf47c-11c8-4f82-917e-02ddc9ca1f9c");
    public static final UUID gravity_body = UUID.fromString("40a9adbf-10d4-444b-9e73-48db34ccdd5f");
    public static final UUID gravity_legs = UUID.fromString("18432187-260a-4b5c-a1de-7f4d7368e62c");
    public static final UUID health_head = UUID.fromString("6f33e61b-17c7-4f9d-a5d3-a307a4c78278");
    public static final UUID health_body = UUID.fromString("41c2115f-2dba-488a-8002-63c1bfb32c28");
    public static final UUID health_legs = UUID.fromString("6f737ff2-9187-40e7-9c4a-8b4b5b71d1af");

    public CustomArmorItem(ArmorMaterial material, Type p_266831_, Properties p_40388_, Attribute attribute, AttributeModifier modifier) {
        //attributes don't matter since we're supplying our own
        super(material, p_266831_, p_40388_);
        ImmutableMultimap.Builder<Attribute,AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute, modifier);
        customModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == this.type.getSlot() ? customModifiers : super.getAttributeModifiers(slot, stack);
    }
}
