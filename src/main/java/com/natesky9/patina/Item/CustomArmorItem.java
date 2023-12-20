package com.natesky9.patina.Item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CustomArmorItem extends ArmorItem {
    private final Multimap<Attribute,AttributeModifier> customModifiers;

    public static final UUID swiftness = UUID.fromString("65497140-f35b-4f21-8fc2-fa6fa7b67ad8");
    //TODO:these need uuids per slot
    public static final UUID fly = UUID.fromString("473bf47c-11c8-4f82-917e-02ddc9ca1f9c");

    public CustomArmorItem(Type p_266831_, Properties p_40388_, Attribute attribute, AttributeModifier modifier) {
        //attributes don't matter since we're supplying our own
        super(ArmorMaterials.LEATHER, p_266831_, p_40388_);
        ImmutableMultimap.Builder<Attribute,AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute, modifier);
        customModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == this.type.getSlot() ? customModifiers : super.getAttributeModifiers(slot, stack);
    }
}
