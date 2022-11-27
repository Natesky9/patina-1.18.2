package com.natesky9.patina.init;

import com.natesky9.patina.Enchantments.*;
import com.natesky9.patina.Patina;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModEnchantments
{
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Patina.MOD_ID);
    //why this isn't public in the enchantment class, I do not know

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.OFFHAND,EquipmentSlot.MAINHAND};
    private static final EquipmentSlot[] ALL_SLOTS = ArrayUtils.addAll(ARMOR_SLOTS,HAND_SLOTS);
    //----------//
    public static RegistryObject<Enchantment> CURSE =
            ENCHANTMENTS.register("curse",
                    () -> new Curse(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR,ARMOR_SLOTS));

    public static RegistryObject<Enchantment> SOULBOUND =
            ENCHANTMENTS.register("soulbound",
                    () -> new SoulboundEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ALL_SLOTS));
    //deadly sins
    public static RegistryObject<Enchantment> ENVYCURSE =
            ENCHANTMENTS.register("envy",
                    () -> new EnvyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> GREEDCURSE =
            ENCHANTMENTS.register("greed",
                    () -> new GreedEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> GLUTTONYCURSE =
            ENCHANTMENTS.register("gluttony",
                    () -> new GluttonyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> LUSTCURSE =
            ENCHANTMENTS.register("lust",
                    () -> new LustEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> PRIDECURSE =
            ENCHANTMENTS.register("pride",
                    () -> new PrideEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> SLOTHCURSE =
            ENCHANTMENTS.register("sloth",
                    () -> new SlothEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> WRATHCURSE =
            ENCHANTMENTS.register("wrath",
                    () -> new WrathEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //
    public static RegistryObject<Enchantment> ENVYBLESSING =
            ENCHANTMENTS.register("strife",
                    () -> new StrifeEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> GLUTTONYBLESSING =
            ENCHANTMENTS.register("brimful",
                    () -> new BrimfulEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> GREEDBLESSING =
            ENCHANTMENTS.register("avarice",
                    () -> new AvariceEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> LUSTBLESSING =
            ENCHANTMENTS.register("coercion",
                    () -> new CoercionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> PRIDEBLESSING =
            ENCHANTMENTS.register("humility",
                    () -> new HumilityEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> SLOTHBLESSING =
            ENCHANTMENTS.register("apathy",
                    () -> new ApathyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> WRATHBLESSING =
            ENCHANTMENTS.register("retribution",
                    () -> new RetributionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //----------//
    public static List<Supplier<Enchantment>> deadlySins = List.of
            (
                ENVYCURSE,
                GLUTTONYCURSE,
                GREEDCURSE,
                LUSTCURSE,
                SLOTHCURSE,
                WRATHCURSE
            );
    //
    public static void register(IEventBus eventBus)
    {
        ENCHANTMENTS.register(eventBus);
    }
}
