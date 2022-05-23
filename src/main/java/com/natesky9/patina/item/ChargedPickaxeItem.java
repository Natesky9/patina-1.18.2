package com.natesky9.patina.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class ChargedPickaxeItem extends PickaxeItem
{
     protected final float speedCharged;
     private final float damageCharged;
    private final Multimap<Attribute, AttributeModifier> chargedModifiers;


    public ChargedPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);

        this.speedCharged = pAttackSpeedModifier*2;
        this.damageCharged = pAttackDamageModifier*2;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 10D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 10D, AttributeModifier.Operation.ADDITION));
        this.chargedModifiers = builder.build();
    }



    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
