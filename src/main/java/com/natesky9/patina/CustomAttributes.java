package com.natesky9.patina;

import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.fromValue;

public class CustomAttributes {
    public static UUID makeUUID(Attribute attribute, EquipmentSlot slot, Operation operation)
    {
        String attribute_string = attribute.toString();
        String slot_string = slot.toString();
        String operation_string = operation.toString();
        String combined = Patina.MOD_ID + attribute_string + slot_string + operation_string;
        UUID newuuid = UUID.nameUUIDFromBytes(combined.getBytes(StandardCharsets.UTF_8));
        return newuuid;
    }
    public static void convert(ItemStack itemStack)
    {
        double getamount;
        AttributeModifier getmodifier;
        Operation getoperation;
        UUID getuuid;
        Multimap<Attribute, AttributeModifier> attributes;

        for (EquipmentSlot getslot: EquipmentSlot.values())//loop through the slots
        {
            attributes = itemStack.getItem().getDefaultAttributeModifiers(getslot);
            for (Attribute getattribute:attributes.keySet())//loop through the attributes
            {
                //getattribute
                getmodifier = attributes.get(getattribute).iterator().next();
                getoperation = getmodifier.getOperation();
                getuuid = getmodifier.getId();
                getamount = getmodifier.getAmount();


                //give it our own uuid using the attribute and slot
                UUID getid = CustomAttributes.makeUUID(getattribute,getslot,getoperation);//creates the custom uuid
                AttributeModifier newmodifier = new AttributeModifier(getid,"custom attribute",getamount,getoperation);

                itemStack.addAttributeModifier(getattribute,getmodifier,getslot);
            }
        }
        System.out.println("Converted item to nbt attributes");
    }

    public static void addAttribute(ItemStack itemStack, Attribute attribute, EquipmentSlot slot, Operation operation, double amount)
    {
        if (!itemStack.getOrCreateTag().contains("AttributeModifiers"))
        {
            convert(itemStack);
        }

        ListTag list = itemStack.getTag().getList("AttributeModifiers",10);
        itemStack.getTag().remove("AttributeModifiers");
        boolean alreadyexisted = false;
        double alreadyexistedamount = 0;
        for (int i = 0; i < list.size(); i++)
        {
            //get the list of attributes
            CompoundTag getmodifier = list.getCompound(i);
            //get the attribute from nbt
            String attributestring = getmodifier.getString("AttributeName");
            Optional<Attribute> attributeOptional = Registry.ATTRIBUTE.getOptional(ResourceLocation.tryParse(attributestring));
            if (attributeOptional.isEmpty())
            {
                System.out.println("Error, reading from nbt doesn't return an attribute #CustomAttributes");
                continue;
            }
            Attribute getattribute = attributeOptional.get();

            double getamount = getmodifier.getDouble("Amount");
            Operation getoperation = fromValue(getmodifier.getInt("Operation"));
            String getname = getmodifier.getString("Name");
            EquipmentSlot getslot = EquipmentSlot.byName(getmodifier.getString("Slot"));

            //now check for the newly added one
            if (getattribute == attribute)
            {
                alreadyexisted = true;
                alreadyexistedamount = getamount;
                continue;
            }
            UUID uuid = makeUUID(getattribute,getslot,getoperation);
            AttributeModifier attributeModifier = new AttributeModifier(uuid,"Custom",getamount,getoperation);
            itemStack.addAttributeModifier(getattribute,attributeModifier,getslot);
        }

        UUID uuid = makeUUID(attribute,slot,operation);
        AttributeModifier addmodifier = new AttributeModifier(uuid,"Custom",alreadyexistedamount + amount, operation);
        itemStack.addAttributeModifier(attribute,addmodifier,slot);
        System.out.println("added attribute");
    }
}
