package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
        //name =
        //durabilityMultiplier
        //protectionFunctionForType
        //enchantmentValue
        //sound
        //toughness
        //knockbackResistance
        //repairIngredient
    COPPER("copper",10, Util.make(new EnumMap<>(ArmorItem.Type.class),typeEnumMap ->
    {
        typeEnumMap.put(ArmorItem.Type.BOOTS,1);
        typeEnumMap.put(ArmorItem.Type.LEGGINGS,3);
        typeEnumMap.put(ArmorItem.Type.CHESTPLATE,5);
        typeEnumMap.put(ArmorItem.Type.HELMET,2);}),
            10,SoundEvents.ARMOR_EQUIP_GENERIC,0,0,
            () -> {return Ingredient.of(Items.COPPER_INGOT);}),
    BRONZE("bronze",24, Util.make(new EnumMap<>(ArmorItem.Type.class),typeEnumMap ->
    {
        typeEnumMap.put(ArmorItem.Type.BOOTS,4);
        typeEnumMap.put(ArmorItem.Type.LEGGINGS,6);
        typeEnumMap.put(ArmorItem.Type.CHESTPLATE,8);
        typeEnumMap.put(ArmorItem.Type.HELMET,4);}),
            0,SoundEvents.COPPER_PLACE,2F,0F,
            () -> {return Ingredient.of(ModItems.BISMUTH_INGOT.get());}),
    DRAGON("dragon",68,Util.make(new EnumMap<>(ArmorItem.Type.class),typeEnumMap ->
    {
        typeEnumMap.put(ArmorItem.Type.HELMET,6);
        typeEnumMap.put(ArmorItem.Type.CHESTPLATE,6);
        typeEnumMap.put(ArmorItem.Type.LEGGINGS,6);
        typeEnumMap.put(ArmorItem.Type.BOOTS,6);}),15,SoundEvents.ENDER_DRAGON_FLAP,2F,0.05F,
            () -> {return Ingredient.of(ModItems.DRAGON_SCALE.get());});


    //LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.LEATHER);
    //});
    //CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.IRON_INGOT);
    //}),
    //IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.IRON_INGOT);
    //}),
    //GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.GOLD_INGOT);
    //}),
    //DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.DIAMOND);
    //}),
    //TURTLE("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> {
    //    return Ingredient.of(Items.SCUTE);
    //}),
    //NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
    //    return Ingredient.of(Items.NETHERITE_INGOT);
    //});

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
        this.name = p_268171_;
        this.durabilityMultiplier = p_268303_;
        this.protectionFunctionForType = p_267941_;
        this.enchantmentValue = p_268086_;
        this.sound = p_268145_;
        this.toughness = p_268058_;
        this.knockbackResistance = p_268180_;
        this.repairIngredient = new LazyLoadedValue<>(p_268256_);
    }

    public int getDurabilityForType(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type p_266752_) {
        return this.protectionFunctionForType.get(p_266752_);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
