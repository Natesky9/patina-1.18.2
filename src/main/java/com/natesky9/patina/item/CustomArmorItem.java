package com.natesky9.patina.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.natesky9.patina.CustomAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomArmorItem extends ArmorItem
{
    protected Multimap<Attribute, AttributeModifier> baseAttributes;
    private final Supplier<Attribute> attributeSupplier;
    private final int attributeValue;


    public CustomArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties,
                           Supplier<Attribute> attribute, int value) {
        super(pMaterial, pSlot, pProperties);
        baseAttributes = null;
        this.attributeSupplier = attribute;
        this.attributeValue = value;
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot != getSlot()) return ImmutableMultimap.of();
        //if (stack.getDamageValue() == 0) return ImmutableMultimap.of();
        if (this.baseAttributes == null)
        {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            UUID uuid = CustomAttributes.makeUUID(attributeSupplier.get(),slot, AttributeModifier.Operation.ADDITION);
            AttributeModifier attributeModifier = new AttributeModifier(uuid,"get attribute",this.attributeValue, AttributeModifier.Operation.ADDITION);
            builder.put(attributeSupplier.get(),attributeModifier);
            this.baseAttributes = builder.build();
        }
        return this.baseAttributes;
    }

}
