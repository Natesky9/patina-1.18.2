package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.world.item.ArmorItem.Type.*;


public class ModArmorMaterials {
    //protection enum maps
    private static final EnumMap<ArmorItem.Type, Integer> simple = Util.make(new EnumMap<>(ArmorItem.Type.class), value -> {
        value.put(HELMET,1);
        value.put(CHESTPLATE,3);
        value.put(LEGGINGS,2);
        value.put(BOOTS,1);
        value.put(BODY,3);
    });
    private static final EnumMap<ArmorItem.Type, Integer> standard = Util.make(new EnumMap<>(ArmorItem.Type.class), value -> {
        value.put(HELMET,2);
        value.put(CHESTPLATE,6);
        value.put(LEGGINGS,5);
        value.put(BOOTS,2);
        value.put(BODY,5);
    });
    private static final EnumMap<ArmorItem.Type, Integer> strong = Util.make(new EnumMap<>(ArmorItem.Type.class), value -> {
        value.put(HELMET,3);
        value.put(CHESTPLATE,8);
        value.put(LEGGINGS,6);
        value.put(BOOTS,3);
        value.put(BODY,11);
    });
    private static final EnumMap<ArmorItem.Type, Integer> advanced = Util.make(new EnumMap<>(ArmorItem.Type.class), value -> {
        value.put(HELMET,4);
        value.put(CHESTPLATE,9);
        value.put(LEGGINGS,7);
        value.put(BOOTS,4);
        value.put(BODY,12);
    });
    //end protection enum maps
    //register methods
    private static Holder<ArmorMaterial> register(String p_334359_, EnumMap<ArmorItem.Type, Integer> armor,
            int enchantability, Holder<SoundEvent> equip, float toughness, float heavy, Supplier<Ingredient> p_333678_)
    {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Patina.MODID, p_334359_)));
        return register(p_334359_, armor, enchantability, equip, toughness, heavy, p_333678_, list);
    }

    private static Holder<ArmorMaterial> register(
            String p_332406_, EnumMap<ArmorItem.Type, Integer> armor, int enchantability, Holder<SoundEvent> equip,
            float toughness, float heavy, Supplier<Ingredient> p_334412_, List<ArmorMaterial.Layer> p_330855_)
    {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values())
        {
            enummap.put(armoritem$type, armor.get(armoritem$type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(Patina.MODID, p_332406_),
                new ArmorMaterial(enummap, enchantability, equip, p_334412_, p_330855_, toughness, heavy));
    }
    //end register methods

    //enum maps
    public static final Holder<ArmorMaterial> COPPER = register("copper", simple, 12, SoundEvents.ARMOR_EQUIP_CHAIN,
            0f, 0f, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final Holder<ArmorMaterial> BRONZE = register("bronze", standard, 24, Holder.direct(SoundEvents.AMETHYST_BLOCK_CHIME),
            1f, 1f, () -> Ingredient.of(ModItems.BISMUTH_INGOT.get()));
    public static final Holder<ArmorMaterial> CRYSTAL = register("crystal_prime", strong, 30, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1f, 0f, () -> Ingredient.of(ModItems.PRIME_GLASS.get()));
    public static final Holder<ArmorMaterial> CRYSTAL_FORTIS = register("crystal_fortis", strong, 30, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1f, 0f, () -> Ingredient.of(ModItems.FORTIS_GLASS.get()));
    public static final Holder<ArmorMaterial> CRYSTAL_FERUS = register("crystal_ferus", strong, 30, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1f, 0f, () -> Ingredient.of(ModItems.FERUS_GLASS.get()));
    public static final Holder<ArmorMaterial> CRYSTAL_ANIMA = register("crystal_anima", strong, 30, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1f, 0f, () -> Ingredient.of(ModItems.ANIMA_GLASS.get()));
    //GLASS("glass",30, Util.make(new EnumMap<>(ArmorItem.Type.class),typeEnumMap ->
    //{
    //    typeEnumMap.put(ArmorItem.Type.BOOTS,4);
    //    typeEnumMap.put(ArmorItem.Type.LEGGINGS,7);
    //    typeEnumMap.put(ArmorItem.Type.CHESTPLATE,9);
    //    typeEnumMap.put(HELMET,4);}),
    //        0,SoundEvents.AMETHYST_BLOCK_CHIME,2F,0F,
    //        () -> {return Ingredient.of(ModItems.PRIME_GLASS.get());}),
    public static final Holder<ArmorMaterial> DRAGON = register("dragon", advanced, 8, Holder.direct(SoundEvents.ENDER_DRAGON_FLAP),
            3f, 3f, () -> Ingredient.of(ModItems.DRAGON_SCALE.get()));

    public static final Holder<ArmorMaterial> UMBRA = register("umbra", simple,
            12, SoundEvents.ARMOR_EQUIP_CHAIN,
            0, -1f, () -> Ingredient.of(Items.PHANTOM_MEMBRANE));


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
        p_266653_.put(HELMET, 11);
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
